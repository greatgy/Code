package com.genius.core.cache.rule;
/**
 * 自增计数器缓存对象的缓存key的生成规则
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午3:06:53
 */
public class AutoIncrRule extends BaseRule {

	protected static String p = "autoincr";
	
	public AutoIncrRule() {
		this("", 0);
	}

	/**
	 * @param k
	 */
	public AutoIncrRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public AutoIncrRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
