package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文档枚举类型
 * 
 * @author LiJiacheng
 */
public enum EExerciseQstType {

	nottodoqst("0"), // 未做试题
	havetodoqst("1"), // 已做试题
	errqst("2");// 错误试题
	
	private final String value;

	private EExerciseQstType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EExerciseQstType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EExerciseQstType get(String str) {
		for (EExerciseQstType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EExerciseQstType.getName(EExerciseQstType.get(value), Locale.CHINA);
	}

	public static String getName(EExerciseQstType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
