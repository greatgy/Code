package com.supergenius.core.rule;

/**
 * 天才AI左侧缓存Rule规则
 *
 * @author Yangguang
 */
public class StartupLatestArticleRule extends ArticleRlue{
	
	protected static String p = "/latest";

	public StartupLatestArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public StartupLatestArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public StartupLatestArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
