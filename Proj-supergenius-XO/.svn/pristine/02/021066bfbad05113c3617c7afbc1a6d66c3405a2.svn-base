package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;
import com.supergenius.xo.common.enums.EChannel;

/**
 * 用户的类别，超天才用户或者是并购结构用户
 * 
 * @author ShangJianguo
 */
public enum EUserType {
	user("0"),// 超天才用户
	tpiuser("1"),// 并购机构用户
	all("2");//所有用户
	
	private final String value;

	private EUserType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserType get(String str) {
		for (EUserType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public static String getName(EChannel e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
}
