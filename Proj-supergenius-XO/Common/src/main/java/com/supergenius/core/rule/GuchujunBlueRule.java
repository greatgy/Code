package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 相信再次拖延的计数
 * @author liushaomin
 */
public class GuchujunBlueRule extends BaseRule{

	protected static String p = "guchujun/blue";

	public GuchujunBlueRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public GuchujunBlueRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public GuchujunBlueRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}


}
