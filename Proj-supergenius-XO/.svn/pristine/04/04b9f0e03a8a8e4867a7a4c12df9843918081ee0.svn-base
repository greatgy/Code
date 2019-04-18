package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 邮件模板类型
 * @author LiuXiaoke
 */
public enum ESysEmailType {
	
	registernoticetouser("0"),//用户注册通知用户
	registernoticeadmin("1"),//用户注册通知管理员
	registervalidate("2"),//用户注册邮箱验证
	resetpasswordvalidate("3"),//用户重置密码邮箱验证
	registercheckresult("4");//用户账户审核结果
	
	private final String value;

	private ESysEmailType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ESysEmailType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ESysEmailType get(String str) {
		for (ESysEmailType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ESysEmailType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return ESysEmailType.getName(ESysEmailType.get(value), Locale.CHINA);
	}
	
	public static String getName(ESysEmailType e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
	
	public static ESysEmailType getByName(String name) {
		for (ESysEmailType e : values()) {
			if(e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
}
