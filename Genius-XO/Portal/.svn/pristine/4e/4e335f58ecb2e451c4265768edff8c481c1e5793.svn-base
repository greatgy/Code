package com.genius.xo.portal.dao;

import com.genius.model.portal.entity.Content;
import com.genius.xo.base.dao.BaseDao;

/**
 * @author Architect.bian
 * 
 */
public interface ContentDao extends BaseDao<Content> {

	/**
	 * 通过ID查询数据库，返回对象
	 * 
	 * @param id
	 *            对应数据库中oid
	 * @return
	 */
	Content get(int id);

	@Deprecated
	Content get(String uid);

	void delete(int id);

	@Deprecated
	boolean delete(String uid);
}
