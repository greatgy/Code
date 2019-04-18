package com.supergenius.xo.enterpriser.rule;

/**
 * 文章页面文章缓存Rule规则
 *
 * @author Yangguang
 */
public class EnterpriserFirstArticleRule extends EnterpriserArticleRlue{
	
	protected static String p = "/first";

	public EnterpriserFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
