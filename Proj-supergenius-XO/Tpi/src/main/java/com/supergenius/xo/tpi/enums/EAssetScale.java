package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 资产规模
 * @author ShangJianguo
 */
public enum EAssetScale {
	
	level1("1"),// 一亿一下
	level2("2"),// 1-5
	level3("3"),// 5-10
	level4("4"),// 10-100
	level5("5");// 100 以上
	
	private final String value;
	
	private EAssetScale (String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}

	public static EAssetScale get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAssetScale get(String str) {
		for (EAssetScale e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EAssetScale getByName(String name) {
		for (EAssetScale e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * 根据v得到name
	 * @param type
	 * @return
	 * @author liushaomin
	 */
	public static String getByValues(EAssetScale type) {
		for (EAssetScale e : values()) {
			if (e.equals(type)) {
				return e.name();
			}
		}
		return null;
	}
	
	public String getName() {
		return EAssetScale.getName(EAssetScale.get(value), Locale.CHINA);
	}
	
	public static String getName(EAssetScale e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

	public static String getName(EAssetScale e) {
		return getName(e, Locale.CHINA);
	}
}
