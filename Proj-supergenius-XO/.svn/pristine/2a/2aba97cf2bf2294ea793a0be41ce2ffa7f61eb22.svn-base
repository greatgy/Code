package com.supergenius.xo.user.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论配置枚举类
 *
 * @author ShangJianguo
 */
public enum ECommentConfig {

	debatered(Integer.valueOf("1000000000000000000000000000000", 2)), // 支持红方1073741824
	debateblue(Integer.valueOf("0100000000000000000000000000000", 2)); // 支持蓝方536870912

	private final int value;

	private ECommentConfig(int v) {
		this.value = v;
	}

	public String toString() {
		return String.valueOf(value);
	}

	public static ECommentConfig get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECommentConfig get(String str) {
		for (ECommentConfig e : values()) {
			int var = Integer.valueOf(e.toString());
			if (var == (var & Integer.valueOf(str))) {
				return e;
			}
		}
		return null;
	}

	public static ECommentConfig getByName(String name) {
		for (ECommentConfig e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param int type
	 * @return
	 */
	public boolean ismatch(int type) {
		return value == (value & type);
	}

	/**
	 * 判断此类型是否与值匹配
	 * 
	 * @param EFinance
	 *            type
	 * @return
	 */
	public static boolean ismatch(int v, ECommentConfig type) {
		return v == (v | Integer.valueOf(type.toString()));
	}

	/**
	 * @param education2
	 * @return
	 */
	public Integer plus(ECommentConfig e) {
		return value | Integer.valueOf(e.toString());
	}

	/**
	 * @param education2
	 * @return
	 */
	public int minus(ECommentConfig e) {
		return value ^ Integer.valueOf(e.toString());
	}
}
