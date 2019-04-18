package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * Tpiuser的状态，验证邮箱未提交信息、信息提交等待审核、信息审核未通过、审核通过等待缴费、正常、冻结
 * @author ShangJianguo
 */
public enum ETpiuserStatus {

	disable("0"),// 已冻结 
	enable("1"),// 可用 
	deleted("2"),// 已删除
	emailvalid("10"),// 等验证邮箱
	completeinfo("11"),// 验证邮箱通过，等待完善信息
	infovalid("12"),// 信息提交等待审核
	infofailed("13"),// 信息审核失败
	infosuccess("14");// 信息审核成功等待缴费

	private final String value;
	
	private ETpiuserStatus(String v) {
		this.value = v;
	}
	
	public String toString() {
		return this.value;
	}

	public static ETpiuserStatus get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ETpiuserStatus get(String str) {
		for (ETpiuserStatus e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(ETpiuserStatus e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
