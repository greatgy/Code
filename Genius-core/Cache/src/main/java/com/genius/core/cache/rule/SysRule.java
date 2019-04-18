package com.genius.core.cache.rule;

public class SysRule extends BaseRule {
	
	protected static String p = "sys";
	
	public SysRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public SysRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public SysRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
