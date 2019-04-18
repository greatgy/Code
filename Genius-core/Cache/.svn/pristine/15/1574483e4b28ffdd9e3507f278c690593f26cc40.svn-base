package com.genius.core.cache.rule;

/**
 * user的缓存规则
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午3:05:14
 */
public class UserRule extends BaseRule {

	protected static String p = "users";
	
	public UserRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public UserRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public UserRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}

}
