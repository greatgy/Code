package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 名人名言枚举
 * @author chenminchang
 * @date 2016-8-11 下午5:45:35 
 */
public enum EQuotes {
	
	global("0"),//全站名人名言
	finance("1");//finance模块名言
	
	private final String value;

	private EQuotes(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EQuotes get(int v) {
		String str = String.valueOf(v);
		for (EQuotes e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EQuotes get(String name) {
		for (EQuotes e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EQuotes e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
