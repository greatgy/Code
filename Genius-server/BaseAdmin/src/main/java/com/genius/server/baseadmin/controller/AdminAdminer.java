package com.genius.server.baseadmin.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseMsgKeyDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.enums.EChannel;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminSO;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class AdminAdminer extends BaseController {
	
	@Autowired
	private AdminSO so;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.channel, EChannel.admin.name());
		model.put(BaseViewKeyDict.channeloid, EChannel.admin);
		model.put(BaseViewKeyDict.channelname, EChannel.getName(EChannel.admin, Locale.CHINA));
		return "doadmin";
	}
	
	@RequestMapping(value = "/api/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> admin_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> map = AdminHP.query(model);
		return json(map, Json.webStrategy);
	}

	@RequestMapping(value = "/api/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_add(Admin admin) {
		Admin tmp = so.getByAdminid(admin.getAdminid());
		if (tmp == null) {
			so.add(admin);
			return success();
		} else {
			return result(BaseMsgKeyDict.exist);
		}
	}

	@RequestMapping(value = "/api/admin/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_edit(Admin admin) {
		if (so.update(admin)) {
			return success();
		} else {
			return result(BaseMsgKeyDict.updateFailed);
		}
	}
	
	@RequestMapping(value = "/api/admin/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> admin_delete(String[] ids) {
		so.delete(ids);
		return success();
	}
	
	@RequestMapping(value = "/api/admin/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			so.updateStatus(adminLog, EStatus.get(status), ids);
			return success();
		} else {
			return result(BaseMsgKeyDict.dopwdIsWrong);
		}
	}

}