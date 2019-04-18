package com.supergenius.core.rule;

/**
 * 评论赞数
 * @author liushaomin
 */
public class PrizeCountCommentRule extends PrizeCountRule{
	
	protected static String p = "/comment";

	public PrizeCountCommentRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
