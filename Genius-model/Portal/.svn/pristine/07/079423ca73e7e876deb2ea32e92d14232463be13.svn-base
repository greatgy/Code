package com.genius.model.portal.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 模板类型
 * 
 * @author architect.bian
 * 2014-6-24 上午11:52:46
 */
public enum ETmpl {

	none("0"), format("1"), freemarker("2");

	private final String value;

	private ETmpl(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ETmpl get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETmpl get(String name) {
		for (ETmpl e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(ETmpl e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
