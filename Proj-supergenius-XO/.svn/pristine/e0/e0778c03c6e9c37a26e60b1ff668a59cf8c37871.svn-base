package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 员工人数
 * @author ShangJianguo
 */
public enum EStaffScale {
	
	level1("1"),// 100下
	level2("2"),// 100-500
	level3("3"),// 500-1000
	level4("4");// 1000 以上
	
	private final String value;
	
	private EStaffScale (String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
	

	public static EStaffScale get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EStaffScale get(String str) {
		for (EStaffScale e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EStaffScale getByName(String name) {
		for (EStaffScale e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EStaffScale.getName(EStaffScale.get(value), Locale.CHINA);
	}
	
	public static String getName(EStaffScale e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

	public static String getName(EStaffScale e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
}
