package com.supergenius.xo.finance.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.finance.entity.Catalogue;

/**
 * 目录Dao
 * @author XueZhenYong
 * @date 2017年12月29日下午2:58:50
 */
@Component("financeCatalogueDao")
public interface CatalogueDao extends BaseDao<Catalogue>{

	/**
	 * 通过cid得到一个对象
	 * @author XueZhenYong
	 * @typename Catalogue
	 * @data 2017年01月02日10:06:18
	 */
	Catalogue get(int cid);

	/**
	 * 通过cid删除一个对象
	 * @author XueZhenYong
	 * @typename Catalogue
	 * @data 2017年01月02日10:10:18
	 */
	boolean delete(int cid);


}
