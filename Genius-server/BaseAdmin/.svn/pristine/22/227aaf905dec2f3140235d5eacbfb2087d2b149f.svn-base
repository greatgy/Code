package com.genius.server.baseadmin.controller;

import java.util.List;
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
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.model.baseadmin.entity.Role;
import com.genius.model.baseadmin.enums.EChannel;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.server.baseadmin.helper.RoleHP;
import com.genius.xo.baseadmin.service.RoleSO;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class RoleAdminer extends BaseController {

	@Autowired
	private RoleSO so;

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String role(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.channel, EChannel.role.name());
		model.put(BaseViewKeyDict.channeloid, EChannel.role);
		model.put(BaseViewKeyDict.channelname, EChannel.getName(EChannel.role, Locale.CHINA));
		return "dorole";
	}
	
	@RequestMapping(value = "/api/role/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> role_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> map = RoleHP.query(model);
		return json(map, Json.webStrategy);
	}
	
	/**
	 * 获取某个管理员的所有角色
	 * @param adminuid
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-30 下午12:42:18
	 */
	@RequestMapping(value = "/api/role/list/{adminuid:.{32}}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Role>> role_list(@PathVariable String adminuid) {
		return json(so.getListByAdminuid(adminuid, true), Json.keyValueStrategy);
	}
	
	@RequestMapping(value = "/api/role/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Role>> role_all(Map<String, Object> model) {
		return json(so.getList(), Json.keyValueStrategy);
	}
	
	@RequestMapping(value = "/api/role/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_add(Role role) {
		Role tmp = so.getByRoleid(role.getRoleid());
		if (tmp == null) {
			so.add(role);
			return success();
		} else {
			return result(BaseMsgKeyDict.exist);
		}
	}

	@RequestMapping(value = "/api/role/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_edit(Role role) {
		if (so.update(role)) {
			return success();
		} else {
			return result(BaseMsgKeyDict.updateFailed);
		}
	}
	
	@RequestMapping(value = "/api/role/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> role_delete(String[] ids) {
		so.delete(ids);
		return success();
	}
	
	@RequestMapping(value = "/api/role/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			so.updateStatus(adminLog, EStatus.get(status), ids);
			return success();
		} else {
			return result(BaseMsgKeyDict.dopwdIsWrong);
		}
	}
}