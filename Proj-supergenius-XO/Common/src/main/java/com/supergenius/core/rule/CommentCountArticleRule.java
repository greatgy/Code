package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author liushaomin
 *
 */
public class CommentCountArticleRule extends CountRule{

	protected static String p = "/cmt";

	public CommentCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CommentCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CommentCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
