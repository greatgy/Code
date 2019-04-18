package com.supergenius.xo.managernews.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.managernews.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author JiaShitao
 * @date 2018年7月3日09:57:23
 */
public interface CatalogueSO extends BaseSO<Catalogue> {
	
	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author 杨光
	 */
	boolean update(int cid, EStatus status);
	
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param cid
	 * @return
	 * @author JiaShitao
	 * @date 2018年7月3日18:00:45
	 */
	Catalogue get(int cid);
	
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param pcid
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日18:35:34
	 */
	Catalogue getOneByCid(int cid);

}
