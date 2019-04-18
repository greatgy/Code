package com.supergenius.web.front.user.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.user.entity.User;

/**
 * 登陆服务类
 * 
 * @author liubin
 * 
 */
public class LoginHP extends BaseHP {

	/**
	 * 设置自动登录
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @author YuYingJie
	 */
	public static void rememberMe(User user, HttpServletRequest request, HttpServletResponse response) {
		DateTime dateTime = DateUtil.NowTime();
		dateTime.plusDays(SysConf.AliveTime);
		String endTime = DateUtil.parseDateTime(dateTime, DateUtil.FORMAT_DATE_NOWID);
		String userAgent = request.getHeader("User-Agent");
		String ip = NetUtil.getIPAddr(request);
		String userpasswd = DigestUtils.md5Hex(userAgent + endTime + user.getUid() + ip);
		setCookie(String.valueOf(user.getOid()), endTime, userpasswd, request, response);
	}

	/**
	 * 设置cookie
	 * 
	 * @param oid
	 * @param endTime
	 * @param userpasswd
	 * @param request
	 * @param response
	 * @author YuYingJie
	 */
	public static void setCookie(String oid, String endTime, String userpasswd, HttpServletRequest request, HttpServletResponse response) {
		Cookie userid = CookieUtil.getCookie(request, ViewKeyDict.userid);
		Cookie userpwd = CookieUtil.getCookie(request, ViewKeyDict.userpwd);
		Cookie endtime = CookieUtil.getCookie(request, ViewKeyDict.endtime);
		if (userid == null) {
			CookieUtil.addCookie(response, ViewKeyDict.userid, oid, 3600 * 24 * SysConf.AliveTime);
		} else {
			CookieUtil.removeCookie(response, ViewKeyDict.userid);
			CookieUtil.addCookie(response, ViewKeyDict.userid, oid, 3600 * 24 * SysConf.AliveTime);
		}
		if (userpwd == null) {
			CookieUtil.addCookie(response, ViewKeyDict.userpwd, userpasswd, 3600 * 24 * SysConf.AliveTime);
		} else {
			CookieUtil.removeCookie(response, ViewKeyDict.userpwd);
			CookieUtil.addCookie(response, ViewKeyDict.userpwd, userpasswd, 3600 * 24 * SysConf.AliveTime);
		}
		if (endtime != null) {
			CookieUtil.addCookie(response, ViewKeyDict.endtime, endTime, 3600 * 24 * SysConf.AliveTime);
		} else {
			CookieUtil.removeCookie(response, ViewKeyDict.endtime);
			CookieUtil.addCookie(response, ViewKeyDict.endtime, endTime, 3600 * 24 * SysConf.AliveTime);
		}
	}
	
}
