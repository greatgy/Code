package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

public enum EUserAisle {
	
	mobile("0"),//手机号注册
	mailbox("1"),//邮箱注册
	qq("2"),//qq
	wx("3"),//微信
	sina("4");//微博
	
	private final String value;

	private EUserAisle(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EUserAisle get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EUserAisle get(String value) {
		for (EUserAisle e : values()) {
			if (e.toString().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	public static EUserAisle getByName(String name) {
		for (EUserAisle e : values()) {
			if (e.name().toString().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
