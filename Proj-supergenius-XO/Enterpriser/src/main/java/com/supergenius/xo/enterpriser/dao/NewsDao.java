package com.supergenius.xo.enterpriser.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.enterpriser.entity.News;

/**
 * 消息Dao
 * 
 * @author YangGuang
 * @date 2018年1月29日10:27:56
 */
@Component("enterpriserNewsDao")
public interface NewsDao extends BaseDao<News>{

	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
