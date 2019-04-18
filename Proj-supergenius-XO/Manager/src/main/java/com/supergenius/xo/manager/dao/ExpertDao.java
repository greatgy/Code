package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Expert;

/** 
 * 专家Dao
 * @author guanshiqian
 * @date 2016-4-28 下午6:18:45 
 */
public interface ExpertDao extends BaseDao<Expert> {
	
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
	List<Expert> search(Map<String, Object> map);
	
	/**
	 * 得到指定一个或者多个状态的专家List
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-8-26下午5:53:13
	 * @return List<Expert>
	 */
	List<Expert> getListByStatus(Map<String, Object> map);
	
	/**
	 * 根据多种状态得到一个Expert对象
	 * @param map
	 * @author liubin
	 * @createtime 2016-8-28下午6:09:26
	 * @return Expert
	 */
	Expert getOneByStatus(Map<String, Object> map);
}
