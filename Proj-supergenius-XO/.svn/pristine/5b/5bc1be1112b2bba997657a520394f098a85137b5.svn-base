package com.supergenius.xo.manager.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.baseadmin.dao.AdminLogDao;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.AppStudentDao;
import com.supergenius.xo.manager.dao.StudentDao;
import com.supergenius.xo.manager.dao.UserLevelDao;
import com.supergenius.xo.manager.entity.AppStudent;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppStudentSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.dao.UserInfoDao;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.UserInfo;

/** 
 * 申请学员SOImpl
 * @author chenminchang
 * @date 2016-3-21 下午5:04:58 
 */
@Service
public class AppStudentSOImpl extends BaseSOImpl<AppStudent> implements AppStudentSO {

	@Autowired
	AppStudentDao dao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	AdminLogDao adminLogDao;
	
	@Autowired
	UserLevelDao userLevelDao;
	
	@Autowired
	UserLevelSO userLevelSO;

	@Override
	protected AppStudentDao getDao() {
		return dao;
	}

	@Override
	public AppStudent getOne(String userUid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, userUid);
		return dao.getOne(map);
	}

	@Override
	public AppStudent getOne(String uid, EMajor major) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, uid);
		map.put(MapperDict.major, major);
		return dao.getOne(map);
	}

	@Override
	public boolean add(AppStudent appStudent, Student student, UserInfo userInfo) {
		if (studentDao.get(student.getUid()) == null) {
			return dao.insert(appStudent) && studentDao.insert(student) && userInfoDao.update(userInfo);
		}
		return dao.insert(appStudent) && studentDao.update(student) && userInfoDao.update(userInfo);
	}

	@Override
	public AppStudent getOne(String userUid, EMajor major, EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.useruid, userUid);
		map.put(MapperDict.major, major);
		map.put(MapperDict.status, status);
		return dao.getOne(map);
	}

	@Override
	public int getCount(EStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MapperDict.status, status);
		return dao.getCount(map);
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public List<AppStudent> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public boolean update(AppStudent appStudent, AdminLog adminLog) {
		Map<String, Object> map = getParamMap(true);
		map.put(MapperDict.uid, appStudent.getUid());
		map.put(MapperDict.status, appStudent.getStatus());
		map.put(MapperDict.semester, appStudent.getSemester());
		map.put(MapperDict.reason, appStudent.getReason());
		return dao.updateFields(map) && adminLogDao.insert(adminLog);
	}


	@Override
	public boolean updateStatusAndType(AppStudent appStudent, Student student, User user, UserLevel userlevel, AdminLog adminLog) {
		Map<String, Object> appStudentMap = getParamMap(true);
		appStudentMap.put(MapperDict.uid, appStudent.getUid());
		appStudentMap.put(MapperDict.status, appStudent.getStatus());
		appStudentMap.put(MapperDict.semester, appStudent.getSemester());
		appStudentMap.put(MapperDict.reason, appStudent.getReason());
		
		Map<String, Object> studentMap = getParamMap(true);
		studentMap.put(MapperDict.uid, student.getUid());
		studentMap.put(MapperDict.status, student.getStatus());
		studentMap.put(MapperDict.majors, student.getMajors());
		
		Map<String, Object> userMap = getParamMap(true);
		userMap.put(MapperDict.uid, user.getUid());
		userMap.put(MapperDict.type, user.getType());
		
		return dao.updateFields(appStudentMap) && studentDao.updateFields(studentMap) && userDao.updateFields(userMap) && adminLogDao.insert(adminLog) && userLevelSO.disableOther(user.getUid(), EUser.student, userlevel.getMajor()) && userLevelDao.insert(userlevel);
	}
}
