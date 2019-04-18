package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;
/**
 * 评论type枚举类
 * @author liushaomin
 */
public enum ECommentType {
	
	comment("0"),//评论
	prize("2");//赞
	
	private final String value;

	private ECommentType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECommentType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECommentType get(String str) {
		for (ECommentType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(ECommentType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
