package com.genius.core.session.rule;

import com.genius.core.cache.rule.BaseRule;

/**
 * Session key的规则
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午3:05:48
 */
public class SessionBaseRule extends BaseRule {
	
	protected static String p = "s";
	
	public SessionBaseRule() {
		this("", 0);
	}

	public SessionBaseRule(String k) {
		this("", 0);
	}
	
	/**
	 * @param k
	 * @param exp 单位为妙 s
	 */
	public SessionBaseRule(String k, int exp) {
		super(k, exp);
		super.path += p;
		refreshConfKey();
	}
}
