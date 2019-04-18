package com.genius.core.session.utils;

import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.cache.engine.Engine;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.session.rule.SessionBaseRule;

/**
 * @author Architect.bian
 *
 */
public class SessionUtil extends MemcacheUtil {
	
	private static Engine getEngine() {
//		int start = (new SessionSuperRule()).getCacheId().length();
//		int end = k.indexOf(CacheConf.SPLITTER_KEY);
//		Rule rule = new SessionRule(StringUtils.substring(k, start, end), k);
		Rule rule = new SessionBaseRule();
		rule.setExpire(BaseSysConf.Expire_UserSession);
		return getEngine(rule);
	}
	
	public static Object get(String k) throws Exception {
		return getEngine().get(k, BaseSysConf.Expire_UserSession);
	}

	/**
	 * @param name
	 * @param value
	 */
	public static void set(String key, Object value) {
		getEngine().set(key, BaseSysConf.Expire_UserSession, value);
	}

	/**
	 * @param name
	 */
	public static void remove(String key) {
		getEngine().remove(key);
	}
}
