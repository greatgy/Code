package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 数独App规则基类
 * @author ChenQi
 * @date 2017年12月14日14:15:59
 */
public class GamesRule extends BaseRule{
	protected static String p = "games";
	
	public GamesRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public GamesRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public GamesRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
