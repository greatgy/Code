package com.supergenius.core.rule;

import com.genius.core.cache.rule.UserRule;

/**
 * 某个用户的文章阅读数
 *
 * @author ShangJianguo
 */
public class UserArticleClickCountRule extends UserRule{

	protected static String p = "/articleclickcount";

	public UserArticleClickCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public UserArticleClickCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public UserArticleClickCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
