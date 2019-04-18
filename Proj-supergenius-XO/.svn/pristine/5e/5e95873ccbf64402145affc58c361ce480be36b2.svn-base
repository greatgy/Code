package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 推荐机构类别: 政府机构、公司
 * 
 * @author ShangJianguo
 */
public enum ERecommendType {

	government("0"), // 政府机构
	company("1");// 公司

	private final String value;

	private ERecommendType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ERecommendType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ERecommendType get(String str) {
		for (ERecommendType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return ERecommendType.getName(ERecommendType.get(value), Locale.CHINA);
	}
	
	public static String getName(ERecommendType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
