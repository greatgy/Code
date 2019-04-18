package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 当前期论战的Rule规则基类
 * @author ShangJianguo
 */
public class DebateCurRlue extends BaseRule{
	protected static String p = "/cur";
	
	public DebateCurRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public DebateCurRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public DebateCurRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
