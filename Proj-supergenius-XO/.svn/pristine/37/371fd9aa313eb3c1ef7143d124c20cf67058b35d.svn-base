package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ShangJianguo
 */
public class StartupArticleListRule extends BaseRule{
	
	protected static String p = "startup/articlelist";

	public StartupArticleListRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public StartupArticleListRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public StartupArticleListRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
