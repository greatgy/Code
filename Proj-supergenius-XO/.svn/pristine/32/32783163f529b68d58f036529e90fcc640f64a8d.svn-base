package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章的类别
 * @author liushaomin
 */
public enum EArticleChannel {
	
	manager("0"), // 职业经理人
	enterpriser("1"),//企业家
	wisdom("2"),// 智慧产业
	video("3");//视频

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
