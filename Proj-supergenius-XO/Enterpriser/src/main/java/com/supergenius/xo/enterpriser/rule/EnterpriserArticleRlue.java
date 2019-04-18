package com.supergenius.xo.enterpriser.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 引资购商文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class EnterpriserArticleRlue extends BaseRule{
	protected static String p = "enterpriser/article";
	
	public EnterpriserArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public EnterpriserArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public EnterpriserArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
