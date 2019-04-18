package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 考试枚举类型
 * 
 * @author LiJiacheng
 */
public enum EExam {

	exam("1"), // 考试
	exercise("2");// 模拟练习

	private final String value;

	private EExam(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EExam get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EExam get(String str) {
		for (EExam e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EExam.getName(EExam.get(value), Locale.CHINA);
	}

	public static String getName(EExam e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
