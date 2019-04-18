package com.supergenius.xo.tpi.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 并购机构类别
 * 
 * @author ShangJianguo
 */
public enum EMergeType {

	international("0"), // 跨国并购机构
	nationalvip("1"),// 国内VIP并购机构
	nationallarge("2"),// 国内大型并购机构
	nationalmedium("3"),//  国内中型并购机构
	nationalsmall("4");// 国内小型并购机构

	private final String value;

	private EMergeType(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EMergeType get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EMergeType get(String str) {
		for (EMergeType e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public static EMergeType getByName(String name) {
		for (EMergeType e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return EMergeType.getName(EMergeType.get(value), Locale.CHINA);
	}
	
	public static String getName(EMergeType e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
