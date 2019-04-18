package com.supergenius.core.rule;

/**
 * 引资购商评论赞数
 * @author LouPengYu
 * @date 2018年2月1日15:13:52
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
