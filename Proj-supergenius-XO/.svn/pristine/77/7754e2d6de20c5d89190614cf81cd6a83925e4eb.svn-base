package com.supergenius.core.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * 相信立案再审的计数
 * @author liushaomin
 */
public class GuchujunRedRule extends BaseRule{
	
	protected static String p = "guchujun/red";

	public GuchujunRedRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public GuchujunRedRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public GuchujunRedRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
