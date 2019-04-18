package com.supergenius.core.rule;

/**
 * 天才AI左侧缓存Rule规则
 *
 * @author Yangguang
 */
public class CareerLeftArticleRule extends CareerArticleRlue{
	
	protected static String p = "/left";

	public CareerLeftArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CareerLeftArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CareerLeftArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
