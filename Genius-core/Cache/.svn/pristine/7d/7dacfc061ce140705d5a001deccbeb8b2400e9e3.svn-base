package com.genius.core.cache.rule;

/**
 * 点击数key的生成规则
 * 每点击一次文章，incr缓存，当到达某个值时更新数据库等等
 * 
 * @author architect.bian
 * @createtime 2014-8-27 下午7:25:29
 */
public class CountRule extends BaseRule {
	
	protected static String p = "count";
	
	public CountRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public CountRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public CountRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
