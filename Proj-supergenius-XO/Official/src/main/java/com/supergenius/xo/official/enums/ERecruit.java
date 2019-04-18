package com.supergenius.xo.official.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 招聘枚举
 * @author liushaomin
 */
public enum ERecruit {

	finance("0"), //财务
	humanresources("1"),//人力资源
	administration("2"),//行政
	project("3"),//项目
	quality("4"),//质量
	seniormanagement("5"),//高级管理
	IT("6"),//IT
	Internet("7"),//联网
	signalcommunication("8"),//通信
	Finance("9"),//金融
	consultation("10"),//咨询
	law("11"),//法律
	education("12"),//教育
	translation("13"),//翻译
	scientificresearchpersonnel("14"),//科研人员
	trainee("15");//实习生

	private final String value;

	private ERecruit(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ERecruit get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ERecruit get(String str) {
		for (ERecruit e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return ERecruit.getName(ERecruit.get(value), Locale.CHINA);
	}
	
	public static String getName(ERecruit e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
