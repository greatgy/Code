package com.genius.core.session.rule;

import javax.servlet.http.HttpServletRequest;

import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.conf.BaseCacheConf;
import com.genius.core.session.constant.SessDict;

/**
 * 用户登录的session规则
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午3:05:36
 */
public class SessionRule extends SessionBaseRule {

	protected static String p = "sid";

	@SuppressWarnings("unused")
	private SessionRule() {
		this(p, "", 0);
	}
	
	/**
	 * 
	 * @param p session的ID，如从cookie中获取的sid
	 * @param k 键
	 */
	public SessionRule(String sid, String k) {
		this(sid, k, 0);
	}

	/**
	 * 
	 * @param k 键值
	 * @param exp 过期时间
	 */
	public SessionRule(String sid, String k, int exp) {
		super(k, exp);
		super.path += BaseCacheConf.SPLITTER_PATH + sid;
		refreshConfKey();
	}

	/**
	 * 通过request获得cookie或者attribute中的值，拼接成cachekey
	 * @param request
	 * @param sessUserkey
	 */
	public SessionRule(HttpServletRequest request, String k) {
		this(WebUtil.getValueFromAttributeOrCookie(request, SessDict.SESSION_ID), k, 0);
	}

	/**
	 * 通过k获取cookie.sid对应的一个sessionrule，k为键
	 * 如:/s/f4785b06c14dc6a08c424ab8ef160d8d:user sid为f4785b06c14dc6a08c424ab8ef160d8d
	 * @param request
	 * @param user
	 * @param expire_UserSession
	 */
	public SessionRule(HttpServletRequest request, String k, int expire_UserSession) {
		this(WebUtil.getValueFromAttributeOrCookie(request, SessDict.SESSION_ID), k, expire_UserSession);
	}
}
