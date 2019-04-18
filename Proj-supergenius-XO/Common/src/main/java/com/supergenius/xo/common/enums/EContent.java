package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 网站内容枚举类型，广告 关于我门等等
 * @author Architect.bian
 *
 */
public enum EContent {

	Companyintroduce("0"),//公司简介
	Aboutus("1"),//关于我们
	Joinus("2"),//加入我们
	Contactus("3");//联系我们
	
	private final String value;
	
	private EContent(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EContent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EContent get(String str) {
		for (EContent e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EContent e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
