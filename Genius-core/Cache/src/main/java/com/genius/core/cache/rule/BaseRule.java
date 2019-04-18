package com.genius.core.cache.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.cache.conf.BaseCacheConf;

/**
 * @author Architect.bian
 * /users/online:uidxxxxxx
 * CacheId:/users
 * path:/users/online
 * key:/users/online:uidxxxxxx
 * toString:/users/online:uidxxxxxx
 */
public abstract class BaseRule implements Rule {

	protected Map<Type, String> confKeys = new HashMap<Rule.Type, String>();

	private Map<Type, String> confValues = new HashMap<Rule.Type, String>();

	protected String key = "";
	
	protected String path = BaseCacheConf.basePath + "/";//所有Rule的基路径
	
	protected int expire = -1;//seconds

	private Set<String> keys = new HashSet<String>();

	public BaseRule() {
		this("");
	}

	public BaseRule(String k) {
		this.setKey(k);
	}

	/**
	 * 
	 * @param k
	 * @param exp seconds
	 */
	public BaseRule(String k, int exp) {
		this.setKey(k);
		this.expire = exp;//seconds
		refreshConfKey();
	}

	public void refreshConfKey() {
		String tmpConfKey = this.getPath().replace(BaseCacheConf.basePath, "");
		if(BaseCacheConf.cacheProps != null && BaseCacheConf.cacheProps.containsKey(tmpConfKey)) {
			String tmpconfValue = (String) BaseCacheConf.cacheProps.get(tmpConfKey);
			if (tmpconfValue != null) {
				this.confKeys.put(Type.memcached, tmpConfKey);
				this.confValues.put(Type.memcached, tmpconfValue);
			}
		}
		if(BaseCacheConf.redisProps != null && BaseCacheConf.redisProps.containsKey(tmpConfKey)) {
			String tmpconfValue = (String) BaseCacheConf.redisProps.get(tmpConfKey);
			if (tmpconfValue != null) {
				this.confKeys.put(Type.redis, tmpConfKey);
				this.confValues.put(Type.redis, tmpconfValue);
			}
		}
	}
	
	public String getConfKey() {
		return getConfKey(Type.memcached);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Rule#getCacheId()
	 */
	@Override
	public String getConfKey(Type type) {
		return this.confKeys.get(type) ;
	}
	
	@Override
	public String getConfValue() {
		return getConfValue(Type.memcached);
	}

	@Override
	public String getConfValue(Type type) {
		return this.confValues.get(type);
	}

	/* (non-Javadoc)
	 * @see com.genius.core.cache.Rule#getKey()
	 */
	@Override
	public String getKey() {
		return this.path + BaseCacheConf.SPLITTER_KEY + this.key;
	}

	@Override
	public void setKey(String k) {
		this.key = k;
	}

	@Override
	public void addKey(String k) {
		if (this.keys.size() == 0) {
			this.keys.add(this.path + BaseCacheConf.SPLITTER_KEY + key);
		}
		this.keys.add(this.path + BaseCacheConf.SPLITTER_KEY + k);
	}

	@Override
	public Set<String> getKeys() {
		if (this.keys.size() == 0) {
			this.keys.add(this.path + BaseCacheConf.SPLITTER_KEY + key);
		}
		return this.keys;
	}
	
	/* (non-Javadoc)
	 * @see com.genius.core.cache.Rule#getPath()
	 */
	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public String toString() {
		return this.getKey() + BaseStrDict.comma + this.getExpire();
	}

	@Override
	public int getExpire() {
		return this.expire;
	}

	@Override
	public void setExpire(int exp) {
		this.expire = exp;
	}

	@Override
	public int hashCode() {
		return StringUtils.isEmpty(this.getKey()) ? System.identityHashCode(this) : this.getKey().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this.hashCode() == o.hashCode()) {
			return true;
		} else {
			return false;
		}
	}
}
