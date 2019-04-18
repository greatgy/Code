package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 收藏、点赞枚举
 * 
 * @author LiJiacheng
 */
public enum ECountDetail {

	collect("1"), // 收藏
	praise("2"), // 点赞
	cilck("3"), // 点击
	top("4"); // 置顶

	private final String value;

	private ECountDetail(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECountDetail get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECountDetail get(String str) {
		for (ECountDetail e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return ECountDetail.getName(ECountDetail.get(value), Locale.CHINA);
	}

	public static String getName(ECountDetail e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
