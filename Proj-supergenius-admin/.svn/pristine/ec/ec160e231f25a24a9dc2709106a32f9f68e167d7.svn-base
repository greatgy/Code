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
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.service.AppExpertDetailSO;
import com.supergenius.xo.manager.service.AppExpertSO;
import com.supergenius.xo.manager.service.CertificateSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 专家申请管理HP
 * @author XieMing
 * @date 2016-10-23 下午2:51:19
 */
public class AppExpertHP extends BaseHP {

	private static AppExpertSO so;
	private static UserSO userSO;
	private static CertificateSO certificateSO;
	private static AppExpertDetailSO appExpertDetailSO;
	
	private static AppExpertSO getSO() {
		if (so == null) {
			so = spring.getBean(AppExpertSO.class);
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
	
	private static AppExpertDetailSO getAppExpertDetailSO() {
		if (appExpertDetailSO == null) {
			appExpertDetailSO = spring.getBean(AppExpertDetailSO.class);
		}
		return appExpertDetailSO;
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
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.level))) {
			map.put(MapperDict.levelto, model.get(ViewKeyDict.level).toString());
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<AppExpert> list = getSO().getList(map);
		for (AppExpert appExpert : list) {
			User user = getUserSO().get(appExpert.getUseruid());
			if(user != null) {
				appExpert.setUserSn(user.getUsersn());
				appExpert.setUserName(user.getShowname());
			}
			Certificate certificate = getCertificateSO().getOne(appExpert.getUseruid(), appExpert.getMajor());
			if(certificate != null) {
				appExpert.setCertificate(certificate.getType().getName());
			}
			List<String> fileList = getAppExpertDetailSO().getFileList(appExpert.getUid(), EAppExpertStage.getFileList());
			List<String> fileList2 = getAppExpertDetailSO().getFileList(appExpert.getUid(), EAppExpertStage.getFileList2());
			appExpert.setFileList(fileList);
			appExpert.setFile2List(fileList2);
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
	public static boolean updateAppExpertStage() {
		List<AppExpert> list = getSO().getList(EAppExpertStage.interviewing);
		AppExpertDetail appExpertDetail = null;
		for (AppExpert appExpert : list) {
			appExpert.setStage(EAppExpertStage.interviewed);
			appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), null, EAppExpertStage.interviewing, EAppExpertStage.interviewed);
			getSO().updateStage(appExpert, appExpertDetail, null);
		}
		list = getSO().getList(EAppExpertStage.reportPass);
		for (AppExpert appExpert : list) {
			DateTime.now().plusDays(1);
			DateTime timeok = DateUtil.parse(appExpert.getProvidetime());
			if(DateTime.now().isAfter(timeok) && DateTime.now().isBefore(timeok.plusDays(1))) {
				appExpert.setStage(EAppExpertStage.interviewing);
				appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), null, EAppExpertStage.reportPass, EAppExpertStage.interviewing);
				getSO().updateStage(appExpert, appExpertDetail, null);
			}
		}
		return true;
	}
	
}
