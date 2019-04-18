package com.supergenius.core.rule;

/**
 * 天才AI左侧缓存Rule规则
 *
 * @author Yangguang
 */
public class CareerExperienceRule extends CareerArticleRlue{
	
	protected static String p = "/experience";

	public CareerExperienceRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CareerExperienceRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public CareerExperienceRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
