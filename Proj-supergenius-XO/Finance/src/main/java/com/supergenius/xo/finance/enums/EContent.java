package com.supergenius.xo.finance.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 内容枚举类
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午2:15:34
 */
public enum EContent {

	index("0"),		//首页
	economics("1"), // 财经资讯
	invest("2"), // 投资资讯
	merger("4"), // 并购资讯
	risk("8"), // 风险资讯
	technology("16"), // 科技资讯
	gold("32"), // 醒世金语
	entrepreneur("64"), // 企业家
	profession("128"), // 职业经理人
	nightwords("256"); // 职场夜话

	private final String value;

	private EContent(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EContent get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EContent get(String str) {
		for (EContent e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
