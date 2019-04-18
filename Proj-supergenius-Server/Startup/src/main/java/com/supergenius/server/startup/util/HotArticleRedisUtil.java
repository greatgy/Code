package com.supergenius.server.startup.util;

import java.util.List;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.core.rule.StartupHotArticleRlue;

/**
 * 热门文章redis工具类
 * 
 * @return
 * @author yangguang
 * @date 2017年10月27日14:51:16
 */
public class HotArticleRedisUtil extends BaseUtil {
	
	/**
	 * 根据权重值增加计数
	 * @param weight
	 * @param uid
	 * @return
	 */
	public static void incrValue(String uid, double weight) {
		Rule rule = new StartupHotArticleRlue(uid);
		double temp = lget(uid);
		temp += weight;
		RedisUtil.lset(rule, 0, temp);
		RedisUtil.ltrim(rule, 30);
	}
	
	/**
	 * 根据权重值增加计数
	 * @param weight
	 * @param uid
	 * @return
	 */
	public static double lget(String uid) {
		Rule rule = new StartupHotArticleRlue(uid);
		if (RedisUtil.lget(rule, 0) < 0) {
			RedisUtil.addList(rule, 0);
		}
		return RedisUtil.lget(rule, 0);
	}
	
	/**
	 * 获取前几天的值和
	 * @param days
	 * @param uid
	 * @return
	 */
	public static double getRange(String uid, int day) {
		Rule rule = new StartupHotArticleRlue(uid);
		List<String> temp = RedisUtil.lrange(rule, 0, day);
		float result = 0;
		for(String string : temp){
			result += Double.parseDouble(string);
		}
		return result;
	}
	
	/**
	 * 每天定时在列表前面添加一个0值
	 * @param days
	 * @param uid
	 * @return
	 */
	public static void addTimer(List<String> uids) {
		for(String uid : uids){
			Rule rule = new StartupHotArticleRlue(uid);
			RedisUtil.addList(rule, 0);
		}
	}
}
