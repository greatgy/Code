package com.supergenius.xo.life.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Topic;

/**
 * 话题Dao
 * @author ChenQi
 * @date 2018年5月9日15:44:07
 */
@Component("lifeTopicDao")
public interface TopicDao extends BaseDao<Topic> {
	
	/**
	 * 获得我的收藏
	 * 
	 * @author ChenQi
	 * @data 2018年5月9日15:44:10
	 */
	List<Topic> getCollectList(Map<String, Object> map);
	
	/**
	 * 获取话题创建时间大于当前时间的最小创建时间
	 * @param map
	 * @return
	 * @author ChenQi
	 * @date 2018年5月9日15:44:14
	 */
	Date getCacheTime(Map<String, Object> map);

	/**
	 * @author 雍雪振
	 * @time 2018年5月22日下午5:51:55
	 * @description: 获取我参与的话题，主要是获得我评论的话题
	 */
	List<Topic> getComList(Map<String, Object> map);
	
}
