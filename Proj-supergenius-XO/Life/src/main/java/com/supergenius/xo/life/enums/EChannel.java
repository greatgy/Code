package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author YangGuang
 * 2014-6-24 上午11:55:24
 */
public enum EChannel {

	article("0"),
	comments("1");

	private final String value;
	private EChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EChannel get(int v) {
		String str = String.valueOf(v);
		for (EChannel e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}

	public static EChannel get(String name) {
		for (EChannel e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EChannel e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}

	public static String getName(EChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

}
