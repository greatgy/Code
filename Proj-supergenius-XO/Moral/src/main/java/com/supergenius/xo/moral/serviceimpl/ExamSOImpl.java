package com.supergenius.xo.moral.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.MapBuilder;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.supergenius.moral.moral.dao.ExamDao;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Exam;
import com.supergenius.xo.moral.enums.EExam;
import com.supergenius.xo.moral.enums.EExamState;
import com.supergenius.xo.moral.service.ExamSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * 考试SO实现
 * 
 * @author LiJiacheng
 */
@Service
public class ExamSOImpl extends BaseSOImpl<Exam> implements ExamSO {

	@Autowired
	ExamDao dao;

	@Autowired
	UserDao userDao;

	@Override
	protected BaseDao<Exam> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Exam> getList(Map<String, Object> map) {
		List<Exam> exams = dao.getList(map);
		for (Exam item : exams) {
			User user = userDao.get(item.getUseruid());
			if (user != null) {
				item.setUsername(user.getShowname());
			}
		}
		return exams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#get(java.lang.String, com.supergenius.xo.moral.enums.EExam)
	 */
	@Override
	public Exam get(String useruid, EExam type, EExamState state) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		if (state != null) {
			map.put(MapperDict.state, state);
		}
		return dao.getOne(map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#get(java.lang.String, com.supergenius.xo.moral.enums.EExam)
	 */
	@Override
	public Exam get(String useruid, EExam type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}
	

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamSO#get(java.lang.String, com.supergenius.xo.moral.enums.EExam, java.lang.String[])
	 */
	@Override
	public Exam get(String useruid, EExam type, String[] states) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		map.put(MapperDict.state + MapperDict.suffix_in_key, states);
		return dao.getOne(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#getListExamScoreOrder(java.lang.String, int)
	 */
	@Override
	public List<Exam> getListExamScoreOrder(String ascdesc, int pageSize) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EExam.exam);
		map.put(MapperDict.orderBy, MapperDict.score);
		map.put(MapperDict.ascDesc, ascdesc);
		map.put(MapperDict.pageSize, pageSize);
		return dao.getList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#getCountByScore(com.supergenius.xo.moral.enums.EExam, int, int)
	 */
	@Override
	public int getCountByScore(EExam type, int beginscore, int endscore) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		map.put(MapperDict.score + MapperDict.suffix_greater_key, beginscore);
		map.put(MapperDict.score + MapperDict.suffix_lessOrEqual_key, endscore);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#getAveragehScore()
	 */
	@Override
	public String getAveragehScore(EExam type) {
		Map<String, Object> where = getParamMap();
		where.put(MapperDict.type, type);
		Map<String, Object> group = new HashMap<>();
		group.put(MapperDict.total, MapBuilder.start(MongoDict.$sum, 1).get());
		group.put("avgScore", MapBuilder.start(MongoDict.$avg, "$" + MapperDict.score).get());
		List<String> fields = new ArrayList<>();
		fields.add(MapperDict.username);
		Map<String, Object> sort = new HashMap<>();
		sort.put(MapperDict.total, -1);
		List<Map<String, Object>> list = dao.group(where, fields, group, sort, null);
		if (list.size() != 0) {
			return list.get(0).get("avgScore").toString();
		}
		return "0";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#getlistFinish(com.supergenius.xo.moral.enums.EExam)
	 */
	@Override
	public int getCountFinish(EExam type) {
		return dao.getDistinct(type, 90).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.ExamSO#getAveragedays(int)
	 * 
	 * @author: LiJiacheng 2015-8-25 下午4:09:10
	 */
	@Override
	public String getAveragedays(int questionNums, String useruid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, EExam.exercise);
		map.put(MapperDict.orderBy, MapperDict.begintime);
		List<Exam> allExams = dao.getList(map);
		int days = 1;
		for (int i = 0; i < allExams.size() - 1; i++) {
			if (allExams.get(i).getBegintime().toString(DateUtil.FORMAT_DATE_SHORT).equals(allExams.get(i + 1).getBegintime().toString(DateUtil.FORMAT_DATE_SHORT))) {
				continue;
			} else {
				days += 1;
			}
		}
		return questionNums / days + "";
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamSO#updateState(java.lang.String, com.supergenius.xo.moral.enums.EState)
	 */
	@Override
	public boolean updateState(Exam exam) {
		Map<String, Object> whereMap = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		whereMap.put(MapperDict.uid, exam.getUid());
		fields.put(MapperDict.state, exam.getState().toString());
		fields.put(MapperDict.score, exam.getScore());
		fields.put(MapperDict.finishtime, exam.getFinishtime());
		return dao.update(whereMap, fields);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamSO#getList(java.lang.String, com.supergenius.xo.moral.enums.EExam)
	 */
	@Override
	public List<Exam> getList(String useruid, EExam type) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, type);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.moral.service.ExamSO#getList(com.supergenius.xo.moral.enums.EExam, com.supergenius.xo.moral.enums.EState)
	 */
	@Override
	public List<Exam> getList(EExam type, EExamState state) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, type);
		map.put(MapperDict.state, state);
		return dao.getList(map);
	}
}
