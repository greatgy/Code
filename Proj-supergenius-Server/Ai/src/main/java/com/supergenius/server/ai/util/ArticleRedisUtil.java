package com.supergenius.server.ai.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.core.rule.AiArticleRlue;

/**
 * 文章redis工具类
 * 
 * @return
 * @author xuzhixiang
 * @date 2017年9月19日16:42:37
 */
public class ArticleRedisUtil extends BaseUtil {

	/**
	 * 一次性赋值给一个rule，并将value保存为map类型
	 * 
	 * @param rule,
	 *            map
	 * @return
	 */
	public static boolean set(String uid, Map<String, Object> map) {
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
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
		Rule rule = new AiArticleRlue(uid);
		List<String> list = RedisUtil.getHKeys(rule);
		if (list.size() == 0) {
			return false;
		}
		return true;
	}
}
