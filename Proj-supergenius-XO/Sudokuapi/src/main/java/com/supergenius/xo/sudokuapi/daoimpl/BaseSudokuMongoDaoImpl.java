package com.supergenius.xo.sudokuapi.daoimpl;

import org.bson.types.ObjectId;

import com.genius.xo.mongodb.dao.BaseMongoDao;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;

/**
 * 游戏项目专用的com.sudoku.xo.daoimpl.BasesSudokuMongoDaoImpl
 * 
 * @author LiJiacheng
 */
public class BaseSudokuMongoDaoImpl<T> extends BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

	protected static String DefaultGetIDMethod = "getUidForDB";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.mongodb.daoimpl.AbstractMongoDao#getObjectID(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-25 下午5:05:38
	 */
	protected Object getObjectID(String id) {
		return new ObjectId(id);
	}

}
