package com.supergenius.xo.finance.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.finance.entity.Catalogue;

/**
 * 目录SO
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:24:36
 */
public interface CatalogueSO extends BaseSO<Catalogue> {

	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param cid
	 * @return
	 * @author ChenQi
	 * @date 2017年11月15日15:19:20
	 */
	Catalogue get(int cid);

	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author XueZhenYong
	 * @Datetime 2018年1月5日上午10:20:18
	 */
	boolean update(int cid, EStatus status);
}
