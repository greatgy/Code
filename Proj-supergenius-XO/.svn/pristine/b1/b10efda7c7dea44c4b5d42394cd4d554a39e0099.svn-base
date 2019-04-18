package com.supergenius.xo.sudokuapi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 段位级别枚举
 * 
 * @author LiJiacheng
 */
public enum EGrade {

	one(0), // 新手
	two(1), // 一段
	three(2), // 二段
	four(3), // 三段
	five(4), // 四段
	six(5), // 五段
	seven(6), // 六段
	eight(7), // 七段
	nine(8), // 八段
	ten(9);// 九段

	private final int value;

	private EGrade(int v) {
		this.value = v;
	}

	public String toString() {
		return this.value + "";
	}

	public static EGrade get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EGrade get(String str) {
		for (EGrade e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EGrade.getName(EGrade.get(value), Locale.CHINA);
	}

	public static String getName(EGrade e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
