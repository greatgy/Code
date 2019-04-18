package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 考试状态枚举
 * @author liushaomin
 */
public enum EExamState {
	
	start("1"),//答题中
	success("2"),//考试通过
	failed("3"),//未通过
	finish("4");//已颁发证书

	private final String value;

	private EExamState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EExamState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EExamState get(String str) {
		for (EExamState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EExamState.getName(EExamState.get(value), Locale.CHINA);
	}

	public static String getName(EExamState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
