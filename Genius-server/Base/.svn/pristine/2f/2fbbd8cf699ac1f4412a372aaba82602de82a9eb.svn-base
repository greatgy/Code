package com.genius.server.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genius.server.base.helper.SysHP;

/**
 * 管理后台拦截器
 * 
 * @author architect.bian
 * @createtime 2014-8-26 下午2:16:37
 */
public abstract class BaseAdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (isNeedInitPageVarUrl(request.getRequestURI())) {
			InitPageVar(request, modelAndView);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 需要初始化页面的uri
	 * @param uri
	 * @return
	 * @author: Architect.bian
	 * 2014-6-23 下午5:01:38
	 */
	protected boolean isNeedInitPageVarUrl(String uri) {
		return true;
	}
	
	/**
	 * @param request
	 * @param modelAndView 
	 */
	protected void InitPageVar(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null) {
			SysHP.initAdminPageVars(modelAndView.getModel());
		}
	}

}
