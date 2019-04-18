package com.supergenius.xo.manager.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 会议室类型枚举
 * @author XieMing
 * @date 2016-5-3 上午10:23:29
 */
public enum EConfer {

	challenge("0"),//挑战
	reply("1"),//答辩
	judgment("2"),//裁判
	expert("3");//专家

	private final String value;

	private EConfer(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EConfer get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EConfer get(String str) {
		for (EConfer e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取会议室的所有类型
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10上午11:50:06
	 */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> major = new HashMap<String, String>();
		for (EConfer eConfer : EConfer.values()) {
			major.put(eConfer.toString(), eConfer.getName());
		}
		return major;
    }
}
