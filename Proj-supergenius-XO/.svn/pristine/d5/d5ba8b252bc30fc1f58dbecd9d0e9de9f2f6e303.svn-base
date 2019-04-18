package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 网站内容枚举
 * @author liushaomin
 */
public enum EContent {
	
	joinus("0"),//加入我们 
	contactus("1"),//联想我们
	aboutus("2"),//关于我们
	privacy("3"),//隐私条款
	service("4");//服务条款
	
	
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
