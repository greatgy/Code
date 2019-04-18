package com.supergenius.xo.sudokuapi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 充值状态枚举类
 * @author YuYingJie
 */
public enum ERecharge {

	no("0"),//未充值
	is("1");//已充值
	
	private final String value;

	private ERecharge(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ERecharge get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ERecharge get(String str) {
		for (ERecharge e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return ERecharge.getName(ERecharge.get(value), Locale.CHINA);
	}
	
	public static String getName(ERecharge e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
}
