package com.supergenius.web.admin.moral.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.moral.helper.BaseStudentHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.ScoreDetail;
import com.supergenius.xo.moral.enums.EScoreDetail;
import com.supergenius.xo.moral.service.ScoreDetailSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 学员hp
 * @author liushaomin
 */
public class StudentHP extends BaseStudentHP{
	
	private static UserSO userSO;
	private static ScoreDetailSO detailSO;
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static ScoreDetailSO getDetailSO() {
		if (detailSO == null) {
			detailSO = (ScoreDetailSO) spring.getBean(ScoreDetailSO.class);
		}
		return detailSO;
	}
	
	/**
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			Map<String, Object> usermap = getParamMap();
			usermap.put(MapperDict.name + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString().trim());
			List<User> users = getUserSO().getList(usermap);
			String[] useruids = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				useruids[i] = users.get(i).getUid(); 
			}
			map.put(MapperDict.useruid + MapperDict.suffix_in_key, useruids);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

	/**
	 * 查寻修改历史
	 * @param model
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, Object> queryScoreDetail(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.id))) {
			map.put(ViewKeyDict.useruid, model.get(ViewKeyDict.id));
		} else {
			map.put(ViewKeyDict.useruid, null);
		}
		map.put(ViewKeyDict.type, EScoreDetail.adminModify);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getDetailSO().getCount(map));
		result.put(ViewKeyDict.rows, getDetailSO().getList(map));
		return result;
	}

	/**
	 * 修改积分
	 * @param beginscore
	 * @param scoreDetail
	 * @param admin
	 * @return
	 * @author YuYingJie
	 */
	public static boolean modifyScore(int beginscore, ScoreDetail scoreDetail, Admin admin) {
		int finishscore = scoreDetail.getFinishscore();
		if (Math.abs(finishscore - beginscore)/(float)beginscore > 0.5) {
			return false;
		}
		updateScore(scoreDetail.getUseruid(), finishscore, beginscore, EScoreDetail.adminModify, admin, scoreDetail.getDesc());
		return true;
	}
	
	
	/**
	 * 证书编号
	 * 证书编号由“BEM”+ 12位阿拉伯数字确定；
	 * 二、第1—6是获得证书年度、月度代码（年度四位、月度两位）；
	 * 三、第7-12位为证书核发顺序编号，按照每年度各个月份颁发证书00001～99999依次顺序取值。
	 * 举例：某学院在2015年1月份第15顺位获得证书。其证书编号是BEM20150100015
	 * @author liushaomin
	 * @return 
	 */
	public static String getCertificateSN() {
		String certificateSN = "";
		String sn = "BEM";
		String prefix = DateUtil.NowTime().toString("yyyyMM");
		String startTime = DateUtil.NowTime().toString("yyyy-MM-01");
		int num = getSO().getCountCertificate(new DateTime(startTime)) + 1;
		int size = 5 - String.valueOf(num).length();
		if (size == 1) {
			certificateSN = sn + prefix + "0" + num;
		}else if(size == 2) {
			certificateSN = sn + prefix + "00" + num ;
		}else if (size == 3) {
			certificateSN = sn + prefix + "000" + num;
		}else if (size == 4) {
			certificateSN = sn + prefix + "0000" + num;
		}
		 return certificateSN;
	}

}
