package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才人生文章的Rule规则基类
 * @author CHenQi
 * @date 2018年5月9日16:33:19
 */
public class LifeArticleRlue extends BaseRule{
	protected static String p = "life/article";
	
	public LifeArticleRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifeArticleRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifeArticleRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
