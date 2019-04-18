package com.supergenius.xo.user.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 短信验证码
 * 
 * @author YangGuang
 * @date 2018年3月14日14:38:17
 *
 */
public class SmsCodeRule extends BaseRule{

	protected static String p = "/user/smscode";

	public SmsCodeRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public SmsCodeRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public SmsCodeRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
