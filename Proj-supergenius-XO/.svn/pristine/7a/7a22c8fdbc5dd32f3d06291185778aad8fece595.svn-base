package com.supergenius.xo.gupage.service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.gupage.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author ChenQi
 * @date 2017年11月13日15:44:16
 */
public interface CatalogueSO extends BaseSO<Catalogue> {

	/**
	 * 根据cid获取一个分类
	 * 
	 * @param 
	 * @return
	 * @author yangguang
	 * @date 2017年11月14日14:27:41
	 */
	Catalogue getOneByCid(int cid);
	
	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author ChenQi
	 */
	boolean update(int cid, EStatus status);
	
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param cid
	 * @return
	 * @author ChenQi
	 * @date 2017年11月15日15:19:20
	 */
	Catalogue get(int cid);
	
}
