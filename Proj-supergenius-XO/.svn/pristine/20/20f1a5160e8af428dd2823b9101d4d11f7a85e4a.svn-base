package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论互动中评论人的类型
 * @author liushaomin
 */
public enum EUserType {
	
	member("0"), // 会员
	visitor("1");// 游客

	private final String value;

	private EUserType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserType get(String str) {
		for (EUserType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EUserType.getName(EUserType.get(value), Locale.CHINA);
	}
	
	public static String getName(EUserType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
