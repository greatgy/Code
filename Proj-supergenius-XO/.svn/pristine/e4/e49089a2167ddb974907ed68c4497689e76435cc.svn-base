package com.supergenius.xo.managernews.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * finance文章一级评论的二级评论数
 * 
 * @author YangGuang
 * @date 2018年1月3日10:19:12
 *
 */
public class ManagernewsSecondCommentCountRule extends CountRule{

	protected static String p = "/managernews/scmt";

	public ManagernewsSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ManagernewsSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
