package com.supergenius.xo.moral.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 章节枚举类型
 * 
 * @author LiJiacheng
 */
public enum EChapter {

	one("1"), // 第一章
	two("2"), // 第二章
	three("3"),// 第三章
	four("4"),//第四章
	five("5"),//第五章
	six("6"),//第六章
	seven("7");//第七章

	private final String value;

	private EChapter(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EChapter get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EChapter get(String str) {
		for (EChapter e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return EChapter.getName(EChapter.get(value), Locale.CHINA);
	}

	public static String getName(EChapter e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

}
