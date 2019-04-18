package com.genius.server.baseadmin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.conf.BaseUriConf;
import com.genius.server.base.controller.BaseController;

/**
 * @author architect.bian
 *
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class AdminindexAdminer extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		return "doadminlogin";
	}

	/**
	 * 管理控制台
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String adminindex(Map<String, Object> model, HttpServletRequest request) {
//		Admin admin = so.getAdminByAdminid(getAdminid());
		return "doadminindex";
	}
	
	/**
	 * 管理控制台
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admintop", method = RequestMethod.GET)
	public String admintop(Map<String, Object> model, HttpServletRequest request) {
		return "doadmintop";
	}
	
	/**
	 * 管理控制台
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminmenu", method = RequestMethod.GET)
	public String adminmenu(Map<String, Object> model, HttpServletRequest request) {
		return "doadminmenu";
	}

}
