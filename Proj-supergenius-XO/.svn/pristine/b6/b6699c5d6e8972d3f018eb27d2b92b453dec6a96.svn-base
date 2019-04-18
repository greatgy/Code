package com.supergenius.xo.life.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ChenQi
 */
public class LifeFirstArticleRule extends LifeArticleRlue{
	
	protected static String p = "/first";

	public LifeFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public LifeFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public LifeFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
