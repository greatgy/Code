package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文档枚举类型
 * 
 * @author LiJiacheng
 */
public enum ECase {

	doc("0"), // 文档
	link("1");// 链接

	private final String value;

	private ECase(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECase get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECase get(String str) {
		for (ECase e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return ECase.getName(ECase.get(value), Locale.CHINA);
	}

	public static String getName(ECase e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
