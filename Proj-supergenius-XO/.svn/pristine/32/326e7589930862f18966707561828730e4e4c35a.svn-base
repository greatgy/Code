package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 论战投票数缓存Rule
 *
 * @author ShangJianguo
 */
public class CountDebateVoteRule extends CountRule{

	protected static String p = "/debatevote";

	public CountDebateVoteRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CountDebateVoteRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CountDebateVoteRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
