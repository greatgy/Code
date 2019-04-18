package com.supergenius.core.rule;

import com.genius.core.cache.rule.UserRule;

/**
 * 用户的基本信息Rule
 *
 * @author ShangJianguo
 */
public class UserInfoRule extends UserRule{

	protected static String p = "/info";

	public UserInfoRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserInfoRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserInfoRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
