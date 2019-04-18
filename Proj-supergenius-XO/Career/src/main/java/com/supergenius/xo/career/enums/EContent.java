package com.supergenius.xo.career.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 内容枚举类
 * 
 * @author ChenQi
 * @date 2017年8月23日11:29:26
 */
public enum EContent {
	
	experience("0"),//职场心得
	gossip("1"),//职场八卦
	guide("2"),//应聘指南
	detail("3"),//文章详情页
	banner("4"),//首页banner
	index("5"),//首页广告位
	tease("10");//职场吐槽
	
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
