package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Judge;

/** 
 * 裁判Dao
 * @author guanshiqian
 * @date 2016-4-28 上午11:43:27 
 */
public interface JudgeDao extends BaseDao<Judge> {
	
	/**
	 * 传入搜索条件得到搜索总数
	 * @param map
	 * @return
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 传入搜索条件得到搜索结果
	 * @param map
	 * @return
	 */
	List<Judge> search(Map<String, Object> map);
	
	/**
	 * 根据查询得到数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午4:28:44
	 * @return int
	 */
	int getQueryCount(Map<String, Object> map);
	
	/**
	 * 根据查询得到List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午4:29:06
	 * @return List<Judge>
	 */
	List<Judge> getQueryList(Map<String, Object> map);
}
