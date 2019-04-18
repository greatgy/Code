package com.supergenius.xo.sudokuapi.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 用戶登录
 * 
 * @author YangGuang
 * @date 2018年3月14日14:38:17
 *
 */
public class UserLoginRule extends BaseRule{

	protected static String p = "/sudokuapi/useruid";

	public UserLoginRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserLoginRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserLoginRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
