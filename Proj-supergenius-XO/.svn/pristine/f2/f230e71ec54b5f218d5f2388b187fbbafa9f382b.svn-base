package com.supergenius.core.rule;

/**
 * 天才职场评论赞数
 * @author ChenQi
 * @date 2017年12月12日17:37:51
 */
public class PrizeCountCommentsCareerRule extends PrizeCountRule{
	
	protected static String p = "/career/comments";

	public PrizeCountCommentsCareerRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsCareerRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsCareerRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
