package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 频道页或栏目页
 * 
 * @author YangGuang
 * 2014-6-24 上午11:55:24
 */
public enum ELifeChannel {

	articlepraise("0"),//文章的赞
	topicpraise("1"),//话题的赞
	videopraise("2"),//视频的赞
	contentpraise("3"),//静态页面的赞
	commentspraise("4"),//评论的赞
	articlecomments("5"),//文章评论
	topiccomments("6"),//话题评论
	videocomments("7"),//视频评论
	contentcomments("8"),//静态页面的评论
	problempraise("9"),//问题的赞
	answerpraise("10"),//回答的赞
	problemcomments("11"),//问题的回答
	essay("12");//动态

	private final String value;
	private ELifeChannel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static ELifeChannel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELifeChannel get(String str) {
		for (ELifeChannel e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}

	public static String getName(ELifeChannel e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}

	public static String getName(ELifeChannel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

}
