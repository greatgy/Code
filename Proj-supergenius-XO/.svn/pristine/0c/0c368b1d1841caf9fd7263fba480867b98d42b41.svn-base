package com.supergenius.xo.startup.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 性别枚举类
 * 
 * @author liubin
 */
public enum ERuler {

	entrepreneurshiptest("0"),//创业测试
	other("10");//其他测试

	private final String value;

	private ERuler(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ERuler get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ERuler get(String str) {
		for (ERuler e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static ERuler getByName(String name) {
		for (ERuler e : values()) {
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
