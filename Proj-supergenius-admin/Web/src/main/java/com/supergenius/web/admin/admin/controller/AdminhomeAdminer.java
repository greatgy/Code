package com.supergenius.web.admin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supergenius.global.conf.UriConf;

/**
 * 管理控制台的controller
 * 
 * @author architect.bian
 * @createtime 2014-6-29 下午2:15:53
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AdminhomeAdminer extends com.genius.server.baseadmin.controller.AdminhomeAdminer {
	
}
