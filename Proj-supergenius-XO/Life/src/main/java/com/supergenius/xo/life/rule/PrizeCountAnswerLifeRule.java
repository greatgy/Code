package com.supergenius.xo.life.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * 评论赞数
 * @author 杨光
 */
public class PrizeCountAnswerLifeRule extends PrizeCountRule{
	
	protected static String p = "/life/answer";

	public PrizeCountAnswerLifeRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountAnswerLifeRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountAnswerLifeRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
