package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Complaint;

/**
 * 投诉举报Dao
 * @author XieMing
 * @date 2016-7-18 下午2:22:43
 */
public interface ComplaintDao extends BaseDao<Complaint> {

	/**
	 * 传入搜索条件得到搜索结果
	 * @param map
	 * @return
	 */
	List<Complaint> search(Map<String, Object> map);
	
	/**
	 * 传入搜索条件得到搜索数量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-6下午7:04:44
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 传入搜索条件得到搜索结果
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-11-13下午6:36:37
	 * @return List<Complaint>
	 */
	List<Complaint> searchList(Map<String, Object> map);
}
