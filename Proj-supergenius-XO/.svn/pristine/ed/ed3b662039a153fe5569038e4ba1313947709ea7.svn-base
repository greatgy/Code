package com.supergenius.xo.life.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * 动态赞数
 * @author ChenQi
 */
public class PrizeCountEssayLifeRule extends PrizeCountRule{
	
	protected static String p = "/life/essay";

	public PrizeCountEssayLifeRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountEssayLifeRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountEssayLifeRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
