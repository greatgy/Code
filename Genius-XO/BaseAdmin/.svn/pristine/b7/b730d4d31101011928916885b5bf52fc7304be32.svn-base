package com.genius.xo.baseadmin.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.baseadmin.entity.Authority;
import com.genius.xo.baseadmin.dao.AuthorityDao;
import com.genius.xo.baseadmin.service.AuthoritySO;

/**
 * @author liushaomin
 * 
 */
@Service
public class AuthoritySOImpl extends BaseSOImpl<Authority> implements AuthoritySO {

	@Autowired
	private AuthorityDao dao;

	@Override
	protected AuthorityDao getDao() {
		return dao;
	}

	@Override
	public List<String> getAuthorities(Map<String, Object> map) {
		return getDao().getAuthorities(map);
	}

	@Override
	public List<String> getAuthoritiesByUrl(String requestUrl) {
		Map<String, Object> map = new HashMap<>();
		map.put(BaseMapperDict.urlregx, requestUrl);
		return getAuthorities(map);
	}

}
