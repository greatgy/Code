package com.supergenius.xo.ai.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 文章类型枚举类
 * 
 * @author 杨光
 * @date 2017年9月26日17:58:25
 */
public enum EType {
	
	published("1"),//已发布的文章
	draft("0");//草稿箱文章
	
	private final String value;

	private EType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EType get(String str) {
		for (EType e : values()) {
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
