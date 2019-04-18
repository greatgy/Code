package com.supergenius.xo.entrepreneur.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author tf
 * 
 */
public enum EEntrepreneurChannel {

	articlepraise("0"),//文章的赞
	contentpraise("1"),//静态页面的赞
	commentspraise("2"),//评论的赞
	articlecomments("3"),//文章评论
	contentcomments("4");//静态页面的评论

	private final String value;
	private EEntrepreneurChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EEntrepreneurChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EEntrepreneurChannel get(String str) {
		for (EEntrepreneurChannel e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}

	public static String getName(EEntrepreneurChannel e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}

	public static String getName(EEntrepreneurChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

}
