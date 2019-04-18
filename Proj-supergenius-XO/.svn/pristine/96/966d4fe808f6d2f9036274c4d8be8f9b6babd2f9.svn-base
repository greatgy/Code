package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章类别
 * 
 * @author ShangJianguo
 */
public enum EArticleChannel {
	
	mergecase("0"), // 并购案例
	mergenews("1");// 并购动态

	private final String value;

	private EArticleChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EArticleChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EArticleChannel get(String str) {
		for (EArticleChannel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EArticleChannel.getName(EArticleChannel.get(value), Locale.CHINA);
	}
	
	public static String getName(EArticleChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
