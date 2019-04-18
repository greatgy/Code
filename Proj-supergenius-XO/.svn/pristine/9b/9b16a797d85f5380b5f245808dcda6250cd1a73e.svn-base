package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 用户缴费方式（来源）
 * @author LiuXiaoke
 */
public enum EPayType {
	
	normal("0"),//正常缴费方式
	offlinepaypermit("1"),//线下交易特批
	nopaypermit("2");//未缴费特批
	
private final String value;
	
	private EPayType(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}
	
	public static EPayType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EPayType get(String str) {
		for (EPayType e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EPayType getByName(String name) {
		for (EPayType e : values()) {
			if(e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EPayType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(EPayType.get(value), Locale.CHINA);
	}
	
}
