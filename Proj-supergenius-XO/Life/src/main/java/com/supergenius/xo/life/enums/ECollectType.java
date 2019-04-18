package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 目录枚举类
 * 
 * @author YangGuang
 * @date 2018年5月14日17:48:03
 */
public enum ECollectType {
	
	article("0"),//文章
	topic("1"),//话题
	video("2");//视频
	
	private final String value;

	private ECollectType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECollectType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECollectType get(String str) {
		for (ECollectType e : values()) {
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
