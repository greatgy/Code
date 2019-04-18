package com.genius.model.baseadmin.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author liushaomin
 *
 */
public enum EWorkStage {
	
	last("0"), first("1"), second("2"), third("3"), fourth("4"), fifth("5"), sixth ("6"), seventh("7"), eighth("8"), ninth("9"), tenth("10");
	
	private final String value;

	private EWorkStage(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EWorkStage get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}
	
	public static EWorkStage get(String str) {
		for (EWorkStage e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String get(EWorkStage eworkstage) {
		for (EWorkStage e : values()) {
			if (e.equals(eworkstage)) {
				return e.name();
			}
		}
		return null;
	}

	public static String getName(EWorkStage e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
