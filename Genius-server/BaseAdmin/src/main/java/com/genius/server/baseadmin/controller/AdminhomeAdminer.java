package com.genius.server.baseadmin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.conf.BaseUriConf;
import com.genius.server.base.controller.BaseController;

/**
 * 管理控制台的控制层
 * 
 * @author architect.bian
 * @createtime 2014-6-29 下午2:14:10
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class AdminhomeAdminer extends BaseController {
	
	/**
	 * 管理控制台
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public String adminhome(Map<String, Object> model, HttpServletRequest request) {
		return "doadminhome";
	}
}
