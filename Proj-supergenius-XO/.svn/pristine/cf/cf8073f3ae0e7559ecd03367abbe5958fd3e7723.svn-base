package com.supergenius.core.rule;

/**
 * 标签今日点击数
 * 
 * @author ChenQi
 */
public class ClickCountAiLabelRule extends ClickCountRule {

	protected static String p = "/ai/label";

	public ClickCountAiLabelRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountAiLabelRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountAiLabelRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
