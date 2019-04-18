package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ShangJianguo
 */
public class StartupRecoomendListRule extends BaseRule{
	
	protected static String p = "startup/recommendlist";

	public StartupRecoomendListRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public StartupRecoomendListRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public StartupRecoomendListRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
