package com.genius.core.base.utils;

import java.util.UUID;

/**
 * @author Architect.bian
 *
 */
public class GlobalUtil extends BaseUtil {
	/**
	 * 随机获取UID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
}
