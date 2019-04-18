package com.supergenius.xo.finance.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 订阅状态枚举类
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:38:06
 */
public enum EFollow {
	
	published("1"),//订阅
	draft("2");    //互相订阅
	
	private final String value;

	private EFollow(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EFollow get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EFollow get(String str) {
		for (EFollow e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
