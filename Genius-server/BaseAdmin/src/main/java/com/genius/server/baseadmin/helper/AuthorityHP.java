package com.genius.server.baseadmin.helper;

import java.util.ArrayList;
import java.util.List;

import com.genius.model.baseadmin.entity.Authority;
import com.genius.model.baseadmin.entity.Role;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AuthoritySO;

/**
 * @author architect.bian
 *
 */
public class AuthorityHP extends BaseHP {


//	private static Logger log = LoggerFactory.getLogger(AdminurlHP.class);
	
	private static AuthoritySO so;

	private static AuthoritySO getSO() {
		if (so == null) {
			so = (AuthoritySO) spring.getBean(AuthoritySO.class);
		}
		return so;
	}
	
	/**
	 * 组织url数组
	 */
	public static String[]  getUrls(List<Authority> list) {
		String[] urls = new String[list.size()];
		for(int i = 0; i < list.size(); i ++){
			urls[i] = list.get(i).getUrl();
		}
		return urls;
	}
	
	/**
	 * 批量插入adminurl
	 */
	public static void  insertUrls(Role role ,String[] urls,String[] urlnames ) {
		List<Authority> list = new ArrayList<>();
		for(int i = 0 ; i < urls.length; i ++ ){
			Authority item = new Authority();
			item.setRoleid(role.getRoleid());
			item.setUrl(urls[i]);
			item.setRoleuid(role.getUid());
			item.setName(urlnames[i]);
			list.add(item);
		}
		getSO().add(list);
	}

	public static List<String> getAuthoritiesByUrl(String requestUrl) {
		return getSO().getAuthoritiesByUrl(requestUrl);
	}
}
