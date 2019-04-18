package com.supergenius.web.admin.tpi.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.AuditAdminHP;
import com.supergenius.xo.tpi.enums.ETpiUserType;

/**
 * 
 * @author ShangJianguo
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AuditAdminAdminer extends BaseController {

	/**
	 * 跳转到用户审核管理员管理
	 * @param model
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/auditadmin", method = RequestMethod.GET)
	public String auditAdmin(Map<String, Object> model) {
		Map<String, Object> typeMap = new HashMap<String, Object>();
		for (ETpiUserType type : ETpiUserType.values()) {
			typeMap.put(type.name(), ETpiUserType.getName(type, Locale.CHINA));
		}
		model.put(ViewKeyDict.map, typeMap);
		model.put(ViewKeyDict.map2, AuditAdminHP.getData());
		return "doauditadmin";
	}

	/**
	 * 保存用户提交的信息
	 * @param type 
	 * @param emails
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/auditadmin", method = RequestMethod.POST)
	public String auditAdmin_save(String type, String emails) {
		AuditAdminHP.saveData(type, emails);
		return redirectPrefix + WebConf.baseRootPath + UriConf.baseAdminPath + "/auditadmin";
	}
}
