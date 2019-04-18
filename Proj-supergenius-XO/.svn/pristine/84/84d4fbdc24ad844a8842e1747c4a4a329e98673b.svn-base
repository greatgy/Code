package com.supergenius.xo.startup.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Catalogue;

/**
 * 目录模块so
 * 
 * @author 许志翔
 * @date 2017年8月23日14:41:11
 */
public interface CatalogueSO extends BaseSO<Catalogue> {
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param pcid
	 * @return
	 * @author 许志翔
	 * @date 2017年8月23日18:00:45
	 */
	Catalogue getOneByCid(int cid);

	/**
	 * 通过pcid获得目录
	 * 
	 * @author ChenQi
	 * @data 2017年8月28日11:22:02
	 * @return List<Catalogue>
	 */
	List<Catalogue> getListByPcid(int pcid);

	/**
	 * 设置是否冻结
	 * 
	 * @param oid
	 * @param isStatus
	 * @return
	 * @author 许志翔
	 */
	boolean update(String cid, int status);
}
