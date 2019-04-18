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
import com.supergenius.web.admin.manager.helper.AppJudgeHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.AppJudgement;
import com.supergenius.xo.manager.entity.AppJudgementDetail;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppJudgementDetailSO;
import com.supergenius.xo.manager.service.AppJudgementSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 裁判申请管理
 * @author XieMing
 * @date 2016-10-21 下午4:35:41
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AppJudgeAdminer extends BaseController {

	@Autowired
	AppJudgementSO so;
	@Autowired
	AppJudgementDetailSO appJudgementDetailSO;
	@Autowired
	UserSO userSO;
	@Autowired
	JudgeSO judgeSO;

	/**
	 * 跳转到裁判申请管理页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:37:01
	 */
	@RequestMapping(value = "/manager/appjudge", method = RequestMethod.GET)
	public String appjudge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.appjudge.name());
		model.put(ViewKeyDict.channelname, EChannel.appjudge.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.stages, EAppJudgementStage.getValueAndChinese());
		model.put(ViewKeyDict.appcount, so.getCount());
		model.put(ViewKeyDict.passcount, so.getCount(EAppJudgementStage.passInterview));
		return "doappjudge";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:37:32
	 */
	@RequestMapping(value = { "/manager/ajax/appjudge/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> appjudge_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AppJudgeHP.query(model);
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
	 * 2016-11-4 上午10:49:12
	 */
	@RequestMapping(value = "/manager/appjudge/download", method = RequestMethod.GET)
	public void appjudge_download(HttpServletResponse response, HttpServletRequest request, String name, String suffix, String path) throws Exception {
		FileUtil.download(name, path + name + suffix, request, response);
	}
	
	/**
	 * 审核裁判申请
	 * @param uid
	 * @param result
	 * @param reason
	 * @return
	 * @author XieMing
	 * 2016-11-1 下午4:57:41
	 */
	@RequestMapping(value = "/manager/ajax/appjudge/setstage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_stage(String uid, String result, String reason) {
		AppJudgement appJudgement = so.get(uid);
		if(!appJudgement.getStage().equals(EAppJudgementStage.init)) {
			return result(MsgKeyDict.editFailed);
		}
		User user = userSO.get(appJudgement.getUseruid());
		AppJudgementDetail appJudgementDetail = null;
		if(result.equals("1")) {
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), null, appJudgement.getStage(), EAppJudgementStage.passInit);
			appJudgement.setStage(EAppJudgementStage.passInit);
			MsgHP.sendAppJudgePass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname());
		} else {
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), null, appJudgement.getStage(), EAppJudgementStage.overInit);
			appJudgement.setStage(EAppJudgementStage.overInit);
			MsgHP.sendAppJudgeNoPass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason);
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appJudgement.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppJudgeStage.getName());
		adminLog.setOperation(EAdminLog.updateAppJudgeStage.getName());
		appJudgementDetail.setAdminuid(AdminHP.getAdminUid());
		appJudgementDetail.setDesc(reason);
		so.updateStage(appJudgement, appJudgementDetail, adminLog);
		return success();
	}
	
	/**
	 * 审核裁判题目
	 * @param uid
	 * @param providetime
	 * @param reason
	 * @param passcount
	 * @param filedata
	 * @return
	 * @author XieMing
	 * 2016-11-2 下午5:15:43
	 */
	@RequestMapping(value = "/manager/ajax/appjudge/setcheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_check(String uid, String providetime, String reason, int passcount, @RequestParam MultipartFile filedata) {
		AppJudgement appJudgement = so.get(uid);
		User user = userSO.get(appJudgement.getUseruid());
		if(!appJudgement.getStage().equals(EAppJudgementStage.checkQuestion)) {
			return result(MsgKeyDict.editFailed);
		}
		String path = null;
		if(filedata.getSize() != 0) {
			path = FileUtil.uploadFile(filedata, SysConf.AppJudgeFilePath);
		}
		AppJudgementDetail appJudgementDetail = null;
		appJudgement.setFile2(path);
		if(appJudgement.getTopiccount() + passcount < SysConf.ApplyJudgeQuestionCount) {
			appJudgement.setDescto(reason);
			appJudgement.setTopiccount(appJudgement.getTopiccount() + passcount);
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), path, appJudgement.getStage(), EAppJudgementStage.overCheck);
			appJudgement.setStage(EAppJudgementStage.overCheck);
			MsgHP.sendCheckNoPass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason, SysConf.ApplyJudgeQuestionCount - appJudgement.getTopiccount());
		} else {
			appJudgement.setProvidetime(providetime);
			appJudgement.setTopiccount(appJudgement.getTopiccount() + passcount);
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), path, appJudgement.getStage(), EAppJudgementStage.passCheck);
			appJudgement.setStage(EAppJudgementStage.passCheck);
			MsgHP.sendCheckPass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), providetime);
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appJudgement.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppJudgeStage.getName());
		adminLog.setOperation(EAdminLog.updateAppJudgeStage.getName());
		appJudgementDetail.setAdminuid(AdminHP.getAdminUid());
		appJudgementDetail.setDesc(reason);
		so.updateStage(appJudgement, appJudgementDetail, adminLog);
		return success();
	}

	/**
	 * 设置裁判申请结果
	 * @param uid
	 * @param result
	 * @param reason
	 * @param filedata
	 * @return
	 * @author XieMing
	 * 2016-11-1 下午7:55:54
	 */
	@RequestMapping(value = "/manager/ajax/appjudge/setresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appjudge_result(String uid, String result, String reason, @RequestParam MultipartFile filedata) {
		AppJudgement appJudgement = so.get(uid);
		/*if(!appJudgement.getStage().equals(EAppJudgementStage.interview)) {
			return result(MsgKeyDict.editFailed);
		}*/
		AppJudgementDetail appJudgementDetail = null;
		String path = null;
		if(filedata.getSize() != 0){
			path = FileUtil.uploadFile(filedata, SysConf.AppJudgeFilePath);
		}
		appJudgement.setFile2(path);
		User user = userSO.get(appJudgement.getUseruid());
		if(result.equals("1")) {
			user.plusType(EUser.judgement);
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), path, appJudgement.getStage(), EAppJudgementStage.passInterview);
			appJudgement.setStage(EAppJudgementStage.passInterview);
			Judge judge = new Judge(appJudgement.getUseruid(), AutoIncrHP.getJudgeSn(user), EJudge.judgment, appJudgement.getMajor());
			Certificate certificate = new Certificate(user.getUid(), AutoIncrHP.getCertificatesn(), appJudgement.getUid(), appJudgement.getMajor(), ECertificate.judge);
			UserLevel userLevel = new UserLevel(user.getUid(), appJudgement.getMajor(), 0, EUser.judgement, ELevelChannel.appJudgement);
			MsgHP.sendJudgeInterviewPass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname());
			if(judge != null && certificate != null && userLevel != null) {
				judgeSO.add(judge, certificate, userLevel, user);
			}
		} else {
			appJudgement.setDescto(reason);
			appJudgementDetail = new AppJudgementDetail(appJudgement.getUseruid(), appJudgement.getUid(), path, appJudgement.getStage(), EAppJudgementStage.overInterview);
			appJudgement.setStage(EAppJudgementStage.overInterview);
			MsgHP.sendJudgeInterviewNoPass(appJudgement.getMajor(), AdminHP.getAdminUid(), user.getUid(), user.getShowname(), reason);
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.appjudge.toInt());
		adminLog.setDataid(appJudgement.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateAppJudgeStage.getName());
		adminLog.setOperation(EAdminLog.updateAppJudgeStage.getName());
		appJudgementDetail.setAdminuid(AdminHP.getAdminUid());
		appJudgementDetail.setDesc(reason);
		so.updateStage(appJudgement, appJudgementDetail, adminLog);
		return success();
	}
	
}
