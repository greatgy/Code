package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author liushaomin
 *
 */
public class StartupCommentCountArticleRule extends CountRule{

	protected static String p = "/startupcmt";

	public StartupCommentCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public StartupCommentCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public StartupCommentCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
