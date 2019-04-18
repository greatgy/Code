package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章类别
 * 
 * @author Liuxiaoke
 */
public enum EMergeNewsType {
	
	overseasnews("0"), // 国外动态
	domesticnews("1"), // 国内动态
	tidbits("2"), // 并购花絮
	predictions("3");// 并购猜想

	private final String value;

	private EMergeNewsType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMergeNewsType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}
	
	public static EMergeNewsType get(String str) {
		for (EMergeNewsType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EMergeNewsType getByName(String str) {
		for (EMergeNewsType e : values()) {
			if (e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EMergeNewsType.getName(EMergeNewsType.get(value), Locale.CHINA);
	}
	
	public static String getName(EMergeNewsType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
