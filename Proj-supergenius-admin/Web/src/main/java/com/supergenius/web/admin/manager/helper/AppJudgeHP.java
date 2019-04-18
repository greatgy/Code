package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.entity.AppJudgement;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.service.AppJudgementDetailSO;
import com.supergenius.xo.manager.service.AppJudgementSO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 裁判申请HP
 * @author XieMing
 * @date 2016-10-21 下午4:38:39
 */
public class AppJudgeHP extends BaseHP {

	private static AppJudgementSO so;
	private static UserSO userSO;
	private static CertificateSO certificateSO;
	private static AppJudgementDetailSO appJudgementDetailSO;
	
	private static AppJudgementSO getSO() {
		if (so == null) {
			so = spring.getBean(AppJudgementSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static CertificateSO getCertificateSO() {
		if (certificateSO == null) {
			certificateSO = spring.getBean(CertificateSO.class);
		}
		return certificateSO;
	}
	
	private static AppJudgementDetailSO getAppJudgementDetailSO() {
		if (appJudgementDetailSO == null) {
			appJudgementDetailSO = spring.getBean(AppJudgementDetailSO.class);
		}
		return appJudgementDetailSO;
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:40:06
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keyword))) {
			map.put(MapperDict.showname + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.keyword).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.stage))) {
			map.put(MapperDict.stage, model.get(ViewKeyDict.stage).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<AppJudgement> list = getSO().getList(map);
		for (AppJudgement appJudgement : list) {
			appJudgement.setUserSn(getUserSO().get(appJudgement.getUseruid()).getUsersn());
			Certificate certificate = getCertificateSO().getOne(appJudgement.getUseruid(), appJudgement.getMajor());
			if(certificate != null) {
				appJudgement.setCertificate(certificate.getType().getName());
			}
			List<String> fileList = getAppJudgementDetailSO().getFileList(appJudgement.getUid(), EAppJudgementStage.getNeedFileList());
			List<String> fileList2 = getAppJudgementDetailSO().getFileList(appJudgement.getUid(), EAppJudgementStage.getNeedFileList2());
			appJudgement.setFileList(fileList);
			appJudgement.setFile2List(fileList2);
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 触发器自动更新裁判申请状态
	 * @return
	 * @author XieMing
	 * 2016-11-14 下午4:02:15
	 */
	public static boolean updateAppJudgeStage() {
		List<AppJudgement> list = getSO().getList(EAppJudgementStage.interviewing);
		AppJudgementDetail appJudgementDetail = null;
		for (AppJudgement appJudgement : list) {
			appJudgement.setStage(EAppJudgementStage.interview);
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), null, EAppJudgementStage.interviewing, EAppJudgementStage.interview);
			getSO().updateStage(appJudgement, appJudgementDetail, null);
		}
		list = getSO().getList(EAppJudgementStage.passCheck);
		for (AppJudgement appJudgement : list) {
			DateTime.now().plusDays(1);
			DateTime timeok = DateUtil.parse(appJudgement.getProvidetime());
			if(DateTime.now().isAfter(timeok) && DateTime.now().isBefore(timeok.plusDays(1))) {
				appJudgement.setStage(EAppJudgementStage.interviewing);
				appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), null, EAppJudgementStage.passCheck, EAppJudgementStage.interviewing);
				getSO().updateStage(appJudgement, appJudgementDetail, null);
			}
		}
		return true;
	}
	
}
