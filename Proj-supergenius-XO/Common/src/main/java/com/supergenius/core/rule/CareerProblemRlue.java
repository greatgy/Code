package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才职场问题Rule规则类
 * @author ChenQi
 * @date 2017年12月6日15:43:06
 */
public class CareerProblemRlue extends BaseRule{
	protected static String p = "career/problem";
	
	public CareerProblemRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public CareerProblemRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CareerProblemRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
