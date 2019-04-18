package com.genius.model.sns.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;


/**
 * 性别枚举类型
 * 
 * @author architect.bian
 * @createtime 2014-8-28 下午2:10:12
 */
public enum EGender {
	
	lady("0"), gentleman("1"), shemale("2");

	private final String value;
	
	private EGender(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EGender get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EGender get(String str) {
		for (EGender e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EGender e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
