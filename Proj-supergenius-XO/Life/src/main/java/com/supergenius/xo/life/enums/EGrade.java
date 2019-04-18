package com.supergenius.xo.life.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 年级枚举类
 * 
 * @author 杨光
 * @date 2018年5月9日12:04:54
 */
public enum EGrade {
	
	six(Integer.valueOf("000000001", 2)),//六年级1
	seven(Integer.valueOf("000000010", 2)),//七年级2
	eight(Integer.valueOf("000000100", 2)),//八年级4
	nine(Integer.valueOf("000001000", 2));//九年级8
	
	private final int value;

	private EGrade(int v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static EGrade get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EGrade get(String str) {
		for (EGrade e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}
	
	public static String getName(EGrade e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param int type
	 * @return
	 */
	public boolean ismatch(int type) {
		return value == (value & type);
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param EFinance
	 *            type
	 * @return
	 */
	public static boolean ismatch(int v, EGrade type) {
		return v == (v | Integer.valueOf(type.toString()));
	}

	/**
	 * @param education2
	 * @return
	 */
	public Integer plus(EGrade e) {
		return value | Integer.valueOf(e.toString());
	}

	/**
	 * @param education2
	 * @return
	 */
	public int minus(EGrade e) {
		return value ^ Integer.valueOf(e.toString());
	}

	/**
	 * 得到最先匹配的类型
	 * 
	 * @param type
	 */
	public static List<EGrade> getMatch(int type) {
		List<EGrade> list = new ArrayList<>();
		for (EGrade item : EGrade.values()) {
			if (item.ismatch(type)) {
				list.add(item);
			}
		}
		return list;
	}
}
