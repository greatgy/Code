package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;
/**
 * 评论type枚举类
 * @author liushaomin
 */
public enum EComment {
	
	comment("0"),//评论
	prize("1");//赞
	
	private final String value;

	private EComment(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EComment get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EComment get(String str) {
		for (EComment e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EComment e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
