package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 论战排行的Rule规则基类
 * @author ShangJianguo
 */
public class DebateRankRlue extends BaseRule{
	protected static String p = "/rank";
	
	public DebateRankRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public DebateRankRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public DebateRankRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
