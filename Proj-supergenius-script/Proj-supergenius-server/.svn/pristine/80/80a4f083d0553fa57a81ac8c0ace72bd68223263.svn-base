package com.supergenius.server.tpi.helper;

import java.util.ArrayList;
import java.util.List;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.tpi.entity.ContactInfo;
import com.supergenius.xo.tpi.entity.TpiUser;

/**
 * 文章HP
 * 
 * @author ShangJianguo
 */
public class BaseTpiuserHP extends BaseHP{
	
	/**
	 * 将地址代码转为中文
	 * @param TpiUser
	 * @return
	 * @author liushaomin
	 */
	public static TpiUser dealAddress(TpiUser tpiuser) {
		if (tpiuser != null) {
			ContactInfo temp  = BaseContactInfoHP.dealAddressName(tpiuser.getContactinfo());
			tpiuser.setContactinfo(temp);
			return tpiuser;
		}
		return tpiuser;
	}
	
	/**
	 * 将地址代码转为中文
	 * @param team
	 * @return
	 * @author liushaomin
	 */
	public static List<TpiUser> dealAddress(List<TpiUser> tpiusers) {
		List<TpiUser> list = new ArrayList<>();
		if (tpiusers != null) {
			for (TpiUser item : tpiusers) {
				if (item.getContactinfo() != null) {
					ContactInfo temp  = BaseContactInfoHP.dealAddressName(item.getContactinfo());
					item.setContactinfo(temp);
				}
				list.add(item);
			}
		}
		return list;
	}
}
