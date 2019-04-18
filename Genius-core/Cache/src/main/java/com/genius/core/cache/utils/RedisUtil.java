package com.genius.core.cache.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.cache.conf.BaseCacheConf;
import com.genius.core.cache.rule.Rule;
import com.google.common.base.Charsets;

import redis.client.RedisClient;
import redis.reply.BulkReply;
import redis.reply.IntegerReply;
import redis.reply.MultiBulkReply;
import redis.reply.Reply;
import redis.reply.StatusReply;

/**
 * @author Architect.bian
 *
 */
public class RedisUtil extends BaseUtil {

	private static Map<String, RedisClient> pool = new HashMap<String, RedisClient>();
	/**
	 * rule与截取key长度的map
	 */
	private static Map<String, Integer> mapKeyLen = new HashMap<>();
	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
	private static String keyLenName = "keylen";
	
	/**
	 * 配置：redis://localhost:6379/2?keylen=3
	 * @param rule
	 * @return
	 */
	protected static RedisClient getClient(Rule rule) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("getClient for Rule:%s, pool keyset:%s", rule, pool.keySet().toString()));
		}
		
		if (pool.containsKey(rule.getConfKey(Rule.Type.redis))) {
			return pool.get(rule.getConfKey(Rule.Type.redis));
		} else {
			try {
				URI uri = new URI(rule.getConfValue(Rule.Type.redis));
				String host = uri.getHost();
				int port = uri.getPort() > 0 ? uri.getPort() : 6379;
				String password = uri.getUserInfo() == null ? "" : uri.getUserInfo().split(":", 2)[1];
				Integer tableindex =  0;
				if (uri.getPath() != null && uri.getPath().indexOf("/") != -1 && uri.getPath().split("/", 2)[1].length() > 0) {
					tableindex = Integer.parseInt(uri.getPath().split("/", 2)[1]);
				}
				RedisClient client = new RedisClient(host, port);
				if (password.length() > 0) {
					client.auth(password);
				}
				client.select(tableindex);
				pool.put(rule.getConfKey(Rule.Type.redis), client);
				if (uri.getQuery() != null && uri.getQuery().indexOf(keyLenName) != -1) {
					int fromindex = uri.getQuery().indexOf(keyLenName);
					int beginIndex = uri.getQuery().indexOf("=", fromindex) + 1;
					int endIndex = uri.getQuery().indexOf("&", fromindex);
					if (endIndex == -1) {
						endIndex = uri.getQuery().length();
					}
					Integer len = rule.getPath().length() + BaseCacheConf.SPLITTER_KEY.length() + Integer.parseInt(uri.getQuery().substring(beginIndex, endIndex));
					mapKeyLen.put(rule.getConfKey(Rule.Type.redis), len);
				}
			} catch (IOException | URISyntaxException e) {
				logException(log, e);
			}
		}
		return pool.get(rule.getConfKey(Rule.Type.redis));
	}
	
	public static void destory() {
		try {
			Collection<RedisClient> clients = pool.values();
			for (RedisClient client : clients) {
				client.close();
			}
		} catch (IOException e) {
			logException(log, e);
		}
	}

	/**
	 * @param rule
	 * @return
	 */
	public static List<String> getAllKeys(Rule rule) {
		RedisClient client = getClient(rule);
		return toStringList(client.keys("*"));
	}
	
	/**
	 * 判断键值是否存在
	 * @author liubin
	 * @date 2017年8月29日 下午6:45:16
	 * @return boolean
	 */
	public static boolean isExistsKey(Rule rule) {
		RedisClient client = getClient(rule);
		if (mapKeyLen.containsKey(rule.getConfKey(Rule.Type.redis))) {
			return toInt(client.exists(rule.getKey().substring(0, mapKeyLen.get(rule.getConfKey(Rule.Type.redis))))) == 0 ? false : true;
		}
		return toInt(client.exists(rule.getKey())) == 0 ? false : true;
	}
	
	/**
	 * 计数，增1
	 * @param rule
	 * @return
	 */
	public static long incr(Rule rule) {
		RedisClient client = getClient(rule);
		String mapKey = rule.getConfKey(Rule.Type.redis);
		if (mapKeyLen.containsKey(mapKey)) {
			Integer hashKeyLen = mapKeyLen.get(mapKey);
			String k = rule.getKey().substring(0, hashKeyLen);
			String f = rule.getKey().substring(hashKeyLen);
			return toLong(client.hincrby(k, f, 1));
		}
		return toLong(client.incr(rule.getKey()));
	}
	
	/**
	 * 计数，减1
	 * @param rule
	 * @return
	 */
	public static long decr(Rule rule) {
		RedisClient client = getClient(rule);
		String mapKey = rule.getConfKey(Rule.Type.redis);
		if (mapKeyLen.containsKey(mapKey)) {
			Integer hashKeyLen = mapKeyLen.get(mapKey);
			String k = rule.getKey().substring(0, hashKeyLen);
			String f = rule.getKey().substring(hashKeyLen);
			return toLong(client.hincrby(k, f, -1));
		}
		return toLong(client.decr(rule.getKey()));
	}

	/**
	 * 返回计数结果,不增加值,-1代表值不存在
	 * @param rule
	 * @return
	 */
	public static long getIncr(Rule rule) {
		RedisClient client = getClient(rule);
		String mapKey = rule.getConfKey(Rule.Type.redis);
		if (mapKeyLen.containsKey(mapKey)) {
			Integer hashKeyLen = mapKeyLen.get(mapKey);
			String k = rule.getKey().substring(0, hashKeyLen);
			String f = rule.getKey().substring(hashKeyLen);
			return toLong(client.hget(k, f));
		}
		return toLong(client.get(rule.getKey()));
	}
	
	/**
	 * 设置计数值
	 * @param rule
	 * @param count
	 * @return
	 */
	public static boolean setIncr(Rule rule, int count) {
		RedisClient client = getClient(rule);
		String mapKey = rule.getConfKey(Rule.Type.redis);
		if (mapKeyLen.containsKey(mapKey)) {
			Integer hashKeyLen = mapKeyLen.get(mapKey);
			String k = rule.getKey().substring(0, hashKeyLen);
			String f = rule.getKey().substring(hashKeyLen);
			return isSuccess(client.hset(k, f, count));
		}
		return isSuccess(client.set(rule.getKey(), count));
	}
	
	public static boolean delIncr(Rule rule) {
		RedisClient client = getClient(rule);
		String mapKey = rule.getConfKey(Rule.Type.redis);
		if (mapKeyLen.containsKey(mapKey)) {
			Integer hashKeyLen = mapKeyLen.get(mapKey);
			String k = rule.getKey().substring(0, hashKeyLen);
			String f = rule.getKey().substring(hashKeyLen);
			return isSuccess(client.hdel(k, a(f)));
		}
		return isSuccess(client.del(a(rule.getKey())));
	}

	/**
	 * @param rule
	 * @param expect
	 */
	public static boolean set(Rule rule, Object value) {
		return isSuccess(getClient(rule).set(rule.getKey(), value));
	}
	
	public static boolean set(Rule rule, byte[] value) {
		return isSuccess(getClient(rule).set(rule.getKey(), value));
	}
	
	public static boolean remove(Rule rule) {
		return delete(rule);
	}

	/**
	 * @param rule
	 */
	public static boolean delete(Rule rule) {
		return isSuccess(getClient(rule).del(a(rule.getKey())));
	}
	
	public static String getStr(Rule rule) {
		return getClient(rule).get(rule.getKey()).asUTF8String();
	}
	
	public static byte[] getBytes(Rule rule) {
		return getClient(rule).get(rule.getKey()).data();
	}
	
	public static long getLong(Rule rule) {
		return toLong(getClient(rule).get(rule.getKey()));
	}

	/**
	 * @param followRule
	 * @return
	 */
	public static int getLLen(Rule rule) {
		return toInt(getClient(rule).llen(rule.getKey()));
	}
	
	/**
	 * 按照索引获取值
	 * @param rule
	 * @param index
	 * @return
	 */
	public static double lget(Rule rule, int index) {
		return toDouble(getClient(rule).lindex(rule.getKey(), index));
	}
	
	/**
	 * 获取指定区域的值
	 * @param rule
	 * @param start
	 * @param stop
	 * @return
	 */
	public static List<String> lrange(Rule rule, int start, int stop) {
		return getClient(rule).lrange(rule.getKey(), start, stop).asStringList(Charset.forName("UTF-8"));
	}
	
	/**
	 * 按照索引设置值
	 * @param rule
	 * @param index
	 * @param val
	 * @return
	 */
	public static boolean lset(Rule rule, int index, Object val) {
		return isSuccess(getClient(rule).lset(rule.getKey(), index, val));
	}
	
	/**
	 * 在最左边插入
	 * @param rule
	 * @param val
	 * @return
	 */
	private static boolean lpush(Rule rule, Object val) {
		return isSuccess(getClient(rule).lpush(rule.getKey(), a(val)));
	}
	
	/**
	 * 在最左插入多个值
	 * @param rule
	 * @param vals
	 * @return
	 */
	public static boolean lpush(Rule rule, Object... vals) {
		return isSuccess(getClient(rule).lpush(rule.getKey(), vals));
	}
	
	/**
	 * 将str添加到list最左(前)边
	 * @param rule
	 * @param str
	 * @return
	 */
	public static boolean addListStr(Rule rule, String str) {
		return lpush(rule, str);
	}
	
	/**
	 * 将object添加到list最左(前)边
	 * @param rule
	 * @param user
	 * @return 
	 */
	public static boolean addList(Rule rule, Object obj) {
		return lpush(rule, obj);
	}
	
	/**
	 * 从左到右返回所有的string list
	 * @param rule
	 * @return
	 */
	public static List<String> getListStr(Rule rule) {
		return toStringList(getClient(rule).lrange(rule.getKey(), 0, -1));
	}
	
	/**
	 * 从左到右返回所有的string list
	 * @param rule
	 * @return
	 */
	public static List<Object> getListObj(Rule rule) {
		return toObjectList(getClient(rule).lrange(rule.getKey(), 0, -1));
	}

	/**
	 * 从list删除value
	 * Command:lrem
	 * @param rule
	 * @param value
	 * @return
	 */
	public static boolean removeList(Rule rule, String value) {
		return isSuccess(getClient(rule).lrem(rule.getKey(), 0, value));
	}
	/**
	 * 保留最大数量的list值，超过maxcount之后的值则删除
	 * @param rule
	 * @param maxcount
	 * @return
	 */
	public static boolean ltrim(Rule rule, int maxcount) {
		return isSuccess(getClient(rule).ltrim(rule.getKey(), 0, maxcount - 1));
	}

	/**
	 * 得到hash类型某个rule的field的数量
	 * @param rule
	 * @return
	 */
	public static int getHLen(Rule rule) {
		return toInt(getClient(rule).hlen(rule.getKey()));
	}

	/**
	 * 获得某个int类型的field
	 * @param rule
	 * @param oid
	 * @return
	 */
	public static int getHInt(Rule rule, String field) {
		return toInt(getHField(rule, field));
	}

	/**
	 * 获得某个str类型的field
	 * @param rule
	 * @param oid
	 * @return
	 */
	public static String getHStr(Rule rule, String field) {
		return toStr(getHField(rule, field));
	}
	
	/**
	 * 一次性赋值给一个rule，将map存为hash类型
	 * @param rule
	 * @param map
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-12-27 下午8:14:33
	 */
	public static boolean setHash(Rule rule, Map<String, Object> map) {
		for (String field : map.keySet()) {
			getClient(rule).hset(rule.getKey(), field, map.get(field));//TODO 命令是否支持一次处理？
		}
		return true;
	}

	public static boolean setHash(Rule rule, String field, Object val) {
		return isSuccess(getClient(rule).hset(rule.getKey(), field, val));
	}
	
	public static boolean setMHash(Rule rule, Object... fieldsvals) {
		return isSuccess(getClient(rule).hmset(rule.getKey(), fieldsvals));
	}
	
	public static boolean delHash(Rule rule, String field) {
		return isSuccess(getClient(rule).hdel(rule.getKey(), a(field)));
	}

	public static List<String> getHList(Rule rule) {
		return toStringList(getClient(rule).hgetall(rule.getKey()));
	}

	public static List<String> getHKeys(Rule rule) {
		return toStringList(getClient(rule).hkeys(rule.getKey()));
	}

	public static List<String> getHVals(Rule rule) {
		return toStringList(getClient(rule).hvals(rule.getKey()));
	}
	
	public static boolean setSortedSet(Rule rule, String field, int val) {
		return addSortedSet(rule, field, val);
	}
	
	public static boolean addSortedSet(Rule rule, String field, int val) {
		return isSuccess(getClient(rule).zadd(a(rule.getKey(), val, field)));
	}
	
	public static boolean setSortedSet(Rule rule, String field, long val) {
		return addSortedSet(rule, field, val);
	}
	
	public static boolean addSortedSet(Rule rule, String field, long val) {
		return isSuccess(getClient(rule).zadd(a(rule.getKey(), val, field)));
	}

	/**
	 * 排序返回前count数量的list
	 * @param rule
	 * @param count 0则全部返回
	 * @return
	 */
	public static List<String> getSortedSetDesc(Rule rule, int count) {
		return toStringList(getClient(rule).zrevrange(rule.getKey(), 0, count - 1, null));
	}

	/**
	 * 将两个rule的数据合并再存到torule中
	 * @param fromrule
	 * @param torule
	 * @return
	 */
	public static boolean unionSortedSet(Rule fromrule, Rule torule) {
		return isSuccess(getClient(fromrule).zunionstore(torule.getKey(), 2, a(fromrule.getKey(), torule.getKey())));
	}

	/**
	 * @param rule
	 * @param uid
	 * @return
	 */
	public static long getSortSetVal(Rule rule, String field) {
		return toLong(getClient(rule).zscore(rule.getKey(), field));
	}

	/**
	 * @param rule
	 * @param uid
	 * @return
	 */
	public static long incrSortedSet(Rule rule, String field) {
		return toLong(getClient(rule).zincrby(rule.getKey(), 1, field));
	}

	public static long decrSortedSet(Rule rule, String field) {
		return toLong(getClient(rule).zincrby(rule.getKey(), -1, field));
	}
	
	public static boolean delSortedSet(Rule rule, String field) {
		return isSuccess(getClient(rule).zrem(rule.getKey(), a(field)));
	}
	
	
	/**
	 * 根据参数返回数组
	 * @param args
	 * @return
	 */
	private static Object[] a(Object... args) {
		return args;
	}

	/**
	 * 返回str字符串
	 * @param field
	 * @return
	 */
	private static String toStr(BulkReply field) {
		String result = field.asUTF8String();
		log.debug(String.format("toStr result:%s", result));
		return result;
	}

	/**
	 * @param rule
	 * @param field
	 * @return
	 */
	private static BulkReply getHField(Rule rule, String field) {
		return getClient(rule).hget(rule.getKey(), field);
	}
	
	private static int toInt(IntegerReply reply) {
		int result = new Long(reply.data()).intValue();
		log.debug(String.format("toInt result:%s", result));
		return result;
	}

	private static int toInt(BulkReply reply) {
		int result = reply.asUTF8String() == null ? 0 :Integer.parseInt(reply.asUTF8String());
		log.debug(String.format("toInt result:%s", result));
		return result;
	}
	
	private static long toLong(IntegerReply reply) {
		Long result = reply.data();
		log.debug(String.format("toLong result:%s", result));
		return result;
	}

	private static long toLong(BulkReply reply) {
		long result = reply.asUTF8String() == null ? -1 :Long.parseLong(reply.asUTF8String());
		log.debug(String.format("toLong result:%s", result));
		return result;
	}
	
	private static double toDouble(BulkReply reply) {
		double result = reply.asUTF8String() == null ? -1 :Double.parseDouble(reply.asUTF8String());
		log.debug(String.format("toLong result:%s", result));
		return result;
	}	
	
	/**
	 * @param set
	 * @return
	 */
	private static boolean isSuccess(StatusReply reply) {
		return reply.data().equals("OK");
	}

	/**
	 * @param lrange
	 * @return
	 */
	private static List<String> toStringList(MultiBulkReply reply) {
		List<String> result = reply.asStringList(Charsets.UTF_8);
		log.debug(String.format("toStringList result:%s", result == null? null : result.toArray().toString()));
		return result;
	}
	
	/**
	 * @param lrange
	 * @return
	 */
	private static List<Object> toObjectList(MultiBulkReply reply) {
		List<Object> list = new ArrayList<>();
		for (Reply<?> item : reply.data()) {
			list.add(item.data());
		}
		return list;
	}

	/**
	 * @param lpush
	 * @return
	 */
	private static boolean isSuccess(IntegerReply reply) {
		return reply.data() >= 0;
	}

}
