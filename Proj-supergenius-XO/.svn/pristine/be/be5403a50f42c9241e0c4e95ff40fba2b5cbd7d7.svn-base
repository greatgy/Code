package com.supergenius.core.rule;

import com.genius.core.cache.rule.UserRule;

/**
 * 某个用户的文章数量
 *
 * @author ShangJianguo
 */
public class UserArticleCountRule extends UserRule{

	protected static String p = "/articlecount";

	public UserArticleCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserArticleCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserArticleCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
