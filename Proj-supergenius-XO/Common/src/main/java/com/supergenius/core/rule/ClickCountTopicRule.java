package com.supergenius.core.rule;

/**
 * 话题点击（阅读）数
 * @author liushaomin
 */
public class ClickCountTopicRule extends ClickCountRule{

	protected static String p = "/topic";

	public ClickCountTopicRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountTopicRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountTopicRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
