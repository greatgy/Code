package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 文章赞数
 * @author liushaomin
 */
	
public class PrizeCountArticleRule extends CountRule{
	protected static String p = "/prize";

	public PrizeCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
