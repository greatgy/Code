package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.AppStudent;

/** 
 * 申请学员Dao
 * @author chenminchang
 * @date 2016-3-21 下午5:00:24 
 */
public interface AppStudentDao extends BaseDao<AppStudent> {

	/**
	 * 根据搜索条件得到count
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午7:17:59
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据搜索条件得到List
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午7:19:14
	 */
	List<AppStudent> search(Map<String, Object> map);
}
