package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 充值类型枚举
 * @author liushaomin
 */
public enum ECharge {

	recharge("0"),//充值
	userfee("1");//会员费
	
	private final String value;

	private ECharge(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECharge get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECharge get(String str) {
		for (ECharge e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return ECharge.getName(ECharge.get(value), Locale.CHINA);
	}
	
	public static String getName(ECharge e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
