package com.supergenius.core.rule;

/**
 * 最新发布的话题
 * @author liushaomin
 */
public class TopicLatestRule extends TopicRule{

	protected static String p = "/latest";

	public TopicLatestRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicLatestRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicLatestRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
