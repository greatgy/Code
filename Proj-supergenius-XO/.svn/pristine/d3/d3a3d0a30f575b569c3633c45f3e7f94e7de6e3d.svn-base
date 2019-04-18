package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 通知枚举类型
 * @author liushaomin
 */
public enum ENoticeChannel {

	recruitment("0"),//招聘
	teammoney("1");//团队中资金需求
	
	private final String value;

	private ENoticeChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ENoticeChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ENoticeChannel get(String str) {
		for (ENoticeChannel e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ENoticeChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
