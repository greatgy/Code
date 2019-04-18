package com.supergenius.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.genius.server.base.interceptor.BaseWebInterceptor;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.helper.SysHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.user.entity.User;

/**
 * @author Architect.bian
 * 
 */
public class WebInterceptor extends BaseWebInterceptor {

	@Override
	protected void initFrontPageVars(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null && modelAndView.getViewName() != null && !isRedirectView(modelAndView.getViewName())) {
			SysHP.initWebPageVars(modelAndView.getModel());
		}
		super.initFrontPageVars(request, modelAndView);
	}
	
	/**
	 * @param request
	 * @return
	 */
	protected boolean isLogin(HttpServletRequest request) {
		request.setAttribute(ViewKeyDict.loginurl, WebConf.LoginURL);
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			request.setAttribute(ViewKeyDict.me, user);
			if (user.getIsUser()) {
				request.setAttribute(ViewKeyDict.member, user.getUid());
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 自动登录是否成功
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author YuYingJie
	 */
	@Override
	protected boolean isAutoLogin(HttpServletRequest request, HttpServletResponse response) {
		if (BaseUserHP.autoLogin(request, response)) {
			return isLogin(request);
		} else {
			return false;
		}
	}
	
}
