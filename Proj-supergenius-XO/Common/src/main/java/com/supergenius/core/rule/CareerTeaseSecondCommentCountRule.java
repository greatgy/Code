package com.supergenius.core.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * Career文章一级评论的二级评论数
 * 
 * @author ChenQi
 * @date 2017年9月19日15:24:41
 *
 */
public class CareerTeaseSecondCommentCountRule extends CountRule{

	protected static String p = "/career/teasescmt";

	public CareerTeaseSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CareerTeaseSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CareerTeaseSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
