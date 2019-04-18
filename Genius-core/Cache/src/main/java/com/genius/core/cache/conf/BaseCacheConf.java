package com.genius.core.cache.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Architect.bian
 *
 */
@Component
public abstract class BaseCacheConf {

	public static String SPLITTER_KEY = ":";
	public static String SPLITTER_PATH = "/";
	public static final String DEFAULT_SERVER = "127.0.0.1:11211";
	public static final String DEFAULT_WEIGHT = "1";
	
	public static Properties cacheProps;
	public static Properties redisProps;
	public static String basePath = "";
	
	@Value("#{cache}")
	public void setBaseCacheConf(Properties cache) {
		BaseCacheConf.cacheProps = cache;
		BaseCacheConf.basePath = cache.getProperty("basePath");
	}
	
	@Value("#{redis}")
	public void setBaseRedisConf(Properties redis) {
		BaseCacheConf.redisProps = redis;
	}
}
