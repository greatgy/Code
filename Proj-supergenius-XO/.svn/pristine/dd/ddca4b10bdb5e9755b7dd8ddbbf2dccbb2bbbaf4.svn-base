package com.supergenius.xo.managernews.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class ManagernewsFirstArticleRule extends ManagernewsArticleRlue{
	
	protected static String p = "/first";

	public ManagernewsFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
