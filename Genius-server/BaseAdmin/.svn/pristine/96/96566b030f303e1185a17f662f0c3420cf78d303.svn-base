package com.genius.server.baseadmin.spring.security;

import java.util.ArrayList;
import java.util.List;

import com.genius.core.base.conf.MappingConf;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.spring.security.BaseSecurityMetadataSource;
import com.genius.server.baseadmin.helper.AuthorityHP;

/**
 * @author architect.bian
 *
 */
public class MySecurityMetadataSource extends BaseSecurityMetadataSource {
	
	/**
	 * 通过requestUrl获取操作此url的权限列表
	 * @param requestUrl
	 * @return
	 * @throws Exception 
	 */
	@Override
	protected List<String> getAuthoritiesByUrl(String requestUrl) {
		List<String> list = new ArrayList<>();
		if (StrUtil.isMatchAny(requestUrl, MappingConf.getAdminCommonUrls())) {
			list.add(ROLE_COMMON);//不需权限
		} else {
			//TODO 缓存处理 定时清楚
			list = AuthorityHP.getAuthoritiesByUrl(requestUrl);
		}
		return list;
	}

}
