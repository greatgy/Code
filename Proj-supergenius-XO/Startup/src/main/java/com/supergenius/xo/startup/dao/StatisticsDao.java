package com.supergenius.xo.startup.dao;

import java.util.HashMap;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.startup.entity.Statistics;

/**
 * 调查结果统计Dao
 * 
 * @author ChenQi
 * @date 2017年6月20日15:12:38
 */
public interface StatisticsDao extends BaseDao<Statistics> {
	/**
	 * 批量删除
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @date 2017年9月11日19:40:07
	 */
	boolean updateByUids(Map<String, Object> map);

	/**
	 * 获得符合各规则的人数
	 * 
	 * @return
	 * @author ChenQi 2017年9月14日15:12:26
	 */
	HashMap<String, Object> getCountByRuler(Map<String, Object> map);
}
