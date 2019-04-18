package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * Life动态一级评论的二级评论数
 * 
 * @author ChenQi
 * @date 2018年5月14日17:45:28
 *
 */
public class LifeEssaySecondCommentCountRule extends CountRule{

	protected static String p = "/life/essayscmt";

	public LifeEssaySecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public LifeEssaySecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public LifeEssaySecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
