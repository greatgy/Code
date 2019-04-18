package com.genius.server.base.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author architect.bian
 *
 */
public abstract class BaseSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Logger log = LoggerFactory.getLogger(BaseSecurityMetadataSource.class);

	//超级管理员角色
	public static String ROLE_ADMIN = "ROLE_ADMIN";
	//一般角色，所有管理员都拥有的角色
	public static String ROLE_COMMON = "ROLE_COMMON";
	//匿名角色
	public static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	
//	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;//TODO 后期可缓存url
	
	/*
	 * 返回所请求资源所需要的权限(non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		log.debug("requestUrl is：" + requestUrl);
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		List<String> authorities = getAuthoritiesByUrl(requestUrl);
		for (String auth : authorities) {
			ConfigAttribute confattr = new SecurityConfig(auth);
			configAttributes.add(confattr);
		}
		return configAttributes;
	}

	/**
	 * 通过requestUrl获取操作此url的权限列表
	 * @param requestUrl
	 * @return
	 */
	protected List<String> getAuthoritiesByUrl(String requestUrl) {
		List<String> list = new ArrayList<>();
		list.add("ROLE_ADMIN");
		return list;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
