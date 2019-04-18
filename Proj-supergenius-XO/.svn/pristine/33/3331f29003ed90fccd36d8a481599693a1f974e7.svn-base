package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 *	评论type枚举类
 * @author liushaomin
 */
public enum EComment {
	
	comment("0"),//评论
	praise("1"),//赞
	debatevote("2");//论战投票
	
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
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
