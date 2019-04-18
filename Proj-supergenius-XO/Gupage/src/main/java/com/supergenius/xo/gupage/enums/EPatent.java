package com.supergenius.xo.gupage.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 专利枚举类
 * 
 * @author 
 * @date 2017年8月23日10:51:26
 */
public enum EPatent {
	
	patent("0"),//专利
	paper("1");//论文
	
	private final String value;

	private EPatent(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EPatent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EPatent get(String str) {
		for (EPatent e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
