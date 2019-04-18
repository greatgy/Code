package com.supergenius.core.rule;

/**
 * 标签今日点击数
 * 
 * @author yangguang
 */
public class ClickCountStartupLabelRule extends ClickCountRule {

	protected static String p = "/startup/label";

	public ClickCountStartupLabelRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountStartupLabelRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountStartupLabelRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
