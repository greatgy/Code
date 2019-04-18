package com.supergenius.core.rule;

/**
 * 挑战详情评论的赞
 * @author XieMing
 * @date 2016-8-28 下午4:52:55
 */
public class PrizeCountPkRule extends PrizeCountRule{
	protected static String p = "/pkschedule";

	public PrizeCountPkRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountPkRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountPkRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
