package com.supergenius.core.rule;

/**
 * 天才Ai文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年10月27日19:20:36
 */
public class StartupHotArticleRlue extends ArticleRlue{
	protected static String p = "/hot";
	
	public StartupHotArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public StartupHotArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public StartupHotArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
