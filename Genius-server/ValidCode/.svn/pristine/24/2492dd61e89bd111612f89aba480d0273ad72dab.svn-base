package com.genius.server.validcode.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 *
 * @author Architect.bian
 */
public class AppUidRule extends BaseRule{
	
	protected static String p = "appuid";

	public AppUidRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public AppUidRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public AppUidRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
