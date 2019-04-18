package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 招聘信息来源的枚举
 * @author liushaomin
 */
public enum ENoticeType {
	
	team("0"),//团队
	recommendedagency("1"),//推荐机构
	investmentagency("2"),//投资机构
	mergersagency("3");//并购机构
	
	private final String value;

	private ENoticeType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ENoticeType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ENoticeType get(String str) {
		for (ENoticeType e : values()) {
			if(e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(ENoticeType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
