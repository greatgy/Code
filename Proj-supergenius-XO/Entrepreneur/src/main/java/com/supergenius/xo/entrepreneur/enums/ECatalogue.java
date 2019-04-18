package com.supergenius.xo.entrepreneur.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 目录枚举类
 * 
 * @author tf
 * @date 2018年7月5日
 */
public enum ECatalogue {

	index(Integer.valueOf("00000001", 2)), // 首页1
	pattern(Integer.valueOf("00000010", 2)), // 格局2
	strategy(Integer.valueOf("000000100", 2)), // 谋断4
	control(Integer.valueOf("00001000", 2)), // 管控8
	managerPeople(Integer.valueOf("00010000", 2)), // 用人之道16
	originator(Integer.valueOf("00100000", 2)), // 创始人&继承者32
	entrepreneurTraining(Integer.valueOf("01000000", 2)), // 企业家培训64
	member(Integer.valueOf("10000000", 2)), // 会员通道128
	information(Integer.valueOf("000100000000", 2));// 资讯256

	private final int value;

	private ECatalogue(int v) {
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
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

	public static String getName(ECatalogue e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}

	/**
	 * @param education2
	 * @return
	 */
	public int plus(ECatalogue e) {
		return value | Integer.parseInt(e.toString());
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param int
	 *            type
	 * @return
	 */
	public boolean ismatch(int type) {
		return value == (value & type);
	}

	/**
	 * @param education2
	 * @return
	 */
	public int minus(ECatalogue e) {
		return value ^ Integer.parseInt(e.toString());
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