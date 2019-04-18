package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天财评论文章的Rule规则基类
 * @author ShangJianguo
 */
public class FinanceRlue extends BaseRule{
	protected static String p = "finance";
	
	public FinanceRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public FinanceRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public FinanceRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
