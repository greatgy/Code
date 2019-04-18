package com.supergenius.core.rule;

/**
 * 评论赞数
 * @author 杨光
 */
public class PrizeCountAnswerCareerRule extends PrizeCountRule{
	
	protected static String p = "/career/answer";

	public PrizeCountAnswerCareerRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountAnswerCareerRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountAnswerCareerRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
