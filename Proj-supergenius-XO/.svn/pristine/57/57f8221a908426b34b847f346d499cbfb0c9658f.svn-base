package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 裁判等级枚举类
 * @author guanshiqian
 * @date 2016-4-28 上午10:23:19 
 */
public enum EJudgementLevel {

	sanji("0"),erji("1"),yiji("2"),gaoji("3"),teji("4"),zhongji("5");

	private final String value;

	private EJudgementLevel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value; 
	}

	public static EJudgementLevel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EJudgementLevel get(String str) {
		for (EJudgementLevel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	public static int getIntLevel(EJudgementLevel e) {
		if (e == null) {
			return -1;
		}
		return Integer.parseInt(e.value);		
	}
}
