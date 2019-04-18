package com.supergenius.xo.enterpriser.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class EnterpriserTopArticleRule extends EnterpriserArticleRlue{
	
	protected static String p = "/top";

	public EnterpriserTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
