package com.supergenius.server.life.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.life.rule.LifeTopicRlue;

/**
 * 论坛redis工具类
 * 
 * @return
 * @author loupengyu
 * @date 2018年2月2日12:07:54
 */
public class TopicRedisUtil extends BaseUtil {

	/**
	 * 一次性赋值给一个rule，并将value保存为map类型
	 * 
	 * @param rule,
	 *            map
	 * @return
	 */
	public static boolean set(String uid, Map<String, Object> map) {
		Rule rule = new LifeTopicRlue(uid);
		return RedisUtil.setHash(rule, map);
	}

	/**
	 * 根据uid和map中的key值取出int类型的value
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static Integer getInt(String uid, String key) {
		Rule rule = new LifeTopicRlue(uid);
		return RedisUtil.getHInt(rule, key);
	}

	/**
	 * 根据uid和map中的key值取出String类型的value
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static String getStr(String uid, String key) {
		Rule rule = new LifeTopicRlue(uid);
		return RedisUtil.getHStr(rule, key);
	}

	/**
	 * 根据uid和map中的key值,value+1
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static Integer incr(String uid, String key) {
		Rule rule = new LifeTopicRlue(uid);
		int val = RedisUtil.getHInt(rule, key);
		val++;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, val);
		set(uid, map);
		return val;
	}

	/**
	 * 根据uid和map中的key值,value-1
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static Integer decr(String uid, String key) {
		Rule rule = new LifeTopicRlue(uid);
		int val = RedisUtil.getHInt(rule, key);
		val--;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, val);
		set(uid, map);
		return val;
	}

	/**
	 * 根据uid,计算权重值。map数据例如:{"field1":0.3, "field2":0.2, "field3":0.5}
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static Float getWeight(String uid, Map<String, Float> map) {
		Rule rule = new LifeTopicRlue(uid);
		float weight = 0f;
		for (String key : map.keySet()) {
			int val = RedisUtil.getHInt(rule, key);
			weight += val * map.get(key);
		}
		return weight;
	}

	/**
	 * 根据uid,判断该文章中redis中是否初始化
	 * 
	 * @param rule,
	 *            string
	 * @return
	 */
	public static Boolean isInit(String uid) {
		Rule rule = new LifeTopicRlue(uid);
		List<String> list = RedisUtil.getHKeys(rule);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}
}
