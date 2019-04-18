package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 *  等级枚举，（头衔）
 * @author liushaomin
 */
public enum ELevel {
	
	one("1"),// 初学者
	two("2"),// 游学者
	three("3"),// 有学者
	four("4"),// 俊才
	fives("5"),// 初师
	six("6"),// 学师
	seven("7"),// 有道者
	eight("8"),// 有智者
	nine("9"),// 智者
	ten("10");// 仁师

	private final String value;

	private ELevel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ELevel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELevel get(String str) {
		for (ELevel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return ELevel.getName(ELevel.get(value), Locale.CHINA);
	}

	public static String getName(ELevel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
