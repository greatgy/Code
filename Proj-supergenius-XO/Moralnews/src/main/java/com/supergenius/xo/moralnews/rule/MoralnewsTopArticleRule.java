package com.supergenius.xo.moralnews.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class MoralnewsTopArticleRule extends MoralnewsArticleRlue {
	
	protected static String p = "/top";

	public MoralnewsTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
