package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 话题相关
 * @author liushaomin
 */
public class TopicRule extends BaseRule{

	protected static String p = "/topic";

	public TopicRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
