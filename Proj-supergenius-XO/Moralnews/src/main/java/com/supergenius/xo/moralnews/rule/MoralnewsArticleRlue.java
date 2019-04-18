




package com.supergenius.xo.moralnews.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * managernews文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class MoralnewsArticleRlue extends BaseRule{
	protected static String p = "moralnews/article";
	
	public MoralnewsArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public MoralnewsArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public MoralnewsArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}


