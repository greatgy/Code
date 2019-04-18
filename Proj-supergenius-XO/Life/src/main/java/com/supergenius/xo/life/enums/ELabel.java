package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论枚举类
 * 
 * @author YangGuang
 * @date 2018年5月17日12:15:51
 */
public enum ELabel {
	
	piano("0"),//钢琴
	dance("1"),//舞蹈
	violin("2"),//小提琴
	painting("3"),//绘画
	theatre("4"),//戏剧
	calligraphy("5"),//书法
	music("6"),//音乐
	judo("7"),//柔道
	recitation("8"),//朗诵
	sculpture("9"),//雕塑
	swimming("10"),//游泳
	gymnastics("11"),//体操
	skating("12"),//溜冰
	skiing("13"),//滑雪
	
	none("20");//滑雪
	
	private final String value;

	private ELabel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ELabel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELabel get(String str) {
		for (ELabel e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static String getName(ELabel e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
