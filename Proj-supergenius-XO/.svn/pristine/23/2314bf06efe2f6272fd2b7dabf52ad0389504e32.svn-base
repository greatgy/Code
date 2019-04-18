package com.supergenius.core.rule;

/**
 * @author liushaomin
 *
 */
public class CommentCountDebateRule extends CommentCountRule{

	protected static String p = "/debate";

	public CommentCountDebateRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CommentCountDebateRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CommentCountDebateRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
