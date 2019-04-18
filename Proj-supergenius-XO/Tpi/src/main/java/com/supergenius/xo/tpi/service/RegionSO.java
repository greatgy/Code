package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Region;

/**
 * 区域SO
 * 
 * @author ShangJianguo
 */
public interface RegionSO extends BaseSO<Region> {
	
	/**
	 * 根据父级菜单选择区域
	 * @param parentid
	 * @return
	 * @author ShangJianguo
	 */
	List<Region> getList(int parentid);
	
	/**
	 * 获取省级的区域
	 * @return
	 * @author ShangJianguo
	 */
	List<Region> getProvinceList();
	
	/**
	 * 根据id编号获取对象
	 * @param id
	 * @return
	 * @author ShangJianguo
	 */
	Region get(int id);
}
