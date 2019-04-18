package com.supergenius.xo.finance.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 目录枚举类
 * 
 * @author XueZhenYong
 * @date 2018年1月3日下午3:26:07
 */
public enum ECatalogue {
	economics(Integer.valueOf("000000001", 2)), // 财经资讯1
	invest(Integer.valueOf("000000010", 2)), // 投资资讯2
	merger(Integer.valueOf("000000100", 2)), // 并购资讯4
	risk(Integer.valueOf("000001000", 2)), // 风险资讯8
	technology(Integer.valueOf("000010000", 2)), // 科技资讯16
	gold(Integer.valueOf("000100000", 2)), // 醒世金语32
	entrepreneur(Integer.valueOf("001000000", 2)), // 企业家64
	profession(Integer.valueOf("010000000", 2)), // 职业经理人128
	nightwords(Integer.valueOf("100000000", 2)); // 职场夜话256

	private final int value;

	private ECatalogue(Integer v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ECatalogue get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogue get(String str) {
		for (ECatalogue e : values()) {
			int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e;
			}
		}
		return null;
	}
	
	public static ECatalogue getByName(String name) {
		for (ECatalogue e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return ECatalogue.getName(ECatalogue.get(value), Locale.CHINA);
	}

	public static String getName(ECatalogue e, Locale locale) {
		return I18N.getEnumName(e, locale);
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
	public static boolean ismatch(int v, ECatalogue type) {
		return v == (v | Integer.valueOf(type.toString()));
	}

	/**
	 * @param education2
	 * @return
	 */
	public Integer plus(ECatalogue e) {
		return value | Integer.valueOf(e.toString());
	}

	/**
	 * @param education2
	 * @return
	 */
	public int minus(ECatalogue e) {
		return value ^ Integer.valueOf(e.toString());
	}

	/**
	 * 得到最先匹配的类型
	 * 
	 * @param type
	 */
	public static List<ECatalogue> getMatch(int type) {
		List<ECatalogue> list = new ArrayList<>();
		for (ECatalogue item : ECatalogue.values()) {
			if (item.ismatch(type)) {
				list.add(item);
			}
		}
		return list;
	}
}
