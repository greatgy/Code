package com.genius.core.base.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Architect.bian
 *
 */
public class ListUtil extends BaseUtil {

	/**
	 * 从list中删除所有为null的值
	 * @param list
	 * @return 
	 */
	public static void trim(List<?> list) {
		Collection<?> c = new HashSet<Object>();
		c.add(null);
		list.removeAll(c);
	}

	public static void trim(Collection<?> list) {
		Collection<?> c = new HashSet<Object>();
		c.add(null);
		list.removeAll(c);
	}
	
	/**
	 * 还未测试，从list中删除指定值
	 * @param list
	 * @param params
	 */
	public static void trim(List<?> list, Object... params) {
		for (Object obj : params) {
			list.remove(obj);
		}
	}
	
	public static String toString(String separator, String... strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(separator);
			sb.append(s);
		}
		return StrUtil.trim(sb.toString(), separator);
	}

	public static List<?> removeAll(List<?> list, List<?> remove) {
		list.removeAll(remove);
		return list;
	}
}
