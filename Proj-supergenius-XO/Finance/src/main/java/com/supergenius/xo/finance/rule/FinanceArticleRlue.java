package com.supergenius.xo.finance.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才Ai文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class FinanceArticleRlue extends BaseRule{
	protected static String p = "finance/article";
	
	public FinanceArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public FinanceArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public FinanceArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
