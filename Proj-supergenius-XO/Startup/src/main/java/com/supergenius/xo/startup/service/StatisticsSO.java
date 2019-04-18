package com.supergenius.xo.startup.service;

import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Statistics;

/**
 * 调查结果统计so
 * 
 * @author ChenQi
 * @date 2017年6月20日11:58:34
 */
public interface StatisticsSO extends BaseSO<Statistics> {

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @author ChenQi
	 * @return
	 */
	boolean update(String[] ids);

	/**
	 * 获取所有统计信息数量
	 * 
	 * @param ids
	 * @author ChenQi
	 * @return
	 */
	public int getCount(EStatus status);
	
	/**
	 * 获得各规则获取人数
	 * 
	 * @param ids
	 * @author ChenQi
	 * @return
	 */
	public Map<String, Long> getCountByRuler();
}
