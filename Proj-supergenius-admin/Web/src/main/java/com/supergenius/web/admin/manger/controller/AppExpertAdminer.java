package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.manager.helper.AppExpertHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.AppExpert;
import com.supergenius.xo.manager.entity.AppExpertDetail;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppExpertSO;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 专家申请管理
 * @author XieMing
 * @date 2016-10-23 下午2:28:17
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AppExpertAdminer extends BaseController {

	@Autowired
	AppExpertSO so;
	@Autowired
	UserSO userSO;
	@Autowired
	ExpertSO expertSO;

	/**
	 * 跳转到专家申请管理页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:37:01
	 */                      
	@RequestMapping(value = "/manager/appexpert", method = RequestMethod.GET)
	public String appexpert(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.appexpert.name());
		model.put(ViewKeyDict.channelname, EChannel.appexpert.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.stages, EAppExpertStage.getValueAndChinese());
		model.put(ViewKeyDict.levels, EExpertLevel.getValueAndChinese());
		model.put(ViewKeyDict.appcount, so.getCount());
		model.put(ViewKeyDict.count, so.getCount(EExpertLevel.expert));
		model.put(ViewKeyDict.highcount, so.getCount(EExpertLevel.highExpert));
		model.put(ViewKeyDict.specialcount, so.getCount(EExpertLevel.specialExpert));
		return "doappexpert";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:37:32
	 */
	@RequestMapping(value = { "/manager/ajax/appexpert/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> appexpert_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AppExpertHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param request
	 * @param name
	 * @param suffix
	 * @param path
	 * @throws Exception
	 * @author XieMing
	 * 2016-11-4 上午10:49:19
	 */
	@RequestMapping(value = "/manager/appexpert/download", method = RequestMethod.GET)
	public void appexpert_download(HttpServletResponse response, HttpServletRequest request, String name, String suffix, String path) throws Exception {
		FileUtil.download(name, path + name + suffix, request, response);
	}
	
	/**
	 * 审核专家申请
	 * @param uid
	 * @param result
	 * @param reason
	 * @return
	 * @author XieMing
	 * 2016-11-4 下午4:18:46
	 */
	@RequestMapping(value = "/manager/ajax/appexpert/setstage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_stage(String uid, String result, String reason) {
		AppExpert appExpert = so.get(uid);
		if(!appExpert.getStage().equals(EAppExpertStage.applyCheck)) {
			return result(MsgKeyDict.editFailed);
		}
		User user = userSO.get(appExpert.getUseruid());
		AppExpertDetail appExpertDetail = null;
		if(result.equals("1")) {
			appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), EAppExpertStage.applyPass.getName(), appExpert.getStage(), EAppExpertStage.applyPass);
			appExpert.setStage(EAppExpertStage.applyPass);
			MsgHP.sendAppExpertPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(),appExpert.getLevelto());
		} else {
			appExpertDetail = new AppExpertDetail(appExpert.getUseruid(), appExpert.getUid(), EAppExpertStage.applyNoPass.getName(), appExpert.getStage(), EAppExpertStage.applyNoPass);
			appExpert.setStage(EAppExpertStage.applyNoPass);
			MsgHP.sendAppExpertNoPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason,appExpert.getLevelto());
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appExpert.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppExpertStage.getName());
		adminLog.setOperation(EAdminLog.updateAppExpertStage.getName());
		appExpertDetail.setAdminuid(AdminHP.getAdminUid());
		appExpertDetail.setDesc(reason);
		so.updateStage(appExpert, appExpertDetail, adminLog);
		return success();
	}
	
	/**
	 * 审核专家题目
	 * @param uid
	 * @param providetime
	 * @param reason
	 * @param passcount
	 * @param filedata
	 * @return
	 * @author XieMing
	 * 2016-11-2 下午5:15:43
	 */
	@RequestMapping(value = "/manager/ajax/appexpert/setcheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_check(String uid, String providetime, String reason, String result, @RequestParam MultipartFile filedata) {
		AppExpert appExpert = so.get(uid);
		User user = userSO.get(appExpert.getUseruid());
		if(!appExpert.getStage().equals(EAppExpertStage.reportCheck)) {
			return result(MsgKeyDict.editFailed);
		}
		String path = null;
		if(filedata.getSize() != 0){
			path = FileUtil.uploadFile(filedata, SysConf.AppExpertFilePath);
		}
		appExpert.setFile2(path);
		AppExpertDetail appExpertDetail = null;
		if(result.equals("1")) {
			appExpert.setProvidetime(providetime);
			appExpertDetail = new AppExpertDetail(user.getUid(), appExpert.getUid(), appExpert.getStage(), EAppExpertStage.reportPass, path);
			appExpert.setStage(EAppExpertStage.reportPass);
			MsgHP.sendExpertCheckPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), providetime, appExpert.getLevelto());
		} else {
			appExpertDetail = new AppExpertDetail(user.getUid(), appExpert.getUid(), appExpert.getStage(), EAppExpertStage.reportNoPass, path);
			appExpert.setStage(EAppExpertStage.reportNoPass);
			appExpertDetail.setDesc(reason);
			MsgHP.sendExpertCheckNoPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason, appExpert.getLevelto());
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appExpert.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppExpertStage.getName());
		adminLog.setOperation(EAdminLog.updateAppExpertStage.getName());
		appExpertDetail.setAdminuid(AdminHP.getAdminUid());
		appExpertDetail.setDesc(reason);
		so.updateStage(appExpert, appExpertDetail, adminLog);
		return success();
	}

	/**
	 * 设置专家申请结果
	 * @param uid
	 * @param result
	 * @param reason
	 * @param filedata
	 * @return
	 * @author XieMing
	 * 2016-11-1 下午7:55:54
	 */
	@RequestMapping(value = "/manager/ajax/appexpert/setresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_result(String uid, String result, String reason, @RequestParam MultipartFile filedata) {
		AppExpert appExpert = so.get(uid);
		/*if(!appExpert.getStage().equals(EAppExpertStage.interviewed)) {
			return result(MsgKeyDict.editFailed);
		}*/
		AppExpertDetail appExpertDetail = null;
		String path = null;
		if(filedata.getSize() != 0){
			path = FileUtil.uploadFile(filedata, SysConf.AppExpertFilePath);
		}
		appExpert.setFile2(path);
		User user = userSO.get(appExpert.getUseruid());
		if(result.equals("1")) {
			user.plusType(EUser.expert);
			appExpertDetail = new AppExpertDetail(user.getUid(), appExpert.getUid(), appExpert.getStage(), EAppExpertStage.interviewPass, path);
			appExpert.setStage(EAppExpertStage.interviewPass);
			appExpertDetail.setDesc(reason);
			ECertificate type = null;
			if(appExpert.getLevelfrom().equals(EExpertLevel.expert)) {
				type = ECertificate.seniorExpert;
			} else if(appExpert.getLevelfrom().equals(EExpertLevel.highExpert)) {
				type = ECertificate.superExpert;
			} else {
				type = ECertificate.expert;
			}
			Certificate certificate = new Certificate(user.getUid(), AutoIncrHP.getCertificatesn(), appExpert.getUid(), appExpert.getMajor(), type);
			Expert expert = new Expert(user.getUid(), AutoIncrHP.getExpert(user), appExpert.getMajor(), appExpert.getLevelto(), appExpert.getDesc(), EExpert.expert, certificate.getUid(), path);
			UserLevel userLevel = new UserLevel(user.getUid(), appExpert.getMajor(), Integer.valueOf(appExpert.getLevelfrom().toString()), Integer.valueOf(appExpert.getLevelto().toString()), EUser.expert, ELevelChannel.appExpert);
			MsgHP.sendExpertInterviewPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), appExpert.getLevelto());
			if(expert != null && certificate != null && userLevel != null) {
				expertSO.add(expert, certificate, null, userLevel, user);
			}
		} else {
			appExpertDetail = new AppExpertDetail(user.getUid(), appExpert.getUid(), appExpert.getStage(), EAppExpertStage.interviewNoPass, path);
			appExpert.setStage(EAppExpertStage.interviewNoPass);
			appExpertDetail.setDesc(reason);
			MsgHP.sendExpertInterviewNoPass(appExpert.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason, appExpert.getLevelto());
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appExpert.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppExpertStage.getName());
		adminLog.setOperation(EAdminLog.updateAppExpertStage.getName());
		appExpertDetail.setAdminuid(AdminHP.getAdminUid());
		appExpertDetail.setDesc(reason);
		so.updateStage(appExpert, appExpertDetail, adminLog);
		return success();
	}
	
}
