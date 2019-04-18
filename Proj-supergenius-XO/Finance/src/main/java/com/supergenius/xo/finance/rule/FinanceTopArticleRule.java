package com.supergenius.xo.finance.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class FinanceTopArticleRule extends FinanceArticleRlue{
	
	protected static String p = "/top";

	public FinanceTopArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public FinanceTopArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public FinanceTopArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
