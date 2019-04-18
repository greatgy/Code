package com.genius.model.baseadmin.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author liushaomin
 *
 */
public enum EWorkOrder {

	useraccount("0");
	
	private final String value;

	private EWorkOrder(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EWorkOrder get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EWorkOrder get(String str) {
		for (EWorkOrder e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EWorkOrder e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
