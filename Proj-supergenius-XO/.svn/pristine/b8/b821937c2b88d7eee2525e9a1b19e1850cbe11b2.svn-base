package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 用户来自渠道枚举类
 * @author chenminchang
 */
public enum EUserChannel {

	userfee("0"),//缴费会员
	specialuser("1"),//特批会员
	inviteuser("2");//邀请会员

	private final String value;

	private EUserChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserChannel get(String value) {
		for (EUserChannel e : values()) {
			if (e.toString().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
