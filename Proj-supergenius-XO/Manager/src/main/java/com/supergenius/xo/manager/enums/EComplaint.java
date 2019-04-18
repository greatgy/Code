package com.supergenius.xo.manager.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 投诉举报类型枚举
 * @author XieMing
 * @date 2016-4-29 下午7:34:28
 */
public enum EComplaint {

	ad("0"),//广告
	falsehood("1"),//作假
	sexy("2"),//色情
	abuse("3"),//滥用职权
	other("4");//其他

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
