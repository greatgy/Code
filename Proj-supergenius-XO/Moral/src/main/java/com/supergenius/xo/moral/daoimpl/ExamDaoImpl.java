package com.supergenius.xo.moral.daoimpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.MongoDict;
import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.moral.moral.dao.ExamDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.Exam;
import com.supergenius.xo.moral.enums.EExam;

/**
 * 考试DAO实现
 * 
 * @author LiJiacheng
 */
@Component
public class ExamDaoImpl extends BaseMongoDaoImpl<Exam> implements ExamDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.moral.moral.dao.ExamDao#getDistinct(com.supergenius.xo.moral.enums.EExam, int)
	 * 
	 * @author: LiJiacheng 2015-8-13 上午11:47:46
	 */
	@Override
	public List<?> getDistinct(EExam type, int score) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put(MapperDict.type, type.toString());
		DBObject scoreDbObject = new BasicDBObject(MongoDict.$gte, score);
		dbObject.put(MapperDict.score, scoreDbObject);
		return getColl().distinct(MapperDict.useruid, dbObject);
	}

}
