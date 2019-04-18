package com.supergenius.core.rule;

/**
 * 话题点击（阅读）数
 * @author liushaomin
 */
public class CommentCountTopicRule extends CommentCountRule{

	protected static String p = "/topic";

	public CommentCountTopicRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CommentCountTopicRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CommentCountTopicRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
