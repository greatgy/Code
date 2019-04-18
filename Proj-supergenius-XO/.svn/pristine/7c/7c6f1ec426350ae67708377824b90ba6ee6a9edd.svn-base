package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 项目推荐资格的枚举
 * @author ShangJianguo
 */
public enum EProjectChannel {
	
	supergenius("0"),//超天才网推荐 
	agency("1"),//机构推荐
	personal("2");//个人推荐
	
	private final String value;
	
	private EProjectChannel(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EProjectChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EProjectChannel get(String str) {
		for (EProjectChannel e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EProjectChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
