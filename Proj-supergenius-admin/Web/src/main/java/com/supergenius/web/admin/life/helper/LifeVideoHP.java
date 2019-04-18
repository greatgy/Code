package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.News;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.life.service.SubjectSO;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 视频管理HP
 * 
 * @author YangGuang
 * @date 2018年5月11日16:39:46
 */
public class LifeVideoHP extends BaseHP {

	private static VideoSO so;
	private static AdminSO adminSO;
	private static SubjectSO subjectSO;
	private static CommentsSO commentsSO;
	private static NewsSO newsSO;
	private static UserSO userSO;

	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}

	private static VideoSO getSO() {
		if (so == null) {
			so = spring.getBean(VideoSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}

	private static SubjectSO getSubjectSO() {
		if (subjectSO == null) {
			subjectSO = spring.getBean(SubjectSO.class);
		}
		return subjectSO;
	}

	private static CommentsSO getCommentsSO() {
		if (commentsSO == null) {
			commentsSO = spring.getBean(CommentsSO.class);
		}
		return commentsSO;
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @return
	 * @author YangGuang
	 * @createtime 2018年5月10日11:54:48
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.grade))) {
			map.put(MapperDict.grade, model.get(MapperDict.grade).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.cid))) {
			map.put(MapperDict.cid, model.get(MapperDict.cid).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.sid))) {
			map.put(MapperDict.sid, model.get(MapperDict.sid).toString().trim());
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy,
				MapperDict.sql_order_by + MapperDict.state + MapperDict.asc + MapperDict.comma + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Video> list = getSO().getList(map);
		User user = null;
		for (Video video : list) {
			if (StrUtil.isNotEmpty(video.getAdminuid())) {
				Admin admin = getAdminSO().get(video.getAdminuid());
				if (admin != null) {
					video.setAdminname(admin.getName());
				}
			}
			if (video.getCid() == 32768) {
				Subject subject = getSubjectSO().get(video.getSid());
				video.setSubjectname(subject.getName());
			}
			user = getUserSO().get(video.getUseruid());
			if (user!=null) {
				video.setUsername(user.getUsername());
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 获得专家点评二级列表
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static List<Comments> getSecondMajor(Video video) {
		List<Comments> list = new ArrayList<Comments>();
		Map<String, Object> majormap = getParamMap();
		majormap.put(MapperDict.type, EComment.major);
		majormap.put(MapperDict.fromuseruid, ViewKeyDict.defaultuid);
		majormap.put(MapperDict.fromuid, video.getUid());
		majormap.put(MapperDict.touseruid, video.getUseruid());
		List<Comments> majorlist = getCommentsSO().getList(majormap);
		Map<String, Object> usermap = getParamMap();
		usermap.put(MapperDict.type, EComment.major);
		usermap.put(MapperDict.fromuseruid, video.getUseruid());
		usermap.put(MapperDict.fromuid, video.getUid());
		usermap.put(MapperDict.touseruid, ViewKeyDict.defaultuid);
		usermap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Comments> userlist = getCommentsSO().getList(usermap);
		list.addAll(majorlist);
		list.addAll(userlist);
		for (Comments comments : list) {
			comments.setIsmajor(1);
		}
		list.sort(COMPARATOR);
		return list;
	};

	private static final Comparator<Comments> COMPARATOR = new Comparator<Comments>() {
		public int compare(Comments o1, Comments o2) {
			int i = o1.compareTo(o2);
			return i;//
		}
	};

	/**
	 * 点评之后发送消息
	 * 
	 * @param video
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Video video) {
		News news = new News();
		news.setTitle(video.getTitle());
		news.setTouid(video.getUseruid());
		news.setFromuid(ViewKeyDict.defaultuid);
		news.setContent("/videoDetail/" + video.getCid() + "/" + video.getUid());
		news.setType(ELifeMsg.evaluatevideo);
		getNewsSO().add(news);
	}
}