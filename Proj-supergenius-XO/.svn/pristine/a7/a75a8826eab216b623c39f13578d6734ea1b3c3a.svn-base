package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 创业平台文章的Rule规则基类
 * @author ChenQi
 */
public class ArticleRlue extends BaseRule{
	protected static String p = "startup/article";
	
	public ArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public ArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public ArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
