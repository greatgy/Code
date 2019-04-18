package com.genius.core.cache.rule;

import com.genius.core.cache.conf.BaseCacheConf;

/**
 * 在线用户缓存键生成规则测试类
 * 
 * @author architect.bian
 * @createtime 2014-8-27 下午7:39:19
 */
public class UserOnlineRule extends UserRule {
	
	protected static String p = "online";

	public UserOnlineRule() {
		this("", 0);
	}
	
	/**
	 * @param k
	 */
	public UserOnlineRule(String k) {
		this(k, 0);
	}

	/**
	 * 
	 * @param k 键值
	 * @param exp 过期时间
	 */
	public UserOnlineRule(String k, int exp) {
		super(k, exp);
		super.path += BaseCacheConf.SPLITTER_PATH + p;
		refreshConfKey();
	}

}
