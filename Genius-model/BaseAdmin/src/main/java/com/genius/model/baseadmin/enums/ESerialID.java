package com.genius.model.baseadmin.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author architect.bian
 *
 */
public enum ESerialID {

	ordersn("0"), workordersn("1");

	private final String value;

	private ESerialID(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ESerialID get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ESerialID get(String str) {
		for (ESerialID e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ESerialID e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
