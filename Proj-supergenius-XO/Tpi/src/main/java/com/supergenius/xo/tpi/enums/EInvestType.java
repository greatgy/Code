package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 投资机构类别
 * 
 * @author ShangJianguo
 */
public enum EInvestType {

	vip("0"), // VIP投资机构
	professional("1"),// 专业投资机构
	legal("2"),// 法人投资机构
	individual("3");// 私人投资机构

	private final String value;

	private EInvestType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EInvestType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EInvestType get(String str) {
		for (EInvestType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EInvestType getByName(String name) {
		for (EInvestType e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EInvestType.getName(EInvestType.get(value), Locale.CHINA);
	}
	
	public static String getName(EInvestType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
