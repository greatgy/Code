package com.supergenius.xo.sudokuapi.dao;

import java.util.Map;

import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.mongodb.DBObject;
import com.supergenius.xo.sudokuapi.entity.Rules;

/**
 * 游戏规则dao
 * 
 * @author liushaomin
 */
public interface RulesDao extends BaseMongoDao<Rules> {

	/**
	 * 指定获取游戏积分规则中的一部分
	 * 
	 * @param dbObject
	 * @param details
	 * @return
	 * @author LiJiacheng
	 */
	Map<String, Object> getRules(DBObject dbObject, String details);
	
}
