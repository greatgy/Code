package com.supergenius.xo.startup.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Catalogue;

/**
 * 目录模块Dao
 * @author 许志翔
 * @date 2017年8月23日14:39:51
 */
@Component
public interface CatalogueDao extends BaseDao<Catalogue> {
	/**
	 * 通过cid得到一个对象
	 * @author 许志翔
	 * @typename Catalogue
	 * @data 2017年8月23日17:58:42
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过cid删除一个对象
	 * @author 许志翔
	 * @typename boolean
	 * @data 2017年8月23日17:58:47
	 */
	boolean delete(int cid);
}
