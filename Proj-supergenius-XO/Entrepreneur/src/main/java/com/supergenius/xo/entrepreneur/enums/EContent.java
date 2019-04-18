package com.supergenius.xo.entrepreneur.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author tf
 * @date 2018年7月4日
 */
public enum EContent {
	
	ad("0"),//广告位
	banner("1"),
	html("2");//静态页面

	
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
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
