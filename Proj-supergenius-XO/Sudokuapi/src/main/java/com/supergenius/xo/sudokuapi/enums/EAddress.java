package com.supergenius.xo.sudokuapi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 分享位置枚举
 * 
 * @author LiJiacheng
 */
public enum EAddress {
	homepage(1), //首页
	singlegameover(2), //单机游戏结束
	computergameover(3); //人机游戏结束

	private final int value;

	private EAddress(int v) {
		this.value = v;
	}

	public String toString() {
		return this.value + "";
	}

	public static EAddress get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAddress get(String str) {
		for (EAddress e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EAddress getByName(String name) {
		for(EAddress e : values()) {
			if((get(e.value).getName()).equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EAddress.getName(EAddress.get(value), Locale.CHINA);
	}

	public static String getName(EAddress e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
