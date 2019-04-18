package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章类别
 * 
 * @author Liuxiaoke
 */
public enum EMergeCaseType {
	
	casestudies("4"), // 案例分析
	legend("5"),// 传奇人物
	mergerstories("6");// 并购故事

	private final String value;

	private EMergeCaseType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMergeCaseType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}
	
	public static EMergeCaseType get(String str) {
		for (EMergeCaseType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EMergeCaseType getByName(String str) {
		for (EMergeCaseType e : values()) {
			if (e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EMergeCaseType.getName(EMergeCaseType.get(value), Locale.CHINA);
	}
	
	public static String getName(EMergeCaseType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
