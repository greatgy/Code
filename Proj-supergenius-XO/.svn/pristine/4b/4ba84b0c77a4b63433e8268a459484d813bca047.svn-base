package com.supergenius.xo.user.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.User;

/** 
 * UserDao
 * @author chenminchang
 * @date 2016-3-24 下午12:54:06 
 */
public interface UserDao extends BaseDao<User> {
	
	/**
	 * 根据裁判的sn与用户的showname模糊查询裁判
	 * @param map
	 * @return
	 * @author XieMing
	 * 2016-8-8 下午12:16:29
	 */
	List<Map<String, Object>> searchJudge(Map<String, Object> map);

	/**
	 * 查询某一专业某一级别的所有学员
	 * @param map
	 * @return
	 * @author XieMing
	 * 2016-8-8 下午3:01:59
	 */
	List<User> searchStudent(Map<String, Object> map);
	
	/**
	 * 找人搜索
	 * @param map
	 * @return
	 * @author ChenQi
	 * 2017年4月28日  17:06:47
	 */
	List<User> searchCustomer(Map<String, Object> map);
	
	/**
	 * 找人搜索count
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2017年5月31日下午4:14:53
	 * @return int
	 */
	int searchCount(Map<String, Object> map);
	
	/**
	 * 获得所有用户uid
	 * @param map
	 * @return
	 * @author yangguang
	 * @createtime 2017年9月4日11:24:22
	 * @return 
	 */
	List<String> getUids(Map<String, Object> map);
}
