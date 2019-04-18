package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才职场问题评论数
 * @author ChenQi
 * @date 2017年12月6日15:43:06
 */
public class CareerProblemCommentCountRlue extends CountRule{
	
	protected static String p = "/career/problemcmt";
	
	public CareerProblemCommentCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public CareerProblemCommentCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CareerProblemCommentCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
