package com.supergenius.web.admin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supergenius.global.conf.UriConf;

/**
 * 管理控制台的首页头部菜单及登录页面
 * 
 * @author architect.bian
 * @createtime 2014-6-29 下午2:34:09
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AdminindexAdminer extends com.genius.server.baseadmin.controller.AdminindexAdminer {
	
}
