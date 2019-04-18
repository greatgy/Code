package com.supergenius.core.rule;

/**
 * 话题排行
 * @author liushaomin
 */
public class TopicRankRule extends TopicRule{

	protected static String p = "/rank";

	public TopicRankRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicRankRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicRankRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
