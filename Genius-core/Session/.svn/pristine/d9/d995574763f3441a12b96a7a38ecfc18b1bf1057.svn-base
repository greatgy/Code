package com.genius.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.genius.core.cache.conf.BaseCacheConf;

/**
 * @author Architect.bian
 *
 */
public final class SessionFactory {
	
	private static HttpSession session = null;

	/**
	 * 通过request获取一个session，通过BaseCacheConf.cacheProps判断是使用mc缓存session还是request.getSession()
	 * @param request
	 * @return
	 * @author Architect.bian
	 * 2014-6-27 下午3:19:50
	 */
	public static HttpSession getHttpSession(HttpServletRequest request) {
		if (session == null) {
			if (BaseCacheConf.cacheProps != null) {
				session = new SessionImpl();
			} else {
				return request.getSession();
			}
		}
		return session;
	}
	
//	@Autowired
	public void setSession(Session sess) {
		session = sess;
	}
}
