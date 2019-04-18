package com.supergenius.core.rule;

/**
 * 推荐话题（72个小时之内赞数最多的3篇话）
 * @author liushaomin
 */
public class TopicRecommendRule extends TopicRule{
	
	protected static String p = "/recommend";

	public TopicRecommendRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public TopicRecommendRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public TopicRecommendRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
