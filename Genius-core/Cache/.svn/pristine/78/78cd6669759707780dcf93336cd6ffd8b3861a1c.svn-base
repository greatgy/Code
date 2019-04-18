/**
 * Java虚拟机内置的缓存，使用static field实现
 * ============================================================================
 * 声明：© 2014 genius.com, All Rights Reserved
 * ----------------------------------------------------------------------------
 * Official Website: http://www.genius.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2014 genius All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2014-1-11 下午12:10:26
 * ============================================================================
 */
package com.genius.core.cache.engine;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Architect.bian
 *
 */
@Component
public class JvmCacheEngine implements Engine {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static Map<String, Object> cache = new HashMap<String, Object>();
	
	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#init()
	 */
	@Override
	public boolean init() {
		return this.init(null, null);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#init(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean init(String servers, String weights) {
		cache = new HashMap<String, Object>();
		log.debug("Jvm Cache Engine started   Servers:" + servers + " Weights:" + weights);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#stop()
	 */
	@Override
	public boolean stop() {
		cache = null;
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean set(String key, Object value) {
		cache.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#set(java.lang.String, int, java.lang.Object)
	 * 后期可实现超时清楚处理，hashmap中新加一个key，
	 * 如20120909105730记录应该清除的时间,
	 * 如果存在此key则清除对应value（另外一个key）的对应的值
	 */
	@Override
	public boolean set(String key, int expire, Object value) {
		cache.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#get(java.lang.String)
	 */
	@Override
	public Object get(String key) throws Exception {
		return cache.get(key);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#get(java.lang.String, int)
	 */
	@Override
	public Object get(String key, int newExpire) throws Exception {
		return cache.get(key);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#get(java.util.Collection)
	 */
	@Override
	public Map<String, Object> get(Collection<String> keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#getKeys()
	 */
	@Override
	public Set<String> getKeys() throws Exception {
		return cache.keySet();
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#getValues()
	 */
	@Override
	public Collection<Object> getValues() throws Exception {
		Collection<Object> set = new HashSet<Object>();
		Set<String> keys = cache.keySet();
		for (String key : keys) {
			set.add(this.get(key));
		}
		return set;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#remove(java.lang.String)
	 */
	@Override
	public boolean remove(String key) {
		cache.remove(key);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#size()
	 */
	@Override
	public int size() throws Exception {
		return cache.size();
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Engine#flushAll()
	 */
	@Override
	public boolean flushAll() {
		this.clearAll();
		return true;
	}

	@Override
	public boolean clearAll() {
		cache.clear();
		return true;
	}

	@Override
	public long incr(String key, int i, int initvar) throws Exception {
		if (cache.get(key) == null) {
			cache.put(key, Long.valueOf(initvar));
			return initvar;
		} else {
			long var = Long.valueOf((String) cache.get(key));
			cache.put(key, ++var);
			return var;
		}
	}

	@Override
	public long decr(String key, int i, int initvar) throws Exception {
		if (cache.get(key) == null) {
			cache.put(key, Long.valueOf(initvar));
			return initvar;
		} else {
			long var = Long.valueOf((String) cache.get(key));
			cache.put(key, --var);
			return var;
		}
	}

}
