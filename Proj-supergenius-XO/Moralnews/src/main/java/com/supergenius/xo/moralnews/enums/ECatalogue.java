package com.supergenius.xo.moralnews.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 目录枚举类
 * 
 * @author YangGuang
 * @date 2018年5月14日17:48:03
 */
public enum ECatalogue {

	index(Integer.valueOf("00000001", 2)), // 首页1
	absenceCriticism (Integer.valueOf("00000010", 2)), // 职业道德缺失与批判2
	execution(Integer.valueOf("00000100", 2)), // 职业道德与执行力4
	credit(Integer.valueOf("00001000", 2)), // 职业道德与信用8
	strategyFraud(Integer.valueOf("00010000", 2)), // 商业谋略与欺诈16
	loyaltyParadox(Integer.valueOf("00100000", 2)), // 忠诚及其悖论32
	trainingExamination(Integer.valueOf("01000000", 2)), // 职业道德培训与考试64
	community(Integer.valueOf("000100000000", 2));// 职业道德社区256

	private final int value;

	private ECatalogue(Integer v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ECatalogue get(Integer v) {
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
	public Integer plus(ECatalogue e) {
		return value | Integer.parseInt(e.toString());
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param Integer
	 *            type
	 * @return
	 */
	public boolean ismatch(Integer type) {
		return value == (value & type);
	}

	/**
	 * @param education2
	 * @return
	 */
	public Integer minus(ECatalogue e) {
		return value ^ Integer.parseInt(e.toString());
	}

	/**
	 * 得到最先匹配的类型
	 * 
	 * @param type
	 */
	public static List<ECatalogue> getMatch(Integer type) {
		List<ECatalogue> list = new ArrayList<>();
		for (ECatalogue item : ECatalogue.values()) {
			if (item.ismatch(type)) {
				list.add(item);
			}
		}
		return list;
	}
}
