package com.supergenius.xo.account.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 充值状态
 * @author liushaomin
 */
public enum EState {
	
	init("0"),//初始
	pay("1"),//已支付
	success("2"),//支付成功
	notifyex("3"),//支付异常
	failed("4");//支付异常
	
	private final String value;

	private EState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EState get(String str) {
		for (EState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
