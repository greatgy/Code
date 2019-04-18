package com.supergenius.xo.enterpriser.rule;

import com.supergenius.core.rule.ClickCountRule;

/**
 * 视频浏览数
 * 
 * @author YangGuang
 */
public class EnterpriserVideoClickCountRule extends ClickCountRule {

	protected static String p = "/enterpriser/video";

	public EnterpriserVideoClickCountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserVideoClickCountRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public EnterpriserVideoClickCountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
