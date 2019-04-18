package com.genius.core.cache.engine;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Architect.bian
 *
 */
public interface Engine {

	/**
	 * @return
	 */
	boolean init();

	/**
	 * @param servers
	 * @param weights
	 * @return
	 */
	boolean init(String servers, String weights);

	/**
	 * @return
	 */
	boolean stop();

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(String key, Object value);

	/**
	 * @param key
	 * @param expire
	 * @param value
	 * @return
	 */
	boolean set(String key, int expire, Object value);

	/**
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Object get(String key) throws Exception;

	/**
	 * @param key
	 * @param newExpire
	 * @return
	 * @throws Exception
	 */
	Object get(String key, int newExpire) throws Exception;

	/**
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> get(Collection<String> keys) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	Set<String> getKeys() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	Collection<Object> getValues() throws Exception;

	/**
	 * @param key
	 * @param i
	 * @param initvar
	 * @return
	 */
	long incr(String key, int i, int initvar) throws Exception;

	/**
	 * @param key
	 * @param i
	 * @param initvar
	 * @return
	 */
	long decr(String key, int i, int initvar) throws Exception;
	
	/**
	 * @param key
	 * @return
	 */
	boolean remove(String key);

	/**
	 * @return
	 * @throws Exception
	 */
	int size() throws Exception;

	/**
	 * 使缓存失效，但并不即时清楚
	 * @return
	 */
	boolean flushAll();

	boolean clearAll();

}
