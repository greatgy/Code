package com.supergenius.xo.entrepreneur.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author xuzhixiang
 * @date 2017年9月19日15:24:41
 *
 */
public class EntrepreneurCommentCountArticleRule extends CountRule{

	protected static String p = "/entrepreneurcmt";

	public EntrepreneurCommentCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurCommentCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurCommentCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
