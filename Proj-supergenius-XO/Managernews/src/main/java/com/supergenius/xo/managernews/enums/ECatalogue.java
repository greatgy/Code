package com.supergenius.xo.managernews.enums;

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
	visionPattern(Integer.valueOf("00000010", 2)), // 视野与格局2
	managementPlan(Integer.valueOf("00000100", 2)), // 管理与谋划4
	salaryIncentive(Integer.valueOf("00001000", 2)), // 薪酬与激励8
	promotionJob(Integer.valueOf("00010000", 2)), // 升迁与跳槽16
	fashionLife(Integer.valueOf("00100000", 2)), // 时尚生活32
	managerTraining(Integer.valueOf("01000000", 2)), // 职业经理人培训64
	member(Integer.valueOf("10000000", 2)), // 会员通道128
	information(Integer.valueOf("000100000000", 2));// 资讯256

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