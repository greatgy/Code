package com.genius.core.cache.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.cache.conf.BaseCacheConf;
import com.genius.core.cache.engine.Engine;
import com.genius.core.cache.engine.MemcacheEngine;
import com.genius.core.cache.rule.Rule;

/**
 * @author Architect.bian
 * 
 */
public class MemcacheUtil extends BaseUtil {

	private static Map<String, Engine> pool = new HashMap<String, Engine>();

	private static Logger log = LoggerFactory.getLogger(MemcacheUtil.class);
	
	/**
	 * @param rule
	 * @return
	 */
	protected static Engine getEngine(Rule rule) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("getEngine for Rule:%s, pool keyset:%s", rule, pool.keySet()));
		}
		if (pool.containsKey(rule.getConfKey())) {
			return pool.get(rule.getConfKey());
		} else {
			Engine engine = new MemcacheEngine();// 可重构成工厂模式
			String conf = rule.getConfValue();
			String[] strs = conf.split(",");
			String servers = strs.length > 0 ? strs[0] : BaseCacheConf.DEFAULT_SERVER;
			String weights = strs.length > 1 ? strs[1] : "";
			engine.init(servers, weights);
			pool.put(rule.getConfKey(), engine);
		}
		return pool.get(rule.getConfKey());
	}

	/**
	 * @param key
	 * @param value
	 * @return whether success
	 */
	public static boolean set(Rule rule, Object value) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("set(Rule %s, Object %s), rule.getExpire:%s", rule, value, rule.getExpire()));
		}
		if (rule.getExpire() > 0) {
			return getEngine(rule).set(rule.getKey(), rule.getExpire(), value);
		}
		return getEngine(rule).set(rule.getKey(), value);
	}

	/**
	 * 通过rule获得对应的值
	 * keys大于一个时，说明是多个key，则获取所有的Key对应的值返回：Map<String, Object>
	 * 若key只有一个，则返回单个值：Object
	 * 若Expire大于0，则重新设置过期时间
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	public static Object get(Rule rule) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(String.format("get(Rule %s)", rule));
		}
		if (rule.getKeys().size() == 1) {
			if (rule.getExpire() > 0) {
				return getEngine(rule).get(rule.getKey(), rule.getExpire());
			}
		} else {
			return getEngine(rule).get(rule.getKeys());
		}
		return getEngine(rule).get(rule.getKey());
	}
	
	/**
	 * 通过对应服务器的任一rule获取该服务器上所有的key
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	public static Set<String> getKeys(Rule rule) throws Exception {
		return getEngine(rule).getKeys();
	}
	
	/**
	 * 通过对应服务器的任一rule获取该服务器上所有的value
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	public static Collection<Object> getValues(Rule rule) throws Exception {
		return getEngine(rule).getValues();
	}
	
	/**
	 * 增1计数
	 * @param rule
	 * @return 增加后的计数
	 * @throws Exception
	 */
	public static long incr(Rule rule) throws Exception {
		return incr(rule, 1, 1);
	}
	
	/**
	 * 增i计数,若不存在则初始为1
	 * @param rule
	 * @param i
	 * @return 增加后的计数
	 * @throws Exception
	 */
	public static long incr(Rule rule, int i) throws Exception {
		return incr(rule, i, 1);
	}

	/**
	 * 增i计数，若不存在设定初始值
	 * @param rule
	 * @param i
	 * @param initvar
	 * @return增加后的计数
	 * @throws Exception
	 */
	public static long incr(Rule rule, int i, int initvar) throws Exception {
		return getEngine(rule).incr(rule.getKey(), i, initvar);
	}
	
	public static long decr(Rule rule) throws Exception {
		return decr(rule, 1, 0);
	}

	public static long decr(Rule rule, int i) throws Exception {
		return decr(rule, i, 0);
	}
	
	public static long decr(Rule rule, int i, int initvar) throws Exception {
		return getEngine(rule).decr(rule.getKey(), i, initvar);
	}
	
	public static boolean remove(Rule rule) {
		if (rule.getKeys().size() == 1) {
			return getEngine(rule).remove(rule.getKey());
		} else {
			Set<String> ks = rule.getKeys();
			Engine engine = getEngine(rule);
			for (String k : ks) {
				engine.remove(k);
			}
			return true;
		}
	}
	
	public static int size(Rule rule) throws Exception {
		return getEngine(rule).size();
	}
	
	public static boolean flushAll(Rule rule) {
		return getEngine(rule).flushAll();
	}
}
