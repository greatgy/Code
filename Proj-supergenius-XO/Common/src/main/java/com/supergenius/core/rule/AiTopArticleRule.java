package com.supergenius.core.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class AiTopArticleRule extends AiArticleRlue{
	
	protected static String p = "/top";

	public AiTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public AiTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public AiTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
