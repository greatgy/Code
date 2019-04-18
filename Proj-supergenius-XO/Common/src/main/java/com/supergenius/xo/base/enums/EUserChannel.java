package com.supergenius.xo.base.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 用户来源枚举类
 * 
 * @author ShangJianguo
 */
public enum EUserChannel {
	// TODO 重新考虑字段
	userfee("0"), specialuser("1"), specialmember("2");

	private final String value;

	private EUserChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserChannel get(String str) {
		for (EUserChannel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EUserChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
