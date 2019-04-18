package com.supergenius.xo.life.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Catalogue;

/**
 * 目录模块Dao
 * @author ChenQi
 * @date 2018年5月9日18:34:53
 */
@Component("lifeCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue> {
	
	/**
	 * 通过cid得到一个对象
	 * @author ChenQi
	 * @typename Catalogue
	 * @data 2018年5月9日18:34:50
	 */
	Catalogue get(long cid);
	
	/**
	 * 通过cid删除一个对象
	 * @author ChenQi
	 * @typename boolean
	 * @data 2018年5月9日18:34:56
	 */
	boolean delete(long cid);
}
