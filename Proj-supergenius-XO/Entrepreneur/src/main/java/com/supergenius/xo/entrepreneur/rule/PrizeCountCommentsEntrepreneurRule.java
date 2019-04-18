package com.supergenius.xo.entrepreneur.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * finance评论赞数
 * @author YangGuang
 * @date 2018年1月3日10:16:14
 */
public class PrizeCountCommentsEntrepreneurRule extends PrizeCountRule{
	
	protected static String p = "/entrepreneur/comments";

	public PrizeCountCommentsEntrepreneurRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsEntrepreneurRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsEntrepreneurRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
