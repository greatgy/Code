package com.supergenius.xo.life.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 投诉举报类型枚举
 * @author XieMing
 * @date 2018年5月9日16:39:09
 */
public enum EComplaint {

	ad("0"),//广告
	cheat("1"),//欺诈骗钱
	sexy("2"),//色情
	Politics("3"),//政治
	violence("4"),//暴力
	insult("5"),//侮辱诋毁
	other("6");//其他

	private final String value;

	private EComplaint(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EComplaint get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EComplaint get(String str) {
		for (EComplaint e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			} 
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取所有举报类型用于页面下拉框显示
	 * @return
	 * @author XieMing
	 * 2016-8-16 上午11:25:23
	 */
	public static Map<String, String> getChinaNames() {
		Map<String, String> map = new HashMap<String, String>();
		for (EComplaint eComplaint : EComplaint.values()) {
			map.put(eComplaint.toString(), eComplaint.getName());
		}
		return map;
	}
	
}