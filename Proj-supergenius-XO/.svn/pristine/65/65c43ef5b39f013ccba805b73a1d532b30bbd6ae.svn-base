package com.supergenius.core.rule;

/**
 * 话题赞的统计
 * @author liushaomin
 */
public class PrizeCountTopicRule extends PrizeCountRule{

	protected static String p = "/topic";

	public PrizeCountTopicRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountTopicRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountTopicRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
