package com.supergenius.core.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class CareerTopArticleRule extends CareerArticleRlue{
	
	protected static String p = "/top";

	public CareerTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CareerTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CareerTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
