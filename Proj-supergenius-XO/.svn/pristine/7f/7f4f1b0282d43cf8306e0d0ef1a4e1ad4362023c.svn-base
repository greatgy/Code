package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author liushaomin
 *
 */
public class PrizeCountRule extends CountRule{

	protected static String p = "/prize";

	public PrizeCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
