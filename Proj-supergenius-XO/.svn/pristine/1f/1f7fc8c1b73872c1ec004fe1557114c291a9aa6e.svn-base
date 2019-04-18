package com.supergenius.xo.entrepreneur.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 企业家培训文章的Rule规则基类
 * 
 * @author tf
 * @date 2018年7月5日
 */
public class EntrepreneurArticleRlue extends BaseRule {
	
	protected static String p = "entrepreneur/article";
	
	public EntrepreneurArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public EntrepreneurArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public EntrepreneurArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
