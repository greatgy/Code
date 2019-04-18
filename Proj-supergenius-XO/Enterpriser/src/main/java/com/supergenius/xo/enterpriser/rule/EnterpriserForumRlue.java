package com.supergenius.xo.enterpriser.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 引资购商的评论文章的Rule规则基类
 * @author loupengyu
 * @date 2017年9月20日10:46:36
 */
public class EnterpriserForumRlue extends BaseRule{
	protected static String p = "enterpriser/forum";
	
	public EnterpriserForumRlue() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public EnterpriserForumRlue(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public EnterpriserForumRlue(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}