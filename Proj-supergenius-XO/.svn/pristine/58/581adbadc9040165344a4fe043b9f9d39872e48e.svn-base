package com.supergenius.xo.life.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.life.dao.CourseDao;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.service.CourseSO;

/**
 * 課程SOImpl
 * 
 * @Author:JiaShitao
 * @Date:2018年5月7日下午6:08:45
 */
@Service("lifeCoursesSOImpl")
public class CourseSOImpl extends BaseSOImpl<Course> implements CourseSO {
	@Autowired
	CourseDao dao;

	@Override
	protected BaseDao<Course> getDao() {
		return dao;
	}

	@Override
	public boolean updateStatusByUid(String uid) {
		Course course = dao.get(uid);
		if (course.getStatus() == EStatus.disable) {
			course.setStatus(EStatus.enable);
		} else {
			course.setStatus(EStatus.disable);
		}
		return dao.update(course);
	}
}
