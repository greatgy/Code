package com.supergenius.xo.manager.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.StudentDao;
import com.supergenius.xo.manager.entity.Student;
import com.supergenius.xo.manager.service.StudentSO;

/** 
 * 学员Impl
 * @author guanshiqian
 * @date 2016-4-27 上午10:06:31 
 */
@Service("managerStudentSOImpl")
public class StudentSOImpl extends BaseSOImpl<Student> implements StudentSO {

	@Autowired
	StudentDao dao;
	
	@Override
	protected BaseDao<Student> getDao() {
		return dao;
	}

	@Override
	public Student getOne(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.uid, uid);
		return getOne(map);
	}

}
