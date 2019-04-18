package com.genius.xo.mongodb.mock.model;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

public enum ELibrary {
	
	country("0"), city("1"), college("2");

	private final String value;
	
	private ELibrary(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static ELibrary get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELibrary get(String str) {
		for (ELibrary e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ELibrary e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
