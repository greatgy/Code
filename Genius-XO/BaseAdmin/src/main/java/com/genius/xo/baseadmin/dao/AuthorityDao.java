package com.genius.xo.baseadmin.dao;

import java.util.List;
import java.util.Map;

import com.genius.model.baseadmin.entity.Authority;
import com.genius.xo.base.dao.BaseDao;

/**
 * 权限Dao
 * 
 * @author architect.bian
 * @createtime 2014-7-28 下午6:50:48
 */
public interface AuthorityDao extends BaseDao<Authority> {
	
	List<String> getAuthorities(Map<String, Object> map);
	
}
