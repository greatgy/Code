package com.supergenius.xo.moralnews.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.moralnews.entity.Catalogue;

/**
 * 目录模块Dao
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
@Component("moralnewsCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue> {
	/**
	 * 通过cid得到一个对象
	 * 
	 * @author JiaShitao
	 * @typename Catalogue
	 * @data 2018年7月3日10:21:21
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过cid删除一个对象
	 * 
	 * @author JiaShitao
	 * @typename boolean
	 * @data 2018年7月3日10:21:41
	 */
	boolean delete(int cid);
}
