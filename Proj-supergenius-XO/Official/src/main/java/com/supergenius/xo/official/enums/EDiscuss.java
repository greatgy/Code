package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论互动枚举
 * @author liushaomin
 */
public enum EDiscuss {
	
	user("0"), // 用户评论
	admin("1"),// 管理员回复
	specialcolumn("2");

	private final String value;

	private EDiscuss(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EDiscuss get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EDiscuss get(String str) {
		for (EDiscuss e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EDiscuss.getName(EDiscuss.get(value), Locale.CHINA);
	}
	
	public static String getName(EDiscuss e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
