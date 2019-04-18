package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 视频状态枚举类
 * 
 * @author 杨光
 * @date 2018年5月9日12:04:54
 */
public enum EState {
	
	wait("0"),//待点评
	waitReply("1"),//待回复
	over("2");//已点评

	
	private final String value;

	private EState(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EState get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EState get(String str) {
		for (EState e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
