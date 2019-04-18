package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 置顶枚举类
 * 
 * @author 杨光
 * @date 2017年9月26日17:58:56
 */
public enum ETop {
	
	istop("1"),//已置顶
	nottop("0");//未置顶
	
	private final String value;

	private ETop(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ETop get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETop get(String str) {
		for (ETop e : values()) {
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
