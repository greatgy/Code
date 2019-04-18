package com.supergenius.xo.user.enums;

import com.genius.core.base.utils.I18N;

import java.util.Locale;

/**
 * 消息枚举类
 * 
 * @author YangGuang
 * @date 2018年5月9日15:57:27
 */
public enum EUserMsg {
	
	sys("0");//系统消息

	private final String value;

	private EUserMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserMsg get(String str) {
		for (EUserMsg e : values()) {
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
