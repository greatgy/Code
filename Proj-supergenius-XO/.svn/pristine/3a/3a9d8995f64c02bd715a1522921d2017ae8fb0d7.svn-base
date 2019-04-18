package com.supergenius.xo.life.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * 天才人生评论赞数
 * @author ChenQi
 * @date 2018年5月9日16:34:19
 */
public class PrizeCountCommentsLifeRule extends PrizeCountRule{
	
	protected static String p = "/life/comments";

	public PrizeCountCommentsLifeRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsLifeRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsLifeRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
