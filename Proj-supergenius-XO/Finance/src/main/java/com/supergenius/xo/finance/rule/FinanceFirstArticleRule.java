package com.supergenius.xo.finance.rule;

/**
 * 最新文章文章缓存Rule规则
 *
 * @author Yangguang
 */
public class FinanceFirstArticleRule extends FinanceArticleRlue{
	
	protected static String p = "/first";

	public FinanceFirstArticleRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public FinanceFirstArticleRule(String k) {
		this(k, 0);
	}

	/**
	 * @param k
	 */
	public FinanceFirstArticleRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
