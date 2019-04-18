package com.supergenius.xo.moralnews.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moralnews.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author JiaShitao
 * @date 2018年9月19日09:47:55
 */
public interface CatalogueSO extends BaseSO<Catalogue> {
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param pcid
	 * @return
	 * @author tf
	 * @date 2018年9月19日
	 */
	Catalogue getOneByCid(int cid);
	
	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author tf
	 */
	boolean update(int cid, EStatus status);

}
