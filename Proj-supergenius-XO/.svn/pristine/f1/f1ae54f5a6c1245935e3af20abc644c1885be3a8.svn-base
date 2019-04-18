package com.supergenius.xo.finance.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * finance评论赞数
 * @author YangGuang
 * @date 2018年1月3日10:16:14
 */
public class PrizeCountCommentsFinanceRule extends PrizeCountRule{
	
	protected static String p = "/finance/comments";

	public PrizeCountCommentsFinanceRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsFinanceRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsFinanceRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
