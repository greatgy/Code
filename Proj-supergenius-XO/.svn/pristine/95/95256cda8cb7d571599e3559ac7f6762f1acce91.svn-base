package com.supergenius.core.rule;

/**
 * 评论赞数
 * @author 杨光
 */
public class PrizeCountCmtStartupRule extends PrizeCountRule{
	
	protected static String p = "/startup/comment";

	public PrizeCountCmtStartupRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCmtStartupRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCmtStartupRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
