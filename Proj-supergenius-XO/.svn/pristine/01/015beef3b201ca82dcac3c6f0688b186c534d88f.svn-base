package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才职场问题评论数
 * @author ChenQi
 * @date 2017年12月6日15:43:06
 */
public class CareerProblemPrizeCountRlue extends CountRule{
	
	protected static String p = "/career/problemprize";
	
	public CareerProblemPrizeCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public CareerProblemPrizeCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CareerProblemPrizeCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
