package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.manager.helper.ExpertHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 专家管理
 * @author XieMing
 * @date 2016-10-22 下午5:58:10
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ExpertAdminer extends BaseController {

	@Autowired
	ExpertSO so;
	@Autowired
	UserSO userSO;
	
	/**
	 * 跳转到专家管理页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-22 下午6:00:17
	 */
	@RequestMapping(value = "/manager/expert", method = RequestMethod.GET)
	public String expert(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.expert.name());
		model.put(ViewKeyDict.channelname, EChannel.expert.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.types, EExpert.getValueAndChinese());
		model.put(ViewKeyDict.levels, EExpertLevel.getValueAndChinese());
		model.put(ViewKeyDict.allcount, so.getCount());
		model.put(ViewKeyDict.count, so.getCount(EExpertLevel.expert));
		model.put(ViewKeyDict.highcount, so.getCount(EExpertLevel.highExpert));
		model.put(ViewKeyDict.specialcount, so.getCount(EExpertLevel.specialExpert));
		model.put(ViewKeyDict.quitcount, so.getCount(EStatus.end));
		model.put(ViewKeyDict.fullcount, so.getFullCount(SysConf.FullExpertCount));
		return "doexpert";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-22 下午6:02:10
	 */
	@RequestMapping(value = { "/manager/ajax/expert/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> expert_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ExpertHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 编辑专家
	 * @param expert
	 * @param uid
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-11-3 上午11:32:37
	 */
	@RequestMapping(value = "/manager/ajax/expert/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managervideo_edit(Expert expert, String uid, String dopwd, HttpServletRequest request) {
		if(!AdminHP.isDopwd(dopwd)) {
			return result(MsgKeyDict.dopwdIsWrong);
		}
		Expert expert1 = so.get(uid);
		expert1.setStatus(expert.getStatus());
		expert1.setChaircount(expert.getChaircount());
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.expert.toInt());
		adminLog.setDataid(expert.getUid());
		adminLog.setDesc("");
		adminLog.setData(EAdminLog.updateExpertStatus.getName());
		adminLog.setOperation(EAdminLog.updateExpertStatus.getName());
		so.update(expert1, adminLog);
		return success();
	}
	
	/**
	 * 更改专家级别
	 * @param uid
	 * @param level
	 * @param reason
	 * @return
	 * @author XieMing
	 * 2016-11-3 下午1:56:34
	 */
	@RequestMapping(value = "/manager/ajax/expert/setlevel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> expert_level(String uid, EExpertLevel level, String dopwd, String reason) {
		if(!AdminHP.isDopwd(dopwd)) {
			return result(MsgKeyDict.dopwdIsWrong);
		}
		Expert expert = so.get(uid);
		User user = userSO.get(expert.getUseruid());
		Expert oldexpert = so.getOne(user.getUid(), expert.getMajor(), level);
		if(oldexpert != null) {
			return result(MsgKeyDict.editFailed);
		}
		Expert newexpert = new Expert();
		ECertificate type = null;
		if(level.equals(EExpertLevel.expert)) {
			type = ECertificate.expert;
		} else if(level.equals(EExpertLevel.highExpert)) {
			type = ECertificate.seniorExpert;
		} else if(level.equals(EExpertLevel.specialExpert)) {
			type = ECertificate.superExpert;
		}
		Certificate certificate = new Certificate(user.getUid(), AutoIncrHP.getCertificatesn(), newexpert.getUid(), expert.getMajor(), type);
		UserLevel userLevel = new UserLevel(user.getUid(), expert.getMajor(), Integer.valueOf(expert.getLevel().toString()), Integer.valueOf(level.ordinal()), EUser.expert, ELevelChannel.specialExpert);
		userLevel.setCertificateuid(certificate.getUid());
		expert.setUid(newexpert.getUid());
		expert.setSn(AutoIncrHP.getTCJudgeSn(user));
		expert.setLevel(level);
		expert.setChaircount(0);
		expert.setComplaintcount(0);
		expert.setDesc(reason);
		expert.setType(EExpert.specialExpert);
		expert.setFile(null);
		expert.setCreatetime(DateTime.now());
		expert.setCertificateuid(certificate.getUid());
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.expert.toInt());
		adminLog.setDataid(expert.getUid());
		adminLog.setDesc(reason);
		adminLog.setData(EAdminLog.updateExpertStatus.getName());
		adminLog.setOperation(EAdminLog.updateExpertStatus.getName());
		MsgHP.sendExpertLevel(expert.getMajor(), AdminHP.getAdminUid(), expert.getUseruid(), level);
		so.add(expert, certificate, adminLog, userLevel, null);
		return success();
	}

}
