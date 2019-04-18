package com.supergenius.xo.enterpriser.rule;

/**
 * 首页文章缓存Rule规则
 *
 * @author Yangguang
 */
public class EnterpriserIndexArticleRule extends EnterpriserArticleRlue{
	
	protected static String p = "/index";

	public EnterpriserIndexArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserIndexArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserIndexArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
