package com.supergenius.web.admin.user.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.user.helper.ConsumerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Certificate;
import com.supergenius.xo.manager.entity.Expert;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.ExpertSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.enums.EUserAisle;
import com.supergenius.xo.user.enums.EUserChannel;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 用户列表controller
 * 
 * @author XieMing
 * @date 2016-5-13 下午6:40:45
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ConsumerAdminer extends BaseController {

	@Autowired
	UserSO so;
	@Autowired
	UserInfoSO userInfoSO;

	@Autowired
	AdminLogSO adminLogSO;
	
	@Autowired
	AdminSO adminSO;
	
	@Autowired
	JudgeSO judgeSO;
	
	@Autowired
	ExpertSO expertSO;

	/**
	 * 跳转到列表页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-15 下午4:23:35
	 */
	@RequestMapping(value = "/user/consumer", method = RequestMethod.GET)
	public String consumer(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.consumer.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.consumer, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.user.name());
		Map<String, String> map = new HashMap<String, String>();
		for (EUserAisle item : EUserAisle.values()) { // 加载用户来自渠道
			map.put(item.toString(), item.getName());
		}
		Map<String, String> map1 = new LinkedHashMap<String, String>();
		// 加载用户所有状态
		map1.put(EStatus.start.toString(), EStatus.start.getName());
		map1.put(EStatus.wait.toString(), EStatus.wait.getName());
		map1.put(EStatus.disable.toString(), EStatus.disable.getName());
		model.put(ViewKeyDict.map, map);
		model.put(ViewKeyDict.map1, map1);
		model.put(ViewKeyDict.totalmember, so.getCount());
		return "doconsumer";
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-15 下午4:23:26
	 */
	@RequestMapping(value = { "/user/ajax/consumer/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> consumer_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ConsumerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 特批用户
	 * 
	 * @param uid
	 * @return
	 * @author XieMing 2016-5-17 下午12:21:29
	 */
	@RequestMapping(value = "/user/ajax/consumer/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_edit(String uid, String reason) {
		User user = so.get(uid);
		user.setDataMap_reason(reason);
		user.setChannelfrom(EUserChannel.specialuser);
		user.setStatus(EStatus.enable);
		user.setType(Integer.parseInt(EUser.user.toString()));
		//if (so.update(user) && ConsumerHP.sendEmail(user, EContent.email_special)) {
		if (so.update(user)) {
			return success();
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 添加特邀嘉宾
	 * 
	 * @param user
	 * @param reason
	 * @return
	 * @author XieMing 2016-5-17 下午12:02:17
	 */
	@RequestMapping(value = "/user/ajax/consumer/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_add(User user, String reason) {
		if (so.isExistEmail(user.getEmail())) {
			return result(MsgKeyDict.exist);
		}
		if (StringUtils.isEmpty(user.getShowname()) || ViewKeyDict.chinese.equals(user.getShowname())) {
			user.setShowname(user.getName());
		} else {
			user.setShowname(user.getNickname());
		}
		user.setUsersn(ConsumerHP.getVONUsersn());
		user.setChannelfrom(EUserChannel.inviteuser);
		user.setDataMap_reason(reason);
		user.setType(Integer.valueOf(EUser.user.toString()));
		user.initPassword(user.getPassword());
		user.initPayPwd(user.getPaypwd());
		if (so.insertInvite(user) && ConsumerHP.sendEmail(user, EContent.email_invite)) {
			return success();
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 账户冻结/解冻
	 * 
	 * @param uid
	 * @param status
	 * @return
	 * @author XieMing 2016-5-17 下午2:08:11
	 */
	@RequestMapping(value = { "/user/ajax/consumer/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_status(String uid, @PathVariable int status) {
		User user = so.get(uid);
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminid());
		adminLog.setChannel(EChannel.consumer.toInt());
		if (user != null) {
			adminLog.setDataid(user.getUid());
			HashMap<String, Object> map = new HashMap<>();
			map.put(MapperDict.uid, user.getUid());
			switch (status) {
			case 0:
				if(user.getStatus().toString() == "11") {
					map.put(MapperDict.freeze, 1);
				}
				if(user.getStatus().toString() == "12") {
					map.put(MapperDict.freeze, 2);
				}
				user.setStatus(EStatus.disable);
				adminLog.setData(EContent.email_disable.getName());
				adminLog.setDesc("冻结用户");
				adminLog.setOperation(EContent.email_disable.getName());
				break;
			case 1:
				if (user.getFreeze() == 1) {
					user.setStatus(EStatus.start);
				}
				if (user.getFreeze() == 2) {
					user.setStatus(EStatus.wait);
				}
				map.put(MapperDict.freeze, 0);
				adminLog.setData(EContent.email_toenable.getName());
				adminLog.setDesc("解冻用户");
				adminLog.setOperation(EContent.email_toenable.getName());
				break;
			}
			if (so.updateStatus(user, adminLog) && userInfoSO.updateFields(map)) {
				return success();
			} else {
				return result(MsgKeyDict.updateFailed);
			}
		} else {
			return result(MsgKeyDict.updateFailed);
		}
	}

	/**
	 * 设置裁判
	 * 
	 * @param uid
	 * @param major
	 * @return
	 * @author XieMing 2016-5-17 下午4:20:08
	 */
	@RequestMapping(value = "/user/ajax/consumer/setjudge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_setjudge(String uid, String major) {
		User user = so.get(uid);
		user.plusType(EUser.judgement);
		Judge judge = judgeSO.getOne(user.getUid(), EMajor.get(major));
		if(judge != null){
			return result(MsgKeyDict.addFailed);
		}
		judge = new Judge(uid, AutoIncrHP.getJudgeSn(user), EJudge.specialInviteJudgement, EMajor.get(major));
		Certificate certificate = new Certificate(uid, AutoIncrHP.getCertificatesn(), judge.getUid(), EMajor.get(major), ECertificate.inviteJudge);
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.user.toInt());
		adminLog.setDataid(judge.getUid());
		adminLog.setDesc("");
		adminLog.setData(EAdminLog.addInviteJudge.getName());
		adminLog.setOperation(EAdminLog.addInviteJudge.getName());
		if (judgeSO.update(user, judge, certificate, adminLog)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 设置专家
	 * 
	 * @param uid
	 * @param expertlevel
	 * @param major
	 * @return
	 * @author XieMing 2016-5-17 下午4:21:02
	 */
	@RequestMapping(value = "/user/ajax/consumer/setexport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_setexport(String uid, String expertlevel, String major) {
		User user = so.get(uid);
		user.plusType(EUser.expert);
		Expert expert = expertSO.getOne(user.getUid(), EMajor.get(major), EExpertLevel.get(expertlevel));
		if(expert != null) {
			return result(MsgKeyDict.addFailed);
		}
		expert = new Expert(uid, AutoIncrHP.getExpert(user), EMajor.get(major), EExpertLevel.get(expertlevel), "", EExpert.inviteExpert, "certificateuid", "file");
		Certificate certificate = new Certificate(uid, AutoIncrHP.getCertificatesn(), expert.getUid(), EMajor.get(major), ECertificate.inviteExpert);
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.user.toInt());
		adminLog.setDataid(expert.getUid());
		adminLog.setDesc("");
		adminLog.setData(EAdminLog.addInviteExpert.getName());
		adminLog.setOperation(EAdminLog.addInviteExpert.getName());
		if (expertSO.update(user, expert, certificate, adminLog)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 设置余额
	 * 
	 * @param uid
	 * @param isplus
	 * @param number
	 * @return
	 * @author XieMing 2016-5-17 下午4:45:41
	 */
	@RequestMapping(value = "/user/ajax/consumer/setaccount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_setaccount(String uid, boolean isplus, String number, String desc) {
		User user = so.get(uid);
		Double num = Double.parseDouble(number);
		boolean flag = false;
		if (!isplus) {
			user.subtractAccount(num);
		} else {
			user.plusAccount(num);
		}
		if (ConsumerHP.sendAccountEmail(user, isplus, number)) {
			flag = true;
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminid());
		adminLog.setDesc(desc);
		adminLog.setDataid(user.getUid());
		adminLog.setChannel(EChannel.consumer.toInt());
		adminLog.setData(EContent.email_tepiaccount.getName());
		adminLog.setOperation(EContent.email_tepiaccount.getName());
		if (so.updateAccount(user, adminLog) && flag) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 重置登录密码
	 * 
	 * @param uid
	 * @param pwd
	 * @return
	 * @author XieMing 2016-5-18 上午9:35:54
	 */
	@RequestMapping(value = "/user/ajax/consumer/setpwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> consumer_setpwd(String uid, String pwd) {
		User user = so.get(uid);
		user.initPassword(pwd);
		if (so.updatePwd(user)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 设置查询条件
	 * 
	 * @return
	 * @author XieMing 2016-5-25 上午10:46:34
	 */
	@RequestMapping(value = "/user/ajax/consumer/search_condition", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> search_condition(Map<String, Object> model, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		for (EUserChannel item : EUserChannel.values()) {
			map.put(item.toString(), item.getName());
		}
		Map<String, String> map1 = new HashMap<String, String>();
		for (EStatus item : EStatus.values()) {
			map1.put(item.toString(), item.getName());
		}
		Map<String, Object> map_all = new HashMap<String, Object>();
		map_all.put(ViewKeyDict.userchannel, map);
		map_all.put(ViewKeyDict.userstate, map1);
		return map_all;
	}

}