package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;


/**
 * 意愿明细的枚举类型
 * @author liushaomin
 */
public enum EWishType {
	
	attention("0"),//支持[关注]Praise
	wantMergers("1"),//想并购
	wantInvestment("2"),//想投资
	recommend("3");//推荐
	
	private final String value;

	private EWishType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EWishType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EWishType get(String str) {
		for (EWishType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EWishType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
