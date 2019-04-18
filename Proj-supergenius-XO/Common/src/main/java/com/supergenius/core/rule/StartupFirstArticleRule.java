package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class StartupFirstArticleRule extends BaseRule{
	
	protected static String p = "startup/article/first";

	public StartupFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public StartupFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public StartupFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}