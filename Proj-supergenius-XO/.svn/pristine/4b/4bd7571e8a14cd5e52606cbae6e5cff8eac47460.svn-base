package com.supergenius.xo.sudokuapi.daoimpl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.xo.sudokuapi.dao.RulesDao;
import com.supergenius.xo.sudokuapi.entity.Rules;

/**
 * 游戏规则dao实现
 * 
 * @author liushaomin
 */
@Component
public class RulesDaoImpl extends BaseSudokuMongoDaoImpl<Rules> implements RulesDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sudoku.xo.dao.RulesDao#getRules(com.mongodb.DBObject, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-26 上午10:53:11
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getRules(DBObject dbObject, String details) {
		DBObject backDbObject = new BasicDBObject(details, 1);
		DBObject resultDbObject = getColl().findOne(dbObject, backDbObject);
		return resultDbObject.toMap();
	}

}
