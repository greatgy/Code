package com.supergenius.xo.ai.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.News;

/**
 * 消息Dao
 * @author ChenQi
 * @date 2017年9月19日10:07:11
 */
@Component("AiNewsDao")
public interface NewsDao extends BaseDao<News> {
	
	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
