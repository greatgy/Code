package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 项目的并购类型枚举类
 * 
 * @author ShangJianguo
 */
public enum ETpiUserType {
	
	investment(0),// 投资机构 
	recommend(1), // 推荐机构
	merger(2);// 并购机构

	private final int value;

	private ETpiUserType(int v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ETpiUserType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETpiUserType get(String str) {
		for (ETpiUserType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 根据name得到对象
	 * 
	 * @param name
	 * @return
	 */
	public static ETpiUserType getByEName(String name) {
		for (ETpiUserType e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 根据values得到name
	 * 
	 * @param str
	 * @return
	 */
	public static String getByName(String str) {
		for (ETpiUserType e : values()) {
			int var = Integer.valueOf(e.toString());
			if (var == Integer.valueOf(str)) {
				return e.name();
			}
		}
		return null;
	}

	public String getTypeName() {
		return ETpiUserType.getName(ETpiUserType.get(value), Locale.CHINA);
	}

	public static String getName(ETpiUserType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
