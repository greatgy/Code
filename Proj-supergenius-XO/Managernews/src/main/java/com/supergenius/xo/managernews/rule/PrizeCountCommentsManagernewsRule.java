package com.supergenius.xo.managernews.rule;

import com.supergenius.core.rule.PrizeCountRule;

/**
 * managernews评论赞数
 * @author YangGuang
 * @date 2018年1月3日10:16:14
 */
public class PrizeCountCommentsManagernewsRule extends PrizeCountRule{
	
	protected static String p = "/managernews/comments";

	public PrizeCountCommentsManagernewsRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsManagernewsRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public PrizeCountCommentsManagernewsRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}