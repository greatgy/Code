package com.supergenius.web.front.life.mobile.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.session.constant.SessDict;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.common.utils.ValidUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 网站首页控制器
 *
 * @author 杨光
 * @date 2017年9月22日12:23:57
 */
@Controller
@RequestMapping(value = BaseUriConf.baseMobilePath)
public class LoginMobile extends BaseController {
	
	@Autowired
	UserSO so;

	/**
	 * 用户退出登录
	 */
	@ResponseBody
	@RequestMapping(value = { "/offline" }, method = RequestMethod.POST)
	public boolean offline_ajax(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		if (BaseUserHP.offline(request)) {
			CookieUtil.removeCookie(response, SessDict.SESSION_ID);
			CookieUtil.removeCookie(response, ViewKeyDict.redirect);
			CookieUtil.removeCookie(response, ViewKeyDict.userid);
			CookieUtil.removeCookie(response, ViewKeyDict.userpwd);
			CookieUtil.removeCookie(response, ViewKeyDict.endtime);
			return true;
		}
		return false;
	}
	
	/**
	 * 弹出框登录
	 * @param model
	 * @param request
	 * @param response
	 * @param usersn
	 * @param password
	 * @param rememberme
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = {"/ajax/login" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_index(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response,String email, String password, String rememberme) {
		if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
			User user = null;
			if (ValidUtil.isEmail(email)) {
				user = so.getEnableByEmail(email);
			} else if (ValidUtil.isMobile(email)) {
				user = so.getByMobile(email);
			}
			if (user != null) {
				if (user.checkPwd(password)) {
					BaseUserHP.online(user, request, response);
					if (Boolean.parseBoolean(rememberme)) {
						rememberMe(user, request, response);
					}
					user.setLastlogintime(DateUtil.NowTime());
					user.setLastloginip(NetUtil.getIPAddr(request));
					if (so.updateFields(user)) {
						return result(MsgKeyDict.success);
					}
				}
			}
		}
		return result(MsgKeyDict.failed);
	}

	/**
	 * 设置自动登录
	 * @param user
	 * @param request
	 * @param response
	 * @author YuYingJie
	 */
	private void rememberMe(User user, HttpServletRequest request, HttpServletResponse response) {
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
	 * @param oid
	 * @param endTime
	 * @param userpasswd
	 * @param request
	 * @param response
	 * @author YuYingJie
	 */
	private void setCookie(String oid, String endTime, String userpasswd, HttpServletRequest request, HttpServletResponse response ) {
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
	
	/**
	 * 手机端登录
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginByPhone(Map<String, Object> model, int pcid, Long cid, HttpServletRequest request, HttpServletResponse response) {
		model.put("pcid", pcid); 
		model.put(ViewKeyDict.cid, cid);
		return "mlogin";
	}
}
