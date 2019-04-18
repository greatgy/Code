package com.supergenius.xo.life.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.life.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author ChenQi
 * @date 2018年5月9日18:35:19
 */
public interface CatalogueSO extends BaseSO<Catalogue> {
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param pcid
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日18:35:34
	 */
	Catalogue getOneByCid(long cid);

	/**
	 * 通过pcid获得目录
	 * 
	 * @author ChenQi
	 * @data 2018年5月9日18:35:29
	 * @return List<Catalogue>
	 */
	List<Catalogue> getListByPcid(long pcid);
	
	/**
	 * 获得一级模块
	 * 
	 * @author YangGuang
	 * @data 2018年5月16日18:46:23
	 * @return List<Catalogue>
	 */
	List<Catalogue> getFirstCatalogues();

	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author ChenQi
	 */
	boolean update(String cid, int status);
}
