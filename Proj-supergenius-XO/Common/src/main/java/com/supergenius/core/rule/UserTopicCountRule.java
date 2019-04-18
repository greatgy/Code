package com.supergenius.core.rule;

import com.genius.core.cache.rule.UserRule;

/**
 * 用户发起话题
 * @author liushaomin
 */
public class UserTopicCountRule extends UserRule{

	protected static String p = "/topiccount";

	public UserTopicCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserTopicCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserTopicCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
