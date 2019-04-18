package com.supergenius.xo.sudokuapi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 游戏项目专用的Status枚举类，增加了EMPTY，WAITING，ONGOING，OVER，DESTROYED等状态
 * @author ChenQi
 *
 */
public enum EGameStatus {
	
	disable("0"), enable("1"), deleted("2"),
	init("10"), start("11"), wait("12"), end("13"),
	empty("20"), waiting("21"), ongoing("22"), over("23"), destroyed("24");

	private final String value;
	
	private EGameStatus(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static EGameStatus get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EGameStatus get(String str) {
		for (EGameStatus e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EGameStatus getByName(String name) {
		for (EGameStatus e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static String getName(EGameStatus e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
