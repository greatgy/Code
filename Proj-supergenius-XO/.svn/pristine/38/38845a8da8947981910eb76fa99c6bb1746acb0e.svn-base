package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 分数类型
 * @author liubin
 * @date 2016-7-18 下午2:12:43 
 */
public enum EScore {
	
	originalFinance("0"),//原创文章
	forwardFinance("1"),//转载文章
	scoreUpGrade("2");//积分晋级（花费积分）
	
	private final String value;

	private EScore(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EScore get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EScore get(String value) {
		for (EScore e : values()) {
			if (e.toString().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获得增加积分的类型
	 * @return
	 * @author chenminchang
	 */
	public static EScore[] getAddScoreTypes(){
		EScore[] types = {EScore.originalFinance, EScore.forwardFinance};
		return types;
	}
}
