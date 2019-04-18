package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * banner图的枚举
 * @author liushaomin
 */
public enum EBanner {
	
	pc("0"), // pc端
	mobile("1");// 移动端

	private final String value;

	private EBanner(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EBanner get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EBanner get(String str) {
		for (EBanner e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EBanner.getName(EBanner.get(value), Locale.CHINA);
	}
	
	public static String getName(EBanner e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
