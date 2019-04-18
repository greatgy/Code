package com.supergenius.xo.life.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ChenQi
 */
public class LifeTopArticleRule extends LifeArticleRlue{
	
	protected static String p = "/top";

	public LifeTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public LifeTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public LifeTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
