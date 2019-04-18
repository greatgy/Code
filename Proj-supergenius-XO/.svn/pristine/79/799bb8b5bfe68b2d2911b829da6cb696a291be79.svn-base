package com.supergenius.core.rule;

/**
 * 评论赞数
 * @author 杨光
 */
public class PrizeCountTeaseCareerRule extends PrizeCountRule{
	
	protected static String p = "/career/tease";

	public PrizeCountTeaseCareerRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountTeaseCareerRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountTeaseCareerRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
