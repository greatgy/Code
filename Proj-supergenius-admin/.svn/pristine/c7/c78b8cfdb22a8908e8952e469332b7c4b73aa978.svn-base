package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.supergenius.web.admin.manager.helper.JudgeHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.JudgeSO;

/**
 * 裁判管理
 * @author XieMing
 * @date 2016-10-21 上午11:41:58
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class JudgeAdminer extends BaseController {

	@Autowired
	JudgeSO so;

	/**
	 * 跳转到裁判管理页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 上午11:43:41
	 */
	@RequestMapping(value = "/manager/judge", method = RequestMethod.GET)
	public String judge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.judge.name());
		model.put(ViewKeyDict.channelname, EChannel.judge.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.types, EJudge.getValueAndChinese());
		model.put(ViewKeyDict.alljudge, so.getCount());
		model.put(ViewKeyDict.judge, so.getCount(EJudge.judgment));
		model.put(ViewKeyDict.fulljudge, so.getFullJudgeCount(SysConf.FullJudgeCount));
		model.put(ViewKeyDict.specialjudge, so.getCount(EJudge.specialJudgement));
		model.put(ViewKeyDict.invitejudge, so.getCount(EJudge.specialInviteJudgement));
		model.put(ViewKeyDict.quitjudge, so.getCount(EStatus.end));
		return "dojudge";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-21 上午11:45:16
	 */
	@RequestMapping(value = { "/manager/ajax/judge/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> judge_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = JudgeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 编辑裁判
	 * @param judge
	 * @param uid
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-11-1 下午2:08:07
	 */
	@RequestMapping(value = "/manager/ajax/judge/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managervideo_edit(Judge judge, String uid, String dopwd, HttpServletRequest request) {
		if(!AdminHP.isDopwd(dopwd)) {
			return result(MsgKeyDict.dopwdIsWrong);
		}
		Judge judge1 = so.get(uid);
		judge1.setStatus(judge.getStatus());
		judge1.setJudgecount(judge.getJudgecount());
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.judge.toInt());
		adminLog.setDataid(judge.getUid());
		adminLog.setDesc("");
		adminLog.setData(EAdminLog.updateJudgeStatus.getName());
		adminLog.setOperation(EAdminLog.updateJudgeStatus.getName());
		so.update(judge1, adminLog);
		return success();
	}
	
}
