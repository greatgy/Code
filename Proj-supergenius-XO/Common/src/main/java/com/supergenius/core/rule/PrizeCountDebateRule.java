package com.supergenius.core.rule;

/**
 * 文章赞数
 * @author liushaomin
 */
	
public class PrizeCountDebateRule extends PrizeCountRule{
	protected static String p = "/debate";

	public PrizeCountDebateRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountDebateRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountDebateRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
