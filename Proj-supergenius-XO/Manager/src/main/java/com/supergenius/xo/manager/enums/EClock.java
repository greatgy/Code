package com.supergenius.xo.manager.enums;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.genius.core.base.utils.I18N;

/**
 * 时间枚举
 * 
 * @author liubin
 * @createtime 2016-7-25 下午3:12:03
 */
public enum EClock {

	eightClock("0"), // 8:00
	nineClock("1"), // 9:00
	tenClock("2"), // 10:00
	elevenClock("3"), // 11:00
	twelveClock("4"), // 12:00
	thirteenClock("5"), // 13:00
	fourteenClock("6"), // 14:00
	fiveteenClock("7"), // 15:00
	sixteenClock("8"), // 16:00
	seventeenClock("9"), // 17:00
	eighteenClock("10"), // 18:00
	nineteenClock("11"), // 19:00
	twentyClock("12"), // 20:00
	twentyoneClock("13"), // 21:00
	twentytwoClock("14");// 22:00

	private final String value;

	private EClock(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EClock get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EClock get(String str) {
		for (EClock e : values()) {
			if (e.value.equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static String getName(EClock e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

	public static Map<String, String> getEClock() {
		Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String str1, String str2) {
				return Integer.valueOf(str1).compareTo(Integer.valueOf(str2));
			}
		});
		for (EClock e : EClock.values()) {
			map.put(e.toString(), e.getName());
		}
		return map;
	}
}
