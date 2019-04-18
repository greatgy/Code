package com.supergenius.core.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author ShangJianguo
 */
public class FinanceLatestRule extends FinanceRlue{
	
	protected static String p = "/latest";

	public FinanceLatestRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public FinanceLatestRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public FinanceLatestRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
