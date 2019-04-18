package com.supergenius.xo.manager.dao;

import java.util.Map;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.manager.entity.Video;

/**
 * 视频Dao
 * @author XieMing
 * @date 2016-7-18 下午2:25:44
 */
public interface VideoDao extends BaseDao<Video> {

	/**
	 * 获得视频的总播放量
	 * @param map
	 * @return
	 * @author liubin
	 * @createtime 2016-9-12上午11:03:19
	 * @return int
	 */
	Integer getTotalPlayCount(Map<String, Object> map);
}
