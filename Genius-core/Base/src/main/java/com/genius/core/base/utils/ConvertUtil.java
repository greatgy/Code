package com.genius.core.base.utils;

import com.genius.core.base.utils.StrUtil;

/**
 * 类型转换类
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午2:33:35
 */
public class ConvertUtil extends BaseUtil {
	
	/**
	 * 将字符串转换成布尔类型
	 * @param value
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-8-25 下午2:34:00
	 */
	public static boolean toBoolean(String value) {
		if (value != null) {
			if (value.equalsIgnoreCase("true") || (StrUtil.isNumeric(value) && Integer.valueOf(value) > 0)) {
				return true;
			}
		}
		 return false;
	}
}