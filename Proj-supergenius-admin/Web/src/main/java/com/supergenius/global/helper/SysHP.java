package com.supergenius.global.helper;

import java.util.Map;

import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;

/**
 * 系统操作，如为拦截器提供初始化view层参数等
 * 
 * @author architect.bian
 * @createtime 2014-7-24 下午8:30:36
 */
public class SysHP extends com.genius.server.base.helper.SysHP {

	/**
	 * 初始化管理后台页面的参数
	 * @param model
	 * @author Architect.bian
	 * @createtime 2014-7-24 下午8:29:25
	 */
	public static void initAdminPageVars(Map<String, Object> model) {
		model.put(ViewKeyDict.baseAdminPath, UriConf.baseAdminPath);
		if (AdminHP.getAdminid() != null) {
			model.put(ViewKeyDict.admin, AdminHP.getAdmin());
		}
	}
	
}
