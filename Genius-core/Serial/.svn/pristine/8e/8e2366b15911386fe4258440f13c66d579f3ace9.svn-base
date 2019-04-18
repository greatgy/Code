package com.genius.core.serial.mock.testenums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 状态枚举类型，可使用Status.get(1)获得对应的值
 * 
 * @author architect.bian
 * @createtime 2014-8-27 下午7:46:27
 */
public enum EStatus {
	
	disable("0"), enable("1"), deleted("2"),
	init("10"), start("11"), wait("12"), end("13");

	private final String value;
	
	private EStatus(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EStatus get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EStatus get(String str) {
		for (EStatus e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EStatus e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
