package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 *  下载类型枚举
 * @author liushaomin
 */
public enum EDoc{

	userdoc("0"),//学员上传
	doc("1"),//网站讲义
	cases("2");//案例
	
	private final String value;

	private EDoc(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EDoc get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EDoc get(String str) {
		for (EDoc e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EDoc e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
