package com.supergenius.xo.enterpriser.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论枚举类
 * 
 * @author loupengyu
 * @date 2018年1月30日13:46:58
 */
public enum EComment {
	
	comment("0"),//评论
	praise("1");//赞
	
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
