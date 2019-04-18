package com.genius.core.cache.rule;

/**
 * 通用的一个Rule，path为:/
 * 每点击一次文章，incr缓存，当到达某个值时更新数据库等等
 * @author architect.bian
 * @createtime 2014-8-25 下午3:06:21
 */
public class OneRule extends BaseRule {
	
	protected static String p = "one";
	
	public OneRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public OneRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public OneRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
