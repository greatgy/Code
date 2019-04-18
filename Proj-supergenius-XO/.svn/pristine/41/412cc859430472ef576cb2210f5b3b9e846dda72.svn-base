package com.supergenius.core.rule;

/**
 * 最新参与用户
 * @author liushaomin
 */
public class TopicJoinUserRule extends TopicRule{

	protected static String p = "/joinuser";

	public TopicJoinUserRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicJoinUserRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicJoinUserRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
