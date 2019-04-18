package com.supergenius.xo.enterpriser.rule;

import com.genius.core.cache.rule.CountRule;

/**
 * enterpriser文章一级评论的二级评论数
 * 
 * @author loupengyu
 * @date 2018年1月3日10:19:12
 *
 */
public class EnterpriserSecondCommentCountRule extends CountRule{

	protected static String p = "/enterpriser/articlescmt";

	public EnterpriserSecondCommentCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserSecondCommentCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserSecondCommentCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
