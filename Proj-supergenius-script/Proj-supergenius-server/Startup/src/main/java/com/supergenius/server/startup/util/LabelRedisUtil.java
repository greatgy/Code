package com.supergenius.server.startup.util;

import java.util.Date;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.core.rule.ClickCountStartupLabelRule;

/**
 * 标签redis工具类
 * 
 * @return
 * @author yangguang
 * @date 2017年9月7日17:28:39
 */
public class LabelRedisUtil extends BaseUtil {
	
	/**
	 * 根据uid得到map中当前日期对应的value；
	 * 
	 * @param uid
	 * @return
	 */
	public static int getInt(String uid) {
		String key = DateUtil.parseDate(new Date(), DateUtil.FORMAT_DATE_SHORT);
		Rule rule = new ClickCountStartupLabelRule(uid);
		return RedisUtil.getHInt(rule, key) > 0 ? RedisUtil.getHInt(rule, key) : 0;
	}
	
	/**
	 * 根据uid和key得到map中标签总点击量；
	 * 
	 * @param uid，key
	 * @return
	 */
	public static int getInt(String uid, String key) {
		Rule rule = new ClickCountStartupLabelRule(uid);
		return RedisUtil.getHInt(rule, key) > 0 ? RedisUtil.getHInt(rule, key) : 0;
	}
	
	/**
	 * 根据uid和map中的key值,value+1
	 * 
	 * @param uid
	 * @return
	 */
	public static Integer incr(String uid) {
		String key = DateUtil.parseDate(new Date(), DateUtil.FORMAT_DATE_SHORT);
		Rule rule = new ClickCountStartupLabelRule(uid);
		int val = RedisUtil.getHInt(rule, key);
		val++;
		RedisUtil.setHash(rule, key, val);
		return val;
	}

	/**
	 * 根据uid和map中的key值,value+1
	 * 
	 * @param uid
	 * @return
	 */
	public static Integer incr(String uid, String key) {
		Rule rule = new ClickCountStartupLabelRule(uid);
		int val = RedisUtil.getHInt(rule, key);
		val++;
		RedisUtil.setHash(rule, key, val);
		return val;
	}
}