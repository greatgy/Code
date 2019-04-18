package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才职场文章的Rule规则基类
 * @author yangguang
 * @date 2017年11月13日18:17:59
 */
public class CareerArticleRlue extends BaseRule{
	protected static String p = "career/article";
	
	public CareerArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public CareerArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CareerArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
