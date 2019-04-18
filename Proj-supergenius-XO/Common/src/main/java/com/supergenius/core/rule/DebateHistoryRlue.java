package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 历史论战的Rule规则基类
 * @author ShangJianguo
 */
public class DebateHistoryRlue extends BaseRule{
	protected static String p = "/history";
	
	public DebateHistoryRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public DebateHistoryRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public DebateHistoryRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
