package com.supergenius.xo.enterpriser.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Catalogue;

/**
 * CatalogueSO
 * @author loupengyu
 * @date 2018年1月29日11:10:48
 */
public interface CatalogueSO extends BaseSO<Catalogue>{
	/**
	 * 通过pcid获得目录
	 * 
	 * @author ChenQi
	 * @data 2017年8月28日11:22:02
	 * @return List<Catalogue>
	 */
	List<Catalogue> getListByPcid(int pcid);
	
	/**
	 * 通过pcid获得二级模块cids
	 * 
	 * @author YangGuang
	 * @data 2018年2月1日21:15:50
	 * @return List<Catalogue>
	 */
	List<Integer> getCidsByPcid(int pcid);
	
	/**
	 * 设置是否冻结
	 * 
	 * @param isStatus
	 * @return
	 * @author loupengyu
	 * @date 2018年1月30日17:13:05
	 */
	boolean update(int cid, EStatus status);
	
	/**
	 * 根据cid获取一个分类板块
	 * 
	 * @param cid
	 * @return
	 * @author loupengyu
	 * @date 2018年1月30日17:13:05
	 */
	Catalogue get(int cid);
}
