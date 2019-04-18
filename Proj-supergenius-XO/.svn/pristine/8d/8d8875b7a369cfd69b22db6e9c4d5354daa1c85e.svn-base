package com.supergenius.xo.moralnews.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * @author tf
 * @date 2018年9月21日
 */
public class PrizeCountCommentsMoralnewsRule extends PrizeCountRule {
	
	protected static String p = "/moralnews/comments";

	public PrizeCountCommentsMoralnewsRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsMoralnewsRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsMoralnewsRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
	
}
