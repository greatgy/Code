package com.genius.server.base.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.session.constant.SessDict;

/**
 * 所有拦截器中第一个拦截器
 * 
 * @author architect.bian
 * @createtime 2014-8-26 下午2:26:53
 */
public class GlobalFirstInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = LoggerFactory.getLogger(GlobalFirstInterceptor.class);

	private static final String logRequestFormat = "%s?%s!%s";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(String.format(logRequestFormat, request.getRequestURL(), request.getQueryString(), request.getMethod()));
		}
		Cookie cookie = CookieUtil.getCookie(request, SessDict.SESSION_ID);
		if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
			//为每个client设置sid，对应于Session
			String str = DateUtil.Now() + StrUtil.getRandomString(SessDict.SESSION_ID_LEN);
			CookieUtil.addCookieSess(response, SessDict.SESSION_ID, str);
			request.setAttribute(SessDict.SESSION_ID, str);//第一次页面访问的时候cookie没法进行页面传递所以使用rquest.attribute 为游客随机设置一个值
		}
		return super.preHandle(request, response, handler);
	}
}
