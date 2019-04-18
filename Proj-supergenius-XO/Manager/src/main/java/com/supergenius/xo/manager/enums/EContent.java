package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 网站内容类型枚举
 * @author XieMing
 * @date 2016-5-3 上午11:22:16
 */
public enum EContent {

	managerIndex("0"),//职业经理人首页
	relus("1");//学员培训细则

	private final String value;

	private EContent(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EContent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EContent get(String str) {
		for (EContent e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EContent e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName(){
	    return I18N.getEnumName(this, Locale.CHINA);
	}
}
