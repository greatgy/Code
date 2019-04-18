package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author xuzhixiang
 * @date 2017年9月19日15:24:41
 *
 */
public class AiCommentCountArticleRule extends CountRule{

	protected static String p = "/aicmt";

	public AiCommentCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public AiCommentCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public AiCommentCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
