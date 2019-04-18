package com.supergenius.xo.entrepreneur.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ChenQi
 */
public class EntrepreneurFirstArticleRule extends EntrepreneurArticleRlue{
	
	protected static String p = "/first";

	public EntrepreneurFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
