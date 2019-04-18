package com.supergenius.web.admin.manager.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.AppReplyDetail;
import com.supergenius.xo.manager.entity.AppReplyExpert;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.service.AppReplyDetailSO;
import com.supergenius.xo.manager.service.AppReplyExpertSO;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.ConfeMemberSO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 答辩管理HP
 * @author liubin
 * @date 2016-11-9 下午3:07:20 
 */
public class ReplyHP extends BaseHP {
	
	private static AppReplySO so;
	private static UserSO userSO;
	private static AppReplyDetailSO appReplyDetailSO;
	private static AppReplyExpertSO appReplyExpertSO;
	private static ExpertSO expertSO;
	private static ConfeMemberSO confeMemberSO;
	private static ConferenceSO conferenceSO;
	
	private static AppReplySO getSO() {
		if (so == null) {
			so = spring.getBean(AppReplySO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static AppReplyDetailSO getAppReplyDetailSO() {
		if (appReplyDetailSO == null) {
			appReplyDetailSO = spring.getBean(AppReplyDetailSO.class);
		}
		return appReplyDetailSO;
	}
	
	private static AppReplyExpertSO getAppReplyExpertSO() {
		if (appReplyExpertSO == null) {
			appReplyExpertSO = spring.getBean(AppReplyExpertSO.class);
		}
		return appReplyExpertSO;
	}
	
	private static ExpertSO getExpertSO() {
		if (expertSO == null) {
			expertSO = spring.getBean(ExpertSO.class);
		}
		return expertSO;
	}
	
	private static ConfeMemberSO getConfeMemberSO() {
		if (confeMemberSO == null) {
			confeMemberSO = spring.getBean(ConfeMemberSO.class);
		}
		return confeMemberSO;
	}
	
	private static ConferenceSO getConferenceSO() {
		if (conferenceSO == null) {
			conferenceSO = spring.getBean(ConferenceSO.class);
		}
		return conferenceSO;
	}

	/**
	 * 根据证书类型得到获得该证书的答辩次数
	 * @param typeCertificate
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午3:56:44
	 * @return int
	 */
	public static int getAppDegreeSuccessCount(ECertificate typeCertificate) {
		List<AppReply> list = getSO().getList(EReplyStage.passReport);
		int count = 0;
		for (AppReply appReply : list) {
			if (typeCertificate == ComplaintHP.getDegree(appReply.getCertificated())) {
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-11-9下午8:42:32
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.keyword, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.degree))) {
			if (model.get(ViewKeyDict.degree).toString().equals(ECertificate.RMBA.toString())) {
				map.put(MapperDict.degreenull, true);
			}
			if (model.get(ViewKeyDict.degree).toString().equals(ECertificate.SMBA.toString())) {
				map.put(MapperDict.degree, ECertificate.RMBA.toString());
			}
			if (model.get(ViewKeyDict.degree).toString().equals(ECertificate.TMBA.toString())) {
				map.put(MapperDict.degree, ECertificate.SMBA.toString());
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.level))) {
			map.put(MapperDict.majorlevel, model.get(ViewKeyDict.level).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.stages))) {
			if (model.get(ViewKeyDict.stages).toString().equals("0")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getReportApplicationReviewStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("1")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getDemonstrationMeetingStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("2")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getPreReplyStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("3")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getReplyApplicationReviewStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("4")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getWaitingReplyingStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("5")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getReplyingStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("6")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getWaitingReplyResultStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("7")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getEndReplyStages());
			}
			if (model.get(ViewKeyDict.stages).toString().equals("8")) {
				map.put(MapperDict.replystage + MapperDict.suffix_in_key, EReplyStage.getReportReviewStages());
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		List<AppReply> list = getSO().searchList(map);
		for (AppReply appReply : list) {
			appReply.setUser(getUserSO().get(appReply.getUseruid()));
			appReply.setDegree(ComplaintHP.getDegree(appReply.getCertificated()).getName());
			int count = getAppReplyDetailSO().getCount(EReplyStage.getAboutPay());
			if (count > 0) {
				if (count > 1) {
					if (appReply.getDegree().equals(ECertificate.RMBA.toString())) {
						appReply.setReplyfee(SysConf.RMBAReplyFee + (count - 1) * SysConf.RMBASecondReplyFee);
					} else if (appReply.getDegree().equals(ECertificate.SMBA.toString())) {
						appReply.setReplyfee(SysConf.SMBAReplyFee + (count - 1) * SysConf.SMBASecondReplyFee);
					} else if (appReply.getDegree().equals(ECertificate.TMBA.toString())) {
						appReply.setReplyfee(SysConf.TMBAReplyFee + (count - 1) * SysConf.TMBASecondReplyFee);
					}
				} else {
					if (appReply.getDegree().equals(ECertificate.RMBA.toString())) {
						appReply.setReplyfee(SysConf.RMBAReplyFee);
					} else if (appReply.getDegree().equals(ECertificate.SMBA.toString())) {
						appReply.setReplyfee(SysConf.SMBAReplyFee);
					} else if (appReply.getDegree().equals(ECertificate.TMBA.toString())) {
						appReply.setReplyfee(SysConf.TMBAReplyFee);
					}
				}
			}
			List<AppReplyDetail> list2 = getAppReplyDetailSO().getList(appReply.getUid());
			for (AppReplyDetail appReplyDetail : list2) {
				if (StrUtil.isNotEmpty(appReplyDetail.getAdminuid())) {
					Admin admin = AdminHP.getAdmin();
					if (admin != null) {
						appReplyDetail.setAdminname(admin.getName());
					}
				}
			}
			appReply.setAppreplydetaillist(list2);
			List<AppReplyExpert> list3 = getAppReplyExpertSO().getAppReplyExpertList(appReply.getUid());
			if (list3.size() > 0 && list3 != null) {
				Expert expert = getExpertSO().get(list3.get(0).getExpertuid());
				if (expert != null) {
					expert.setUser(getUserSO().get(expert.getUseruid()));
				}
				appReply.setExpert(expert);
			}
			if (list3.size() >= 2) {
				Expert expert2 = getExpertSO().get(list3.get(1).getExpertuid());
				if (expert2 != null) {
					expert2.setUser(getUserSO().get(expert2.getUseruid()));
				}
				appReply.setExpert2(expert2);
			}
			if (list3.size() >= 3) {
				Expert expert3 = getExpertSO().get(list3.get(2).getExpertuid());
				if (expert3 != null) {
					expert3.setUser(getUserSO().get(expert3.getUseruid()));
				}
				appReply.setExpert3(expert3);
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 查询专家
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午12:30:56
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> queryExpert(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.showname + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.level))) {
			if (model.get(ViewKeyDict.level).toString().equals(ECertificate.RMBA.name().toString())) {
				map.put(MapperDict.level, EExpertLevel.expert.toString());
			}
			if (model.get(ViewKeyDict.level).toString().equals(ECertificate.SMBA.name().toString())) {
				map.put(MapperDict.level, EExpertLevel.highExpert.toString());
			}
			if (model.get(ViewKeyDict.level).toString().equals(ECertificate.TMBA.name().toString())) {
				map.put(MapperDict.level, EExpertLevel.specialExpert.toString());
			}
		}
		result.put(ViewKeyDict.total, getExpertSO().getCount(map));
		List<Expert> list = getExpertSO().getList(map);
		for (Expert expert : list) {
			User user = getUserSO().get(expert.getUseruid());
			expert.setUser(user);
			if (user != null) {
				expert.setUsersn(user.getUsersn());
				expert.setUsername(user.getShowname());
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 定时器，更改答辩状态
	 * @return
	 * @author liubin
	 * @createtime 2016-11-14下午6:24:49
	 * @return boolean
	 */
	public static boolean updateReplyStage() {
		List<AppReply> list = getSO().getList(EReplyStage.passData);
		List<AppReply> list2 = getSO().getList(EReplyStage.passReplyData);
		List<AppReply> list3 = getSO().getList(EReplyStage.opening);
		List<AppReply> list4 = getSO().getList(EReplyStage.replying);
		for (AppReply appReply : list) {
			String openTimeString = appReply.getOpentimeok();
			String opentime = openTimeString.substring(0, openTimeString.indexOf(" "));
			DateTime time = DateTime.parse(opentime);
			AppReplyDetail appReplyDetail = new AppReplyDetail();
			if (time.isBeforeNow() && time.isAfter(DateTime.now().plusDays(-1))) {
				appReply.setReplystage(EReplyStage.opening);
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.OpenReplyStartTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.passData);
				appReplyDetail.setReplystageto(EReplyStage.opening);
				getSO().update(appReply, appReplyDetail);
			}
		}
		for (AppReply appReply : list3) {
			String openTimeString = appReply.getOpentimeok();
			String opentime = openTimeString.substring(0, openTimeString.indexOf(" "));
			DateTime time = DateTime.parse(opentime);
			AppReplyDetail appReplyDetail = new AppReplyDetail();
			if (time.isAfter(DateTime.now().plusDays(1))) {
				appReply.setReplystage(EReplyStage.endOpen);
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.OpenReplyEndTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.opening);
				appReplyDetail.setReplystageto(EReplyStage.endOpen);
				getSO().update(appReply, appReplyDetail);
			}
		}
		for (AppReply appReply : list2) {
			String replyTimeString = appReply.getReplytimeok();
			String replytime = replyTimeString.substring(0, replyTimeString.indexOf(" "));
			DateTime time = DateTime.parse(replytime);
			AppReplyDetail appReplyDetail = new AppReplyDetail();
			if (time.isBeforeNow() && time.isAfter(DateTime.now().plusDays(-1))) {
				appReply.setReplystage(EReplyStage.replying);
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.ReplyStartTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.passReplyData);
				appReplyDetail.setReplystageto(EReplyStage.replying);
				getSO().update(appReply, appReplyDetail);
			}
		}
		for (AppReply appReply : list4) {
			String replyTimeString = appReply.getReplytimeok();
			String replytime = replyTimeString.substring(0, replyTimeString.indexOf(" "));
			DateTime time = DateTime.parse(replytime);
			AppReplyDetail appReplyDetail = new AppReplyDetail();
			if (time.isAfter(DateTime.now().plusDays(1))) {
				appReply.setReplystage(EReplyStage.endReply);
				appReplyDetail.setAppreplyuid(appReply.getUid());
				appReplyDetail.setUseruid(appReply.getUseruid());
				appReplyDetail.setName(SysConf.ReplyEndTitle);
				appReplyDetail.setReplystagefrom(EReplyStage.replying);
				appReplyDetail.setReplystageto(EReplyStage.endReply);
				getSO().update(appReply, appReplyDetail);
			}
		}
		return true;
	}
	
	/**
	 * 得到答辩的专家
	 * @param appreplyuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午6:53:57
	 * @return List<User>
	 */
	public static List<User> getReplyExperts(String appreplyuid) {
		List<AppReplyExpert> appReplyExperts = getAppReplyExpertSO().getList(appreplyuid, false);
		List<User> list = new ArrayList<User>();
		for (AppReplyExpert appReplyExpert : appReplyExperts) {
			Expert expert = getExpertSO().get(appReplyExpert.getExpertuid());
			if (expert != null) {
				User user = getUserSO().get(expert.getUseruid());
				if (user != null) {
					list.add(user);
				}
			}
		}
		return list;
	}
	
	/**
	 * 增加会议室成员
	 * @param user
	 * @param conference
	 * @param confemembertype
	 * @return
	 * @author liubin
	 * @createtime 2016年11月24日下午2:12:00
	 * @return boolean
	 */
	public static boolean addConfUser(User user, Conference conference, EConfemember confemembertype) {
		ConfeMember confemember = new ConfeMember();
		confemember.setUseruid(user.getUid());
		confemember.setConfuid(conference.getUid());
		confemember.setConfsn(conference.getSn());
		confemember.setCid(conference.getCid());
		confemember.setPkuid(conference.getTypeuid());
		confemember.setPkname(conference.getTypename());
		confemember.setUsertype(confemembertype);
		confemember.setUserid(conference.getCid());
		confemember.setUsername(user.getShowname());
		confemember.setUseralias(user.getShowname());
		confemember.setEmail(user.getEmail());
		confemember.setPhone(user.getPhone());
		confemember.setType(conference.getType());
		getConfeMemberSO().add(confemember);
		conference.setExpectJoinCount(conference.getExpectJoinCount() + 1);
		getConferenceSO().update(conference);
		return getConferenceSO().update(conference);
	}
}
