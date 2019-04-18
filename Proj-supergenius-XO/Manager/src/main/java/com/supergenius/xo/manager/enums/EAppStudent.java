package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 申请学员枚举类
 * @author chenminchang
 * @date 2016-3-21 下午3:56:53 
 */
public enum EAppStudent {
	
	manager("0"),//职业经理人申请
	entrepreneur("1");//企业家培训申请

	private final String value;

	private EAppStudent(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EAppStudent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAppStudent get(String str) {
		for (EAppStudent e : values()) {
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
