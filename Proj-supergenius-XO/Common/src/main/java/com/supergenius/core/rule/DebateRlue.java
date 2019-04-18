package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 论战的Rule规则基类
 * @author ShangJianguo
 */
public class DebateRlue extends BaseRule{
	protected static String p = "debate";
	
	public DebateRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public DebateRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public DebateRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
