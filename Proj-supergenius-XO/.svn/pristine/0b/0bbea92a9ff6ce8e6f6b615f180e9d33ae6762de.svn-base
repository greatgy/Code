package com.supergenius.xo.moralnews.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class MoralnewsFirstArticleRule extends MoralnewsArticleRlue {
	
	protected static String p = "/first";

	public MoralnewsFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
