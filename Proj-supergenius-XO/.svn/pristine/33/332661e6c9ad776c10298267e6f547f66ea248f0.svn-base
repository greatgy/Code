package com.supergenius.xo.ai.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.Catalogue;

/**
 * 目录模块Dao
 * 
 * @author 杨光
 * @date 2017年9月19日09:52:44
 */
@Component("aiCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue> {
	
	/**
	 * 通过cid得到一个对象
	 * 
	 * @author 杨光
	 * @typename Catalogue
	 * @data 2017年9月19日10:21:21
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过cid删除一个对象
	 * 
	 * @author 杨光
	 * @typename boolean
	 * @data 2017年9月19日10:21:41
	 */
	boolean delete(int cid);
}
