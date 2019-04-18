package com.supergenius.core.rule;

import com.genius.core.cache.conf.BaseCacheConf;
import com.genius.core.cache.rule.CountRule;

/**
 * @author GreatHost
 * 
 */
public class CountSessRule extends CountRule {

	protected static String p = "sess";

	public CountSessRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CountSessRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp
	 *            seconds
	 */
	public CountSessRule(String k, int exp) {
		super(k, exp);
		super.path += BaseCacheConf.SPLITTER_PATH + p;
		refreshConfKey();
	}
}

