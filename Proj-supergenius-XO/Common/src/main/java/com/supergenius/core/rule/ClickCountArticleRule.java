package com.supergenius.core.rule;

/**
 * 文章阅读数
 * @author liushaomin
 */
public class ClickCountArticleRule extends ClickCountRule{

	protected static String p = "/article";

	public ClickCountArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public ClickCountArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public ClickCountArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
