package com.supergenius.xo.finance.rule;

import com.supergenius.core.rule.ClickCountRule;

/**
 * 标签今日点击数
 * 
 * @author ChenQi
 */
public class ClickCountFinanceLabelRule extends ClickCountRule {

	protected static String p = "/finance/label";

	public ClickCountFinanceLabelRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountFinanceLabelRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountFinanceLabelRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
