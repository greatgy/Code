package com.genius.core.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.conf.BaseSysConf;

/**
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午2:35:38
 */
public class CookieUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(CookieUtil.class);
	
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, 3600 * 24 * 365);
	}

	/**
	 * 添加Session作用于的cookie，浏览器关闭session清除
	 * @param response
	 * @param sessionId
	 * @param string
	 */
	public static void addCookieSess(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, -1);
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int age) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(age);
		cookie.setPath("/");
		if (StringUtils.isNotEmpty(BaseSysConf.CookieDomain)) {
			cookie.setDomain(BaseSysConf.CookieDomain);
		}
		try {
			response.addCookie(cookie);
		} catch (Exception e) {
			logException(log, e);
		}
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取所有Cookie，返回key=value;key1=value1;的字符串
	 * @param request
	 * @return
	 */
	public static String getAllCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		StringBuffer buf = new StringBuffer();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				buf.append(c.getName() + "=" + c.getValue() + ";");
			}
		}
		return buf.toString();
	}

	/**
	 * @param request
	 * @param response
	 */
	public static void clearAll(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath("/");// 根据你创建cookie的路径进行填写
				if (StringUtils.isNotEmpty(BaseSysConf.CookieDomain)) {
					cookie.setDomain(BaseSysConf.CookieDomain);
				}
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			System.out.println("清空Cookies发生异常！");
		}
	}

	/**
	 * @param request
	 * @param uid
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		if (StringUtils.isNotEmpty(BaseSysConf.CookieDomain)) {
			cookie.setDomain(BaseSysConf.CookieDomain);
		}
		response.addCookie(cookie);
	}

	/**
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-27 下午3:48:50
	 */
	public static String get(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}
}
