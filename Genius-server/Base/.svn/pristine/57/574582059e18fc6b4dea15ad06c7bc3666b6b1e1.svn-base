package com.genius.server.base.spring.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author architect.bian
 * 
 */
public class BaseAccessDecisionManager implements AccessDecisionManager {

	private static Logger log = LoggerFactory.getLogger(BaseAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		log.debug("configAttributes:" + configAttributes.toString());
		// 所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			log.debug("needPermission:" + needPermission);
			// 用户所拥有的权限authentication
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				log.debug("GrantedAuthority:" + ga.toString());
				if (!ga.toString().equals(BaseSecurityMetadataSource.ROLE_ANONYMOUS) && needPermission.equals(BaseSecurityMetadataSource.ROLE_COMMON)) {//管理员登录后，最基本的权限对应的Url
					return;
				} else	if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		// 没有权限让我们去捕捉
		throw new AccessDeniedException("没有权限访问！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.AccessDecisionManager#supports(org
	 * .springframework.security.access.ConfigAttribute)
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.AccessDecisionManager#supports(java
	 * .lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
