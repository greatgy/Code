package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才人生一级评论的二级评论数
 * @author ChenQi
 * @date 2018年5月9日16:14:02
 */
public class LifePbmSecondCmtCountRlue extends CountRule{
	protected static String p = "/life/problemscmt";
	
	public LifePbmSecondCmtCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifePbmSecondCmtCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifePbmSecondCmtCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
