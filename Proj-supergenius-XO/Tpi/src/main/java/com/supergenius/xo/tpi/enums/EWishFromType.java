package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;


/**
 * 用户来源枚举
 * 
 * @author ShangJianguo
 */
public enum EWishFromType {
	
	merger("0"),// 并购平台
	supergenius("1");// 超天才网
	
	private final String value;

	private EWishFromType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EWishFromType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EWishFromType get(String str) {
		for (EWishFromType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EWishFromType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
