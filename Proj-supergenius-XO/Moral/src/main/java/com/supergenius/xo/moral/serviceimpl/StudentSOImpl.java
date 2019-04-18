package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.StudentDao;
import com.supergenius.moral.moral.dao.UserStatisticsDao;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Student;
import com.supergenius.xo.moral.entity.UserStatistics;
import com.supergenius.xo.moral.enums.ELevel;
import com.supergenius.xo.moral.service.StudentSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * 学员so的实现
 * 
 * @author liushaomin
 */
@Service
public class StudentSOImpl extends BaseSOImpl<Student> implements StudentSO {

	@Autowired
	StudentDao dao;

	@Autowired
	UserDao userdao;

	@Autowired
	UserStatisticsDao statisticsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 */
	@Override
	protected BaseDao<Student> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList(java.util.Map)
	 */
	@Override
	public List<Student> getList(Map<String, Object> map) {
		List<Student> students = getDao().getList(map);
		for (Student item : students) {
			User user = userdao.get(item.getUseruid());
			if (user != null) {
				item.setName(user.getShowname());
			}
			Map<String, Object> map2 = new HashMap<>();
			map2.put(MapperDict.useruid, item.getUseruid());
			UserStatistics statistics = statisticsDao.getOne(map2);
			if (statistics != null) {
				item.setUserStatistics(statistics);
			}
		}
		return students;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.status, eStatus);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#getOneByCertificateSN(java.lang.String)
	 */
	@Override
	public Student getOneByCertificateSN(String sn) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.certificate + MapperDict.dot + MapperDict.sn, sn);
		Student student = dao.getOne(map);
		if (student != null) {
			User user = userdao.get(student.getUseruid());
			if (user != null) {
				student.setName(user.getShowname());
			}
		}
		return student;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#getOneByUseruid(java.lang.String)
	 */
	@Override
	public Student getOneByUseruid(String useruid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, useruid);
		Student student = dao.getOne(map);
		if (student != null) {
			User user = userdao.get(student.getUseruid());
			if (user != null) {
				student.setName(user.getShowname());
			}
		}
		return student;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#updateLevel(com.supergenius.xo.moral.enums.ELevel, java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-8-20 下午3:01:36
	 */
	@Override
	public boolean updateLevel(ELevel level, String useruid) {
		Map<String, Object> whereMap = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		whereMap.put(MapperDict.useruid, useruid);
		fields.put(MapperDict.level, level.toString());
		return dao.update(whereMap, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#updateScore(com.supergenius.xo.moral.entity.Student)
	 */
	@Override
	public boolean updateScore(Student student) {
		Map<String, Object> whereMap = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		whereMap.put(MapperDict.useruid, student.getUseruid());
		fields.put(MapperDict.score, student.getScore());
		return dao.update(whereMap, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#getCountBeforeNow(java.lang.String)
	 */
	@Override
	public int getCountBeforeNow(DateTime startTime) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.StudentSO#getCountCertificate(java.lang.String)
	 */
	@Override
	public int getCountCertificate(DateTime startTime) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		map.put(MapperDict.certificate + MapperDict.suffix_no_key, null);
		return dao.getCount(map);
	}
}
