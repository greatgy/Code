package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 货币单位
 * @author ShangJianguo
 */
public enum ECurrency {

	rmb("0"), // 人民币
	dollar("1"),// 美元
	euro("2"), // 欧元
	japaneseyen("3");// 日元

	private final String value;

	private ECurrency(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECurrency get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECurrency get(String str) {
		for (ECurrency e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static ECurrency getByName(String name) {
		for (ECurrency e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return ECurrency.getName(ECurrency.get(value), Locale.CHINA);
	}
	
	public static String getName(ECurrency e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
