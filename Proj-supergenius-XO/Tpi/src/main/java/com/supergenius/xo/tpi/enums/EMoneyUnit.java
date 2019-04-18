package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 货币单位
 * @author ShangJianguo
 */
public enum EMoneyUnit {

	million("0"), // 百万
	hundredmillion("1");// 亿

	private final String value;

	private EMoneyUnit(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMoneyUnit get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EMoneyUnit get(String str) {
		for (EMoneyUnit e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EMoneyUnit getByName(String name) {
		for (EMoneyUnit e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EMoneyUnit.getName(EMoneyUnit.get(value), Locale.CHINA);
	}
	
	public static String getName(EMoneyUnit e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
