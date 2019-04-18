package com.supergenius.web.admin.enterpriser.hellper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.enterpriser.helper.EmailHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Lecture;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.enterpriser.service.LectureSO;
import com.supergenius.xo.enterpriser.service.ParticipateSO;
import com.supergenius.xo.user.enums.EContent;

/** 
 * 讲座后台HP
 * @author chenminchang
 * @date 2016-10-25 下午4:07:11 
 */
public class LectureHP extends BaseHP {
	
	private static Logger log = LoggerFactory.getLogger(LectureHP.class);
	private static LectureSO so;
	private static ParticipateSO participateSO;

	private static LectureSO getSO() {
		if (so == null) {
			so = spring.getBean(LectureSO.class);
		}
		return so;
	}
	
	private static ParticipateSO getParticipateSO() {
		if (participateSO == null) {
			participateSO = spring.getBean(ParticipateSO.class);
		}
		return participateSO;
	}
	
	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keywords))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, (model.get(ViewKeyDict.keywords).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.timestart))) {
			String start = model.get(ViewKeyDict.timestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.time + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.timeend))) {
			String end = model.get(ViewKeyDict.timeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.time + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<Lecture> list = getSO().getList(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 更新lecture状态
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26上午11:33:36
	 */
	public static boolean updateStatus(String uid, EStatus status) {
		if (uid != null) {
			if (EStatus.enable.equals(status)) {//开始报名操作，将同一讲座的其他期为报名中的设置为报名结束
				updateOtherStatus(uid); 
			}
			return getSO().updateStatus(uid, status);
		}
		return false;
	}
	
	/**
	 * 更新同一讲座的其他期的status
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午12:21:21
	 */
	public static boolean updateOtherStatus(String uid) {
		Lecture lec = getSO().get(uid);
		if (lec != null) {
			List<Lecture> list = getSO().getListBySn(lec.getSn());
			for (Lecture lecture : list) {
				if (EStatus.enable.equals(lecture.getStatus())) {
					getSO().updateStatus(lecture.getUid(), EStatus.end);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 改变讲座时间，地点，发邮件
	 * @param uid
	 * @param status
	 * @param adminlog
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午4:59:40
	 */
	public static boolean changeLecture(String uid, EStatus status, String reason) {
		Lecture lecture = getSO().get(uid);
		if (lecture != null) {
			List<Participate> list = getParticipateSO().getListByLecture(lecture.getUid());
			if (EStatus.disable.equals(status)) {//信息确定
				if (getSO().updateStatus(uid, EStatus.wait)) {
					for (Participate participate : list) {
						if (!EmailHP.sendLectureAbout( lecture.getName(), participate.getName(), lecture.getTimeStr(), reason, String.valueOf(lecture.getSemester()), lecture.getAddress(), participate.getEmail(), EContent.email_lecturestart))
							log.info("the Lecture set status ok , but send email fail to Participate, the Participate uid:" + participate.getUid() + "  ,the Participate name:" + participate.getName());
					}
					return true;
				}
			} else if (EStatus.enable.equals(status)) {//信息修改
				for (Participate participate : list) {
					if (!EmailHP.sendLectureAbout( lecture.getName(), participate.getName(), lecture.getTimeStr(), reason, String.valueOf(lecture.getSemester()), lecture.getAddress(), participate.getEmail(), EContent.email_lecturetimechange))
						log.info("send email fail to Participate from update lecture info, the Participate uid:" + participate.getUid() + "  ,the Lecture uid:" + lecture.getUid());
				}
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * 增加学期
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午6:24:27
	 */
	public static boolean addSemester(String uid) {
		Lecture lecture = getSO().get(uid);
		int semester = getLectuerMaxSemester(lecture);
		if (-1 != semester) {
			lecture.setUid(GlobalUtil.getUUID());
			lecture.setSemester(semester + 1);
			lecture.setStatus(EStatus.init);
			lecture.setCreatetime(DateUtil.NowTime());
			return getSO().add(lecture);
		}
		return false;
	}
	
	/**
	 * 得到当前讲座的最大期
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-10-26下午6:32:14
	 */
	public static int getLectuerMaxSemester(Lecture lecture) {
		if (lecture != null) {
			Map<String, Object> map = getParamMap(Pager.getNewInstance(1, 1), true);
			map.put(MapperDict.sn, lecture.getSn());
			map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.semester + MapperDict.desc);
			List<Lecture> list = getSO().getList(map);
			if (list != null && list.size() >0 )
				return list.get(0).getSemester();
		}
		return -1;
	}
}
