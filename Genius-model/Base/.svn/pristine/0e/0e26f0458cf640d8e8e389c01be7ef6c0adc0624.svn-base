package com.genius.model.base.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author Architect.bian
 *
 */
public enum EStatus {
	
	disable("0"), enable("1"), deleted("2"),
	init("10"), start("11"), wait("12"), end("13");

	private final String value;
	
	private EStatus(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EStatus get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EStatus get(String str) {
		for (EStatus e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static String getName(EStatus e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
