package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才Ai文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class AiArticleRlue extends BaseRule{
	protected static String p = "ai/article";
	
	public AiArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public AiArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public AiArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
