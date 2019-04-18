package com.supergenius.xo.moralnews.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * finance文章一级评论的二级评论数
 * 
 * @author YangGuang
 * @date 2018年1月3日10:19:12
 *
 */
public class MoralnewsSecondCommentCountRule extends CountRule{

	protected static String p = "/moralnews/scmt";

	public MoralnewsSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public MoralnewsSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
