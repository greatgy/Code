package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 交易类别（账户注册，推荐项目等）
 * 
 * @author ShangJianguo
 */
public enum EAccountDetailType {

	registe("0"), // 账户注册(一次性缴费)
	project("1");// 推荐项目

	private final String value;

	private EAccountDetailType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EAccountDetailType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAccountDetailType get(String str) {
		for (EAccountDetailType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EAccountDetailType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
