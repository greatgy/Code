package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 项目进度
 * 
 * @author ShangJianguo
 */
public enum EProjectState {
	
	unpay("0"),//未付款 
	payed("1");//已付款

	private final String value;

	private EProjectState(String v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static EProjectState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}
	
	public static EProjectState get(String str) {
		for (EProjectState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 根据name得到values
	 * 
	 * @param name
	 * @return
	 */
	public static EProjectState getByValues(String name) {
		for (EProjectState e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 根据values得到name
	 * 
	 * @param str
	 * @return
	 */
	public static String getByName(String str) {
		for (EProjectState e : values()) {
			int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e.name();
			}
		}
		return null;
	}

	public String getTypeName() {
		return EProjectState.getName(EProjectState.get(value), Locale.CHINA);
	}

	public static String getName(EProjectState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
