package com.supergenius.server.tpi.helper;

import java.util.List;

import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.tpi.entity.Region;
import com.supergenius.xo.tpi.service.RegionSO;

/**
 * 区域HP
 * 
 * @author ShangJianguo
 */
public class BaseRegionHP extends BaseHP{
	
	private static RegionSO so;
	
	private static RegionSO getSo() {
		if (so == null) {
			so = spring.getBean(RegionSO.class);
		}
		return so;
	}
	
	/**
	 * 获取省份
	 * @return
	 * @author ShangJianguo
	 */
	public static List<Region> getProvinceList() {
		return getSo().getProvinceList();
	}
	
	/**
	 * 根据父级的id获取区域
	 * @param parentid
	 * @return
	 * @author ShangJianguo
	 */
	public static List<Region> getListByParentid(int parentid) {
		return getSo().getList(parentid);
	}
	
	/**
	 * 根据编码获取名字
	 * @param regionid
	 * @return
	 * @author ShangJianguo
	 */
	public static String getName(String regionid) {
		if (StrUtil.isNotEmpty(regionid)) {
			Region region = getSo().get(Integer.parseInt(regionid));
			if (region != null) {
				return region.getName();					 
			}
		}
		return "";
	}
	
	
}
