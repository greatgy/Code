package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文档枚举类型
 * 
 * @author LiJiacheng
 */
public enum EExerciseType {

	single("0"), // 基础理解分析题
	material("1");// 案例总合分析题
	
	private final String value;

	private EExerciseType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EExerciseType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EExerciseType get(String str) {
		for (EExerciseType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EExerciseType.getName(EExerciseType.get(value), Locale.CHINA);
	}

	public static String getName(EExerciseType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
