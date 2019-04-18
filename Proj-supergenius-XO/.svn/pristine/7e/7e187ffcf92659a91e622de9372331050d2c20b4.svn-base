package com.supergenius.xo.manager.enums;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/**
 * 学员级别枚举
 * @author guanshiqian
 */
public enum EStudentLevel {

	basic("0"),//基础学员
	manager("1"),//经理级学员
	majordomo("2"),//总监级学员
	vicepresident("3"),//副总裁级学员
	president("4");//总裁级学员
	
	private final String value;

	private EStudentLevel(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.parseInt(this.value);
	}

	public static EStudentLevel get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EStudentLevel get(String str) {
		for (EStudentLevel e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 得到所有学员级别的值和国际化名字
	 * @return
	 * @author liubin
	 * @createtime 2016-9-1下午2:50:42
	 * @return Map<String,String>
	 */
	public static Map<String, String> getEsudentsLevelMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (EStudentLevel e : values()) {
			map.put(e.toString(), e.getName());
		}
		return map;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
