package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 性别枚举类
 * 
 * @author liubin
 */
public enum EGender {

	lady("0"), //女
	gentleman("1"),//男
	shemale("2");//人妖

	private final String value;

	private EGender(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EGender get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EGender get(String str) {
		for (EGender e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EGender getByName(String name) {
		for (EGender e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
