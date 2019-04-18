package com.supergenius.web.admin.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.Role;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class RoleAdminer extends com.genius.server.baseadmin.controller.RoleAdminer {

	@Override
	@RequestMapping(value = "/ajax/role/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> role_list(Map<String, Object> model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.role_list(model, request);
	}

	@Override
	@RequestMapping(value = "/ajax/role/list/{adminuid:.{32}}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Role>> role_list(@PathVariable String adminuid) {
		// TODO Auto-generated method stub
		return super.role_list(adminuid);
	}

	@Override
	@RequestMapping(value = "/ajax/role/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Role>> role_all(Map<String, Object> model) {
		// TODO Auto-generated method stub
		return super.role_all(model);
	}

	@Override
	@RequestMapping(value = "/ajax/role/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_add(Role role) {
		// TODO Auto-generated method stub
		return super.role_add(role);
	}

	@Override
	@RequestMapping(value = "/ajax/role/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_edit(Role role) {
		// TODO Auto-generated method stub
		return super.role_edit(role);
	}

	@Override
	@RequestMapping(value = "/ajax/role/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> role_delete(String[] ids) {
		// TODO Auto-generated method stub
		return super.role_delete(ids);
	}

	@Override
	@RequestMapping(value = "/ajax/role/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		// TODO Auto-generated method stub
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.supergeniusadmin.toInt());
		adminLog.setOperation(EAdminLog.updateRoleStatus.getName());
		adminLog.setData(EAdminLog.updateRoleStatus.getName());
		return super.role_status(ids, status, adminLog, dopwd);
	}

}