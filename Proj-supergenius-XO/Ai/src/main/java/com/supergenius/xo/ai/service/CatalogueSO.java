package com.supergenius.xo.ai.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.ai.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author 杨光
 * @date 2017年9月19日09:57:23
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
	 * @author 杨光
	 * @date 2017年8月23日18:00:45
	 */
	Catalogue get(int cid);
	
	/**
	 * 通过pcid获得目录
	 * 
	 * @author xuzhixiang
	 * @data 2017年8月28日11:22:02
	 * @return List<Catalogue>
	 */
	List<Catalogue> getListByCid(int cid);

}
