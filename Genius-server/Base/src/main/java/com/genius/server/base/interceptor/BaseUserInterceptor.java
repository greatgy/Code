package com.genius.server.base.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genius.core.base.conf.MappingConf;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.SysHP;

/**
 * 用户拦截器
 * 
 * @author architect.bian
 * @createtime 2014-8-26 下午2:18:07
 */
public abstract class BaseUserInterceptor extends HandlerInterceptorAdapter {

	private String regex = ".*";
	
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (isNeedInitPageVarUrl(request.getRequestURI()) && modelAndView != null) {
			InitPageVar(request, modelAndView);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * @param requestURI
	 * @return
	 */
	protected boolean isNeedInitPageVarUrl(String uri) {
		return Pattern.matches(this.getRegex(), uri) && !StrUtil.isMatchAny(uri, MappingConf.getNoNeedInitPageVarUrls());
	}

	/**
	 * @param request
	 * @param modelAndView 
	 */
	protected void InitPageVar(HttpServletRequest request, ModelAndView modelAndView) {
		if (modelAndView != null) {
			SysHP.initMyCenterPageVars(modelAndView.getModel());
		}
	}

}
