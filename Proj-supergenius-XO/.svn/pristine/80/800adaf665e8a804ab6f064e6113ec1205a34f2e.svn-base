package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 商品类型枚举
 * @author diaobisong
 */
public enum EGoods {

	video(1), // 视频
	ticket(2), // 门票
	lecture(6); // 讲座
//	challenge(3);// 挑战
//	reply(4),//答辩
//	secReply(5);//二次答辩
	
	private final int value;

	private EGoods(int v) {
		this.value = v;
	}

	public int getValue() {
		return value;
	}

	public static EGoods get(int v) {
		for (EGoods e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
	public static EGoods get(long v) {
		for (EGoods e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
	@Override
 	public String toString() {
 		return String.valueOf(value);
 	}
	
	public String getName(){
	    return I18N.getEnumName(this, Locale.CHINA);
	}
}
