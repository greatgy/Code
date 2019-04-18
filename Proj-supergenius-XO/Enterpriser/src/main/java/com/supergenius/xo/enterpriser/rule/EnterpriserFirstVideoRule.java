package com.supergenius.xo.enterpriser.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 顾雏军专栏图片的Rule规则基类
 * @author YangGuang
 * @date 2018年1月12日11:11:03
 */
public class EnterpriserFirstVideoRule extends BaseRule{
	protected static String p = "enterpriser/video";
	
	public EnterpriserFirstVideoRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public EnterpriserFirstVideoRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public EnterpriserFirstVideoRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
