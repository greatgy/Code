package com.supergenius.core.rule;

/**
 * 文章排行榜缓存Rule规则
 *
 * @author ShangJianguo
 */
public class FinanceRankRule extends FinanceRlue{
	
	protected static String p = "/rank";

	public FinanceRankRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public FinanceRankRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public FinanceRankRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
