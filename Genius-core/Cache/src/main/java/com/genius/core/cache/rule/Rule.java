package com.genius.core.cache.rule;

import java.util.Set;

/**
 * Rule的接口
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午3:05:58
 */
public interface Rule {
	
	public enum Type {
		memcached, redis
	}
	/**
	 * 默认取memcached的ConfKey
	 * @return
	 */
	String getConfKey();
	
	/**
	 * 缓存配置文件中，如果启用了针对path的配置，则返回的是此path，否则默认是根path
	 * @return
	 */
	String getConfKey(Type type);

	/**
	 * 默认取memcached的ConfValue
	 * @param type
	 * @return
	 */
	String getConfValue();
	/**
	 * 获得配置文件中针对此Rule的值
	 * @return
	 */
	String getConfValue(Type type);
	
	/**
	 * 获得完整Key,eg: /user/online:uid
	 * @return
	 */
	String getKey();
	
	/**
	 * @return
	 */
	void setKey(String k);

	/**
	 * @return
	 */
	void addKey(String k);

	/**
	 * 可用一个rule获取多个值
	 * @return
	 */
	Set<String> getKeys();
	
	/**
	 * 所有Rule的基路径,eg:/user/online
	 * @return
	 */
	String getPath();

	/**
	 * 
	 * @return
	 */
	int getExpire();
	
	/**
	 * 
	 * @param exp
	 * @return
	 */
	void setExpire(int exp);

}