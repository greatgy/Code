package com.supergenius.web.admin.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.model.baseadmin.entity.Admin;
import com.genius.model.baseadmin.entity.AdminLog;
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
public class AdminAdminer extends com.genius.server.baseadmin.controller.AdminAdminer {
	
	@Override
	@RequestMapping(value = "/ajax/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> admin_list(Map<String, Object> model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.admin_list(model, request);
	}

	@Override
	@RequestMapping(value = "/ajax/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_add(Admin admin) {
		// TODO Auto-generated method stub
		return super.admin_add(admin);
	}

	@Override
	@RequestMapping(value = "/ajax/admin/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_edit(Admin admin) {
		// TODO Auto-generated method stub
		return super.admin_edit(admin);
	}

	@Override
	@RequestMapping(value = "/ajax/admin/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> admin_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		// TODO Auto-generated method stub
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.supergeniusadmin.toInt());
		adminLog.setOperation(EAdminLog.updateAdminerStatus.getName());
		adminLog.setData(EAdminLog.updateAdminerStatus.getName());
		return super.admin_status(ids, status, adminLog, dopwd);
	}

	/* (non-Javadoc)
	 * @see com.genius.server.baseadmin.controller.AdminAdminer#admin_delete(java.lang.String[])
	 * @author: ShangJianguo
	 * 2014-10-22 上午11:42:17
	 */
	@Override
	@RequestMapping(value = "/ajax/admin/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> admin_delete(String[] ids) {
		// TODO Auto-generated method stub
		return super.admin_delete(ids);
	}
}