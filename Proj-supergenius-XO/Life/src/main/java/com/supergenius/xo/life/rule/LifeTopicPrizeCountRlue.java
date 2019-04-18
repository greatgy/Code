package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才人生问题评论数
 * @author JiaShitao
 * @date 2018年5月9日15:43:06
 */
public class LifeTopicPrizeCountRlue extends CountRule{
	
	protected static String p = "/life/topic/prize";
	
	public LifeTopicPrizeCountRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifeTopicPrizeCountRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifeTopicPrizeCountRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}