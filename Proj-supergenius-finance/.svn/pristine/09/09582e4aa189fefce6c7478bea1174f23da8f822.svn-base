package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

public class WxTockenRule extends BaseRule{

	protected static String p = "/finance/token";

	public WxTockenRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public WxTockenRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public WxTockenRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
