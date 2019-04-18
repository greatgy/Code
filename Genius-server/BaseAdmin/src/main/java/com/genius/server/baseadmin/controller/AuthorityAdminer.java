package com.genius.server.baseadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.baseadmin.entity.Authority;
import com.genius.server.base.controller.BaseController;
import com.genius.xo.baseadmin.service.AuthoritySO;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class AuthorityAdminer extends BaseController {
	
	@Autowired
	private AuthoritySO so;

	@RequestMapping(value = "/api/authority/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Authority>> authority_all(Map<String, Object> model) {
		return json(so.getList(), Json.keyValueStrategy);
	}
	
	@RequestMapping(value = "/api/authority/list/{roleuid:.{32}}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Authority>> authority_list(@PathVariable String roleuid) {
		return json(so.getListByField(BaseMapperDict.roleuid, roleuid), Json.keyValueStrategy);
	}
}