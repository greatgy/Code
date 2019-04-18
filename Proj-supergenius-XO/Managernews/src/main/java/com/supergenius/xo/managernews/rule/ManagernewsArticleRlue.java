package com.supergenius.xo.managernews.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * managernews文章的Rule规则基类
 * @author xuzhixiang
 * @date 2017年9月20日10:46:36
 */
public class ManagernewsArticleRlue extends BaseRule{
	protected static String p = "managernews/article";
	
	public ManagernewsArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public ManagernewsArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public ManagernewsArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
