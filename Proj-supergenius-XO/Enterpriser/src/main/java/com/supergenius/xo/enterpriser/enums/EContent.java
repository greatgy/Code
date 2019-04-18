package com.supergenius.xo.enterpriser.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 引资购商项目模块类型枚举
 * @author LoupengYu
 * @date 2018年2月2日10:22:31
 */
public enum EContent {
	
	lecture("0"),//专题讲座
	train("1"),//企业家培训
	cooperation("2"),//互助合作
	photo("3"),//引资购商图片
	advertising("4"),//广告位
	concept("5"),//引资购商概念
	member("6");//会员通道
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
