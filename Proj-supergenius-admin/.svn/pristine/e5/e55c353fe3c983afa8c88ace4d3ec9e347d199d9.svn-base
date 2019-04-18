package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Complaint;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EVideoFrom;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.manager.service.ComplaintSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.manager.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 举报管理HP
 * @author liubin
 * @date 2016-11-6 下午6:54:52 
 */
public class ComplaintHP extends BaseHP {

	private static ComplaintSO so;
	private static UserSO userSO;
	private static PkScheduleSO pkScheduleSO;
	private static AppReplySO appReplySO;
	private static CertificateSO certificateSO;
	private static VideoSO videoSO;
	private static JudgeSO judgeSO;
	private static ExpertSO expertSO;
	
	private static ComplaintSO getSO() {
		if (so == null) {
			so = spring.getBean(ComplaintSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static PkScheduleSO getPkScheduleSO() {
		if (pkScheduleSO == null) {
			pkScheduleSO = spring.getBean(PkScheduleSO.class);
		}
		return pkScheduleSO;
	}
	
	private static AppReplySO getAppReplySO() {
		if (appReplySO == null) {
			appReplySO = spring.getBean(AppReplySO.class);
		}
		return appReplySO;
	}
	
	private static CertificateSO getCertificateSO() {
		if (certificateSO == null) {
			certificateSO = spring.getBean(CertificateSO.class);
		}
		return certificateSO;
	}
	
	private static VideoSO getVideoSO() {
		if (videoSO == null) {
			videoSO = spring.getBean(VideoSO.class);
		}
		return videoSO;
	}
	
	private static JudgeSO getJudgeSO() {
		if (judgeSO == null) {
			judgeSO = spring.getBean(JudgeSO.class);
		}
		return judgeSO;
	}
	
	private static ExpertSO getExpertSO() {
		if (expertSO == null) {
			expertSO = spring.getBean(ExpertSO.class);
		}
		return expertSO;
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午6:56:11
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.fromusername + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.tousername + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.tousertype, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString());
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		List<Complaint> list = getSO().searchList(map);
		for (Complaint complaint : list) {
			User fromUser =  getUserSO().get(complaint.getFromuseruid());
			User toUser = getUserSO().get(complaint.getTouseruid());
			complaint.setFromuser(fromUser);
			complaint.setTouser(toUser);
			if (complaint.getTousertype() == EUser.judgement) {
				PKSchedule pkSchedule = getPkScheduleSO().get(complaint.getRefuid());
				Judge judge = null;
				if (pkSchedule != null) {
					judge = getJudgeSO().getOne(complaint.getTouseruid(), pkSchedule.getMajor());
					complaint.setReflevelname(pkSchedule.getPkuserlevel().getName());
					complaint.setReflevelname2(EUser.judgement.getTypeName());
					complaint.setRefurl(WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()));
				}
				if (judge != null) {
					complaint.setRefcount(judge.getJudgecount());
				}
			} else if (complaint.getTousertype() == EUser.expert) {
				AppReply appReply = getAppReplySO().get(complaint.getRefuid());
				if (appReply != null) {
					complaint.setReflevelname(appReply.getMajorlevel().getName());
					if (StrUtil.isNotEmpty(complaint.getRefuid())) {
						if (getVideoSO().getOne(appReply.getUid(), EVideoFrom.reply) != null) {
							complaint.setRefurl(WebConf.baseManagerPath + String.format(SysConf.VideoDetailHref, appReply.getUid()));
						}
					}
					ECertificate expertLevel = getDegree(appReply.getCertificated());
					Expert expert = null;
					if (expertLevel == ECertificate.RMBA) {
						expert = getExpertSO().getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.expert);
					} else if (expertLevel == ECertificate.SMBA) {
						expert = getExpertSO().getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.highExpert);
					} else if (expertLevel == ECertificate.TMBA) {
						expert = getExpertSO().getOne(complaint.getTouseruid(), appReply.getMajor(), EExpertLevel.specialExpert);
					}
					if (expert != null) {
						complaint.setRefcount(expert.getChaircount());
						complaint.setReflevelname2(expert.getLevel().getName());
					}
				}
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 根据appReply已获取证书的uid获取申请的学位的证书类型
	 * @param uid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7上午10:28:28
	 * @return ECertificate
	 */
	public static ECertificate getDegree(String uid) {
		if(StrUtil.isEmpty(uid)) {
			return ECertificate.RMBA;
		} else {
			Certificate certificate = getCertificateSO().get(uid);
			ECertificate type = certificate.getType();
			if(ECertificate.RMBA.equals(type)) {
				return ECertificate.SMBA;
			} else {
				return ECertificate.TMBA;
			}
		}
	}
	
	/**
	 * 根据用户uid和用户类型获得该次举报是否有惩罚
	 * @param useruid
	 * @param typeUser
	 * @return
	 * @author liubin
	 * @createtime 2016-11-7下午7:42:18
	 * @return boolean
	 */
	public static boolean isGetPunishOfComplaint(String useruid, EMajor major, EUser typeUser) {
		Pager pager = Pager.getNewInstance(1, null);
		List<EPKStage> stages = EPKStage.getPKOver();
		int count = 0;
		Judge judge = getJudgeSO().getOne(useruid, major);
		if (judge != null) {
			if (judge.getComplaintcount() < SysConf.JudgeComplaintCountGetPunish) {
				return false;
			}
			List<PKSchedule> list = getPkScheduleSO().getListByAllJudgementuid(pager, useruid, major, stages, judge.getCreatetime());
			if (list != null && list.size() > 0) {
				for (PKSchedule pkSchedule : list) {
					if (getSO().getOne(useruid, pkSchedule.getUid(), typeUser) != null) {
						count++;
					}
				}
			}
		}
		if (count >= SysConf.JudgeComplaintCountGetPunish) {
			return true;
		}
		return false;
	}
}
