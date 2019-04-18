package com.supergenius.xo.gupage.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.gupage.entity.Catalogue;

/**
 * 目录模块Dao
 * @author ChenQi
 * @date 2017年11月13日15:42:12
 */
@Component("gupageCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue> {
	
	/**
	 * 通过cid得到一个对象
	 * @author ChenQi
	 * @typename Catalogue
	 * @data 2017年11月13日16:12:18
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过cid删除一个对象
	 * @author ChenQi
	 * @typename boolean
	 * @data 2017年11月13日16:12:18
	 */
	boolean delete(int cid);
}
