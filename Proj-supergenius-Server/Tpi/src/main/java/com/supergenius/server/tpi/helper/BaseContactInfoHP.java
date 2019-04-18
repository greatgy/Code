package com.supergenius.server.tpi.helper;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.tpi.entity.ContactInfo;

/**
 * 联系信息基类
 * 
 * @author ShangJianguo
 */
public class BaseContactInfoHP extends BaseHP{
	
	/**
	 * 给省份和城市赋值为汉语
	 * @param ci
	 * @return
	 * @author ShangJianguo
	 */
	public static ContactInfo dealAddressName(ContactInfo ci) {
		if (ci == null) {
			return null;
		}
		ci.setCityName(BaseRegionHP.getName(ci.getCity()));
		ci.setProvinceName(BaseRegionHP.getName(ci.getProvince()));
		return ci;
	}
}
