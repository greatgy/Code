package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 密保问题枚举类
 * @author LiuBin
 * @date 2016-3-23 下午5:05:52 
 */
public enum EQuestion {
	
	fathername("1"), mothername("2"), wifename("3"), sonname("4"), daughtername("5"),
	fatherbirthday("11"),motherbirthday("12"), wifebirthday("13"),sonbirthday("14"),daughterbirthday("15");

	private final String value;

	private EQuestion(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EQuestion get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EQuestion get(String str) {
		for (EQuestion e : values()) {
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
