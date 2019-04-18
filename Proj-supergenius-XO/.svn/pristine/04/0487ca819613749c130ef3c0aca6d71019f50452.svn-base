package com.supergenius.xo.life.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * 天才人生文章一级评论的二级评论数
 * 
 * @author CHenQi
 * @date 2018年5月9日16:33:46
 *
 */
public class LifeSecondCommentCountRule extends CountRule{

	protected static String p = "/life/scmt";

	public LifeSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public LifeSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public LifeSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
