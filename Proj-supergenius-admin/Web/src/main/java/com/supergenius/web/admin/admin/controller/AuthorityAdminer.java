package com.supergenius.web.admin.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.model.baseadmin.entity.Authority;
import com.supergenius.global.conf.UriConf;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AuthorityAdminer extends com.genius.server.baseadmin.controller.AuthorityAdminer {

	@Override
	@RequestMapping(value = "/ajax/authority/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Authority>> authority_all(Map<String, Object> model) {
		// TODO Auto-generated method stub
		return super.authority_all(model);
	}

	@Override
	@RequestMapping(value = "/ajax/authority/list/{roleuid:.{32}}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Authority>> authority_list(@PathVariable String roleuid) {
		// TODO Auto-generated method stub
		return super.authority_list(roleuid);
	}

}