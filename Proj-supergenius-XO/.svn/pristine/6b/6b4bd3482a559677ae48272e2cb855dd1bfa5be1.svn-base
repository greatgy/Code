package com.supergenius.xo.entrepreneur.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ChenQi
 */
public class EntrepreneurTopArticleRule extends EntrepreneurArticleRlue{
	
	protected static String p = "/top";

	public EntrepreneurTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
