package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * @author liushaomin
 *
 */
public class CommentCountRule extends CountRule{
	
	protected static String p = "/comment";

	public CommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
