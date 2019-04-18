package com.supergenius.core.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.genius.server.base.interceptor.BaseWebInterceptor;
import com.supergenius.global.helper.SysHP;

/**
 * @author Architect.bian
 * 
 */
public class WebInterceptor extends BaseWebInterceptor {

	@Override
	protected void initFrontPageVars(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null) {
			SysHP.initWebPageVars(modelAndView.getModel());
		}
		super.initFrontPageVars(request, modelAndView);
	}
}
