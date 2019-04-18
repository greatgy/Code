package com.supergenius.xo.ai.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.Collect;

/**
 * 收藏Dao
 * 
 * @author 杨光
 * @date 2017年8月23日14:16:27
 */
@Component("aiCollectDao")
public interface CollectDao extends BaseDao<Collect> {

	/**
	 * 获取收藏的人数
	 * 
	 * @param map
	 * @return
	 * @author ChenQi
	 * @create 2017年9月5日18:20:16
	 */
	/*int getCountByuseruid(Map<String, Object> map);*/
}
