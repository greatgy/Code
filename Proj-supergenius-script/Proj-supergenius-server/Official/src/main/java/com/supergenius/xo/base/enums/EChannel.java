package com.supergenius.xo.base.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author architect.bian
 * 2014-6-24 上午11:55:24
 */
public enum EChannel {

	index("0");

	private final String value;

	private EChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EChannel get(String name) {
		for (EChannel e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
