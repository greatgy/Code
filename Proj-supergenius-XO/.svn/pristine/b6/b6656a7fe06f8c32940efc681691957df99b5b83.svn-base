package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 
 * @author ShangJianguo
 */
public enum EType {
	
	team("0"),//团队
	project("1");//项目
	
	private final String value;

	private EType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EType get(String str) {
		for (EType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
