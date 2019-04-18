package com.supergenius.core.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.genius.core.base.conf.MappingConf;
import com.genius.server.base.exception.SimpleMappingExceptionResolver;
import com.supergenius.global.helper.SysHP;

/**
 * 异常处理类
 * 
 * @author architect.bian
 * @createtime 2014-7-24 下午8:39:26
 */
public class MyExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * json请求异常处理
	 */
	@Override
	protected Map<String, Object> jsonRequestExceptionHandler(Exception ex) {
		return super.jsonRequestExceptionHandler(ex);
	}

	/**
	 * 普通页面请求异常处理
	 */
	@Override
	protected void pageRequestExceptionHandler(HttpServletRequest request, ModelAndView modelAndView, Exception ex) {
		String requestURI = request.getRequestURI();
		if (MappingConf.isAdminUrl(requestURI)) {
			SysHP.initAdminPageVars(modelAndView.getModel());
		} else if (MappingConf.isLoginNeedUrl(requestURI)) {
			SysHP.initMyCenterPageVars(modelAndView.getModel());
		} else if (MappingConf.isNeedInitPageVarUrl(requestURI)) {
			SysHP.initWebPageVars(modelAndView.getModel());
		}
//		super.pageRequestExceptionHandler(request, modelAndView, ex);
	}

}
