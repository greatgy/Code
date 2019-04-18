package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才人生视频浏览量
 * @author ChenQi
 * @date 2018年5月17日16:58:29
 */
public class LifeVideoClickCountRlue extends CountRule{
	
	protected static String p = "/life/click";
	
	public LifeVideoClickCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifeVideoClickCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifeVideoClickCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
