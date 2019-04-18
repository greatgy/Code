package com.supergenius.core.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.genius.server.base.interceptor.BaseAdminInterceptor;
import com.supergenius.global.helper.SysHP;

/**
 * @author Architect.bian
 * 
 */
public class AdminInterceptor extends BaseAdminInterceptor {

	protected void InitPageVar(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null) {
			SysHP.initAdminPageVars(modelAndView.getModel());
		}
	}

}
