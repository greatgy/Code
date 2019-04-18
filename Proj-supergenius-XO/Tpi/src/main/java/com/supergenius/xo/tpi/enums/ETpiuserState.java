package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * Tpiuser的状态
 * @author ShangJianguo
 */
public enum ETpiuserState {

	emailvalid("0"),// 等验证邮箱
	completeinfo("1"),// 验证邮箱通过，等待完善信息
	infovalid("2"),// 信息提交等待审核
	infofailed("3"),// 信息审核失败
	infosuccess("4"),// 信息审核成功等待缴费
	payed("5");// 完成缴费

	private final String value;
	
	private ETpiuserState(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}
	
	public static ETpiuserState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETpiuserState get(String str) {
		for (ETpiuserState e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static ETpiuserState getByName(String name) {
		for (ETpiuserState e : values()) {
			if(e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ETpiuserState e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(ETpiuserState.get(value), Locale.CHINA);
	}
}
