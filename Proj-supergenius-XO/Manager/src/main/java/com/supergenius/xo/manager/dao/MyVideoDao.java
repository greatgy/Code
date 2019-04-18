package com.supergenius.xo.manager.dao;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.MyVideo;

/**
 * 我的视频Dao
 * 
 * @author liubin
 * @date 2016-8-12 上午10:36:35
 */
public interface MyVideoDao extends BaseDao<MyVideo> {

	/**
	 * 根据查询得到满足条件的我的视频List
	 * @param map
	 * @return
	 */
	List<MyVideo> getSearchList(Map<String, Object> map);
}
