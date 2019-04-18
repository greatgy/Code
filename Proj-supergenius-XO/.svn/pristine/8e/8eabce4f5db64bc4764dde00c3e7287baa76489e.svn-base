package com.supergenius.core.rule;

/**
 * 最新发布的话题
 * @author liushaomin
 */
public class TopicMaxCommentRule extends TopicRule{

	protected static String p = "/maxcomment";

	public TopicMaxCommentRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicMaxCommentRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicMaxCommentRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
