package com.supergenius.xo.gupage.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * gupage文章一级评论的二级评论数
 * 
 * @author YangGuang
 * @date 2018年1月3日10:19:12
 *
 */
public class GupageSecondCommentCountRule extends CountRule{

	protected static String p = "/gupage/scmt";

	public GupageSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public GupageSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public GupageSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
