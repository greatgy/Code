package com.supergenius.xo.account.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 退款状态
 * @author YangGuang
 */
public enum ERefundState {
	
	init("0"),//初始
	agree("1"),//已支付
	success("2"),//支付成功
	failed("3");//支付异常
	
	private final String value;

	private ERefundState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ERefundState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ERefundState get(String str) {
		for (ERefundState e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(ERefundState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
