package com.supergenius.xo.managernews.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class ManagernewsTopArticleRule extends ManagernewsArticleRlue{
	
	protected static String p = "/top";

	public ManagernewsTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
