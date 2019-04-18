package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 天才人生文章的Rule规则基类
 * @author CHenQi
 * @date 2018年5月9日16:33:19
 */
public class LifeTopicRlue extends BaseRule{
	protected static String p = "life/topic";
	
	public LifeTopicRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public LifeTopicRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public LifeTopicRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}