package com.supergenius.xo.gupage.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * 顾雏军专栏评论赞数
 * @author ChenQi
 * @date 2018年1月12日16:42:00
 */
public class PrizeCountCommentsGupageRule extends PrizeCountRule{
	
	protected static String p = "/gupage/comments";

	public PrizeCountCommentsGupageRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsGupageRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsGupageRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
