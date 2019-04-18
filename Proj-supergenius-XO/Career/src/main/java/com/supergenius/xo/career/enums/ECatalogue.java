package com.supergenius.xo.career.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 内容枚举类
 * 
 * @author yangguang
 * @date 2017年11月14日12:15:59
 */
public enum ECatalogue {
	
	tease("11"),//职场吐槽
	guide("16"),//应聘指南
	experience("15"),//职场心得
	gossip("13"),//职场八卦
	test("1"),//职场测试
	member("17"),//会员通道
	nonsense("14"),//职场鬼话
	puzzled("12");//职场困惑
	
	private final String value;

	private ECatalogue(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECatalogue get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECatalogue get(String str) {
		for (ECatalogue e : values()) {
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
