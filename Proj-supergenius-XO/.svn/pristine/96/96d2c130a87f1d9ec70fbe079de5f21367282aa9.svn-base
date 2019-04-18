package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 题型枚举
 * 
 * @author LiJiacheng
 */
public enum EQst {

	single("1"), // 单选
	material("2");// 材料

	private final String value;

	private EQst(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EQst get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EQst get(String str) {
		for (EQst e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EQst.getName(EQst.get(value), Locale.CHINA);
	}

	public static String getName(EQst e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
