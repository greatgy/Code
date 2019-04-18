package com.supergenius.xo.enterpriser.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * 引资购商评论赞数
 * @author loupengyu
 * @date 2018年1月12日16:42:00
 */
public class PrizeCountCommentsEnterpriserRule extends PrizeCountRule{
	
	protected static String p = "/enterpriser/comments";

	public PrizeCountCommentsEnterpriserRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsEnterpriserRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsEnterpriserRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
