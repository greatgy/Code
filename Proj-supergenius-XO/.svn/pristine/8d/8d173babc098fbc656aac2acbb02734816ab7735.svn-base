package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章（新闻枚举）
 * @author liushaomin
 */
public enum EArticleType {

	article("0"), // 文章
	news("1"),// 新闻
	video("2"),// PC视频
	mobilevideo("3");// 移动端视频

	private final String value;

	private EArticleType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EArticleType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EArticleType get(String str) {
		for (EArticleType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EArticleType.getName(EArticleType.get(value), Locale.CHINA);
	}
	
	public static String getName(EArticleType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
