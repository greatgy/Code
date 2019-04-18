package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/** 
 * 类型枚举类型
 * @author guanshiqian
 * @date 2016-4-27 下午4:52:14 
 */
public enum ECount {
	
	vote("0"),//投票
	prize("1");//点赞
	
	private final String value;

	private ECount(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECount get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECount get(String str) {
		for (ECount e : values()) {
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
