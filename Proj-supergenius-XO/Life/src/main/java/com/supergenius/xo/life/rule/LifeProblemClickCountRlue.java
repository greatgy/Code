package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才人生问题浏览量
 * @author JiaShitao
 * @date 2018年5月9日15:43:06
 */
public class LifeProblemClickCountRlue extends CountRule{
	
	protected static String p = "/life/click";
	
	public LifeProblemClickCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifeProblemClickCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifeProblemClickCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
