package com.supergenius.core.rule;

/**
 * 推荐文章缓存Rule规则
 *
 * @author ShangJianguo
 */
public class FinanceRecommendRule extends FinanceRlue{
	
	protected static String p = "/recommend";

	public FinanceRecommendRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public FinanceRecommendRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public FinanceRecommendRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
