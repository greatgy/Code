package com.supergenius.core.rule;


/**
 * 挑战评论的计数
 * 
 * @author XieMing
 * @date 2016-8-28 下午4:56:46
 */
public class CommentCountPkRule extends CommentCountRule{

	protected static String p = "/pkschedule";

	public CommentCountPkRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CommentCountPkRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CommentCountPkRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
