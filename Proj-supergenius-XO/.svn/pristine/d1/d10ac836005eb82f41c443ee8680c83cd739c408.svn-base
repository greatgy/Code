package com.supergenius.xo.entrepreneur.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * finance文章一级评论的二级评论数
 * 
 * @author YangGuang
 * @date 2018年1月3日10:19:12
 *
 */
public class EntrepreneurSecondCommentCountRule extends CountRule{

	protected static String p = "/entrepreneur/scmt";

	public EntrepreneurSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EntrepreneurSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
