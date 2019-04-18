package com.supergenius.xo.career.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 内容枚举类
 * 
 * @author yangguang
 * @date 2017年11月14日12:15:59
 */
public enum EType {
	
	research("1"),//研究型
	convention("2"),//常规型
	art("3"),//艺术型
	enterprise("4"),//企业型
	reality("5"),//现实型
	sociology("6");//社会型
	
	private final String value;

	private EType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EType get(String str) {
		for (EType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
