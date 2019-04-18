package com.supergenius.xo.entrepreneur.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 模块枚举类
 * 
 * @author tf
 * @date 2018年7月5日
 */
public enum ECatalogueType {
	
	none("0"),//无类别
	article("1");//文章
	private final String value;

	private ECatalogueType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECatalogueType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogueType get(String str) {
		for (ECatalogueType e : values()) {
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
