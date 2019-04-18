package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 支付状态枚举
 * @author YuYingJie
 */
public enum EAccountState {
	
	init("0"),//初始状态
	finish("1"),//完成支付
	failed("2");//支付失败
	
	
	private final String value;
	
	private EAccountState(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EAccountState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAccountState get(String str) {
		for (EAccountState e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EAccountState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
