package com.supergenius.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.helper.SEOHP;
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
		if (modelAndView != null) {
			SysHP.initWebPageVars(modelAndView.getModel());
			if (BaseSysConf.getSEOPath("finance") != null) {
				modelAndView.getModel().put(ViewKeyDict.site, "finance");
				SEOHP.initialize(request.getServletPath(), modelAndView.getModel());
			}
		}
		super.initFrontPageVars(request, modelAndView);
	}

	@Override
	protected void loginHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect(WebConf.UserBaseRootPath + "/login");
	}

	@Override
	protected void handleMemberUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = BaseUserHP.getCurrUser(request);
		if (!user.getIsUser()) {
			response.sendRedirect(WebConf.FinanceBaseRootPath + "/notmember");
		}
	}

	/**
	 * @param request
	 * @return
	 */
	protected boolean isLogin(HttpServletRequest request) {
		if (WebUtil.isMobileRequest(request)) {
			request.setAttribute(ViewKeyDict.phone, true);
		}
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			request.setAttribute(ViewKeyDict.me, user);
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