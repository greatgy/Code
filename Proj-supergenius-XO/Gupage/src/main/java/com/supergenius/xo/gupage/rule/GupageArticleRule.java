package com.supergenius.xo.gupage.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 顾雏军专栏文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class GupageArticleRule extends BaseRule{
	protected static String p = "gupage/article";
	
	public GupageArticleRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public GupageArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public GupageArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
