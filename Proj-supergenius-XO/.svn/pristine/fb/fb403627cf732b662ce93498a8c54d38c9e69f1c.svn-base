package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息状态
 * @author YuYingJie
 */
public enum EMsgState {

	success("0"),//已读
	init("1"),//未读
	delete("2");//已删除
	
	private final String value;

	private EMsgState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EMsgState get(int v) {
		String str = String.valueOf(v);
		for (EMsgState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EMsgState get(String name) {
		for (EMsgState e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EMsgState e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
	
	public static String getName(EMsgState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
