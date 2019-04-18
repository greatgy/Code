package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 模块类型枚举类
 * 
 * @author ChenQi
 * @date 2018年5月9日18:33:09
 */
public enum ECatalogueType {
	
	none("0"),//无类别
	article("1"),//文章
	topic("2"),//话题
	video("3");//视频
	
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
