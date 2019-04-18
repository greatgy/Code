package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才职场一级评论的二级评论数
 * @author ChenQi
 * @date 2017年12月6日15:43:06
 */
public class CareerProblemSecondCommentCountRlue extends CountRule{
	protected static String p = "/career/problemscmt";
	
	public CareerProblemSecondCommentCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public CareerProblemSecondCommentCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CareerProblemSecondCommentCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
