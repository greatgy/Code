package com.supergenius.xo.user.dao;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.user.entity.News;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息Dao
 * 
 * @author YangGuang
 * @date 2018年5月9日15:58:50
 */
@Component("userNewsDao")
public interface NewsDao extends BaseDao<News>{

	/**
	 * 通过useruid更新字段
	 * @param map
	 * @return
	 */
	boolean updateByUseruid(Map<String, Object> map);
}
