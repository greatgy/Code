package com.supergenius.core.rule;

import com.genius.core.cache.rule.UserRule;

/**
 * 用户参与的话题
 * @author liushaomin
 */
public class UserTopicCommentRule extends UserRule{

	protected static String p = "/topiccomment";

	public UserTopicCommentRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserTopicCommentRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserTopicCommentRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
