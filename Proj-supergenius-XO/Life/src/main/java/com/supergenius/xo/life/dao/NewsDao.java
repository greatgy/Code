package com.supergenius.xo.life.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.News;

/**
 * 消息Dao
 * 
 * @author YangGuang
 * @date 2018年5月9日15:58:50
 */
@Component("lifeNewsDao")
public interface NewsDao extends BaseDao<News>{

	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
