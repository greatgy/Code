package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.UserLevel;

/** 
 * 级别明细Dao
 * @author guanshiqian
 * @date 2016-4-27 下午2:18:10 
 */
public interface UserLevelDao extends BaseDao<UserLevel> {

	/**
	 * 根据查询得到用户的数量
	 * @param map
	 * @return
	 */
	int getUserCount(Map<String, Object> map);
	
	/**
	 * 获取查询总数
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午6:31:25
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 根据查询得到用户的信息
	 * @param map
	 * @return
	 */
	List<UserLevel> getUserList(Map<String, Object> map);
	
	/**
	 * 获取查询list
	 * @param map
	 * @return
	 * @author chenminchang
	 * @create 2016-10-31下午6:32:18
	 */
	List<UserLevel> search(Map<String, Object> map);
	
}
