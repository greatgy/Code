package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author liushaomin
 *
 */
public class ClickCountRule extends CountRule{
	
	protected static String p = "/click";

	public ClickCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
