package com.supergenius.web.admin.account.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.account.helper.RechargeHP;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;

/**
 * 充值controller
 * @author YuYingJie
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class RechargeAdminer extends BaseController {

	@Autowired
	AccountSO so;
	
	/**
	 * 跳转到列表页面 
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = "/account/recharge", method = RequestMethod.GET)
	public String Account(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.recharge.name());
		model.put(ViewKeyDict.site, EChannel.account.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.recharge, Locale.CHINA));
		Map<String, String> bankMap = new HashMap<>();
		Map<String, String> siteMap = new HashMap<>();
		Map<String, String> typeMap = new HashMap<>();
		for (EBank item : EBank.values()) {
			bankMap.put(item.toString(), item.getName());
		}
		for (ESite item : ESite.values()) {
			siteMap.put(item.toString(), item.getName());
		}
		for (ECharge item : ECharge.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, bankMap);
		model.put(ViewKeyDict.map1, siteMap);
		model.put(ViewKeyDict.map2, typeMap);
		model.put(ViewKeyDict.rechargemoney, so.getMoneyByType(ECharge.recharge));
		model.put(ViewKeyDict.userfeemoney, so.getMoneyByType(ECharge.userfee));
		return "dorecharge";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = { "/account/ajax/recharge/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> recharge_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = RechargeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 导出excel
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/account/exportexcel" }, method = RequestMethod.POST)
	public String recharge_exportexcel(Map<String, Object> model, String sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String []sn = sns.split(",");
		RechargeHP.exportexcel(sn , request, response);
		return null;
	}
	
}
