package com.supergenius.core.rule;

/**
 * 评论赞数
 * @author 杨光
 */
public class PrizeCountCmtAiRule extends PrizeCountRule{
	
	protected static String p = "/ai/comment";

	public PrizeCountCmtAiRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCmtAiRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCmtAiRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
