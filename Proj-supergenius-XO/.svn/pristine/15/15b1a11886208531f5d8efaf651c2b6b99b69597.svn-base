package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 门票状态枚举
 * @author XieMing
 * @date 2016-5-3 上午11:17:28
 */
public enum ETicket {

	waiting("0"), start("1"), end("2");//1等待，2开始，3结束

	private final String value;

	private ETicket(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ETicket get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETicket get(String str) {
		for (ETicket e : values()) {
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
