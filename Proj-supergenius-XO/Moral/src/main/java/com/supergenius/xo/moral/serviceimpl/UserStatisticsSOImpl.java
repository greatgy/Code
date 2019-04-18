package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.supergenius.moral.moral.dao.UserStatisticsDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.UserStatistics;
import com.supergenius.xo.moral.service.UserStatisticsSO;

/**
 * 用户统计so的实现
 * 
 * @author liushaomin
 */
@Service("MoralUserStatisticsSOImpl")
public class UserStatisticsSOImpl extends BaseSOImpl<UserStatistics> implements UserStatisticsSO {

	@Autowired
	UserStatisticsDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<UserStatistics> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updatecount(com.supergenius.xo.moral.entity.Student)
	 */
	@Override
	public boolean updateCountdl(String useruid, int countdl) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.countdl, countdl);
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updataUserStat(java.lang.String, int, int)
	 */
	@Override
	public boolean updateCountupload(String useruid, int countupload) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.countupload, countupload);
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#getOneByUseruid(java.lang.String)
	 */
	@Override
	public UserStatistics getOneByUseruid(String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updataScore(java.lang.String, int)
	 */
	@Override
	public boolean updateScore(String useruid, int score) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.score, score);
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#getTopTenScore(java.util.Map, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-7-22 下午6:42:37
	 */
	@Override
	public List<UserStatistics> getTopTenScore(Map<String, Object> map) {
		map.put(BaseMapperDict.pageSize, 10);
		map.put(BaseMapperDict.orderBy, MapperDict.score);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updatedays(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-7-24 下午5:17:23
	 */
	@Override
	public boolean updateCountDays(String useruid) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.countdaysign, 1);
		DBObject whereObject = new BasicDBObject(where);
		DBObject field = new BasicDBObject(fields);
		DBObject fieldsObject = new BasicDBObject(MongoDict.$inc, field);
		return dao.update(whereObject, fieldsObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updateDayOne(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-7-24 下午5:36:41
	 */
	@Override
	public boolean updateDayOne(String useruid) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.countdaysign, 1);
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updataExamTime(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateExamTime(String useruid, DateTime updatatime) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.examtime, updatatime.getMillis());
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.UserStatisticsSO#updateSignZero(java.util.Map)
	 * 
	 * @author: LiJiacheng 2015-8-27 下午6:40:04
	 */
	@Override
	public boolean updateSignZero(String uidString) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, uidString);
		Map<String, Object> signMap = new HashMap<>();
		signMap.put(MapperDict.countdaysign, 0);
		return dao.update(map, signMap);
	}

}
