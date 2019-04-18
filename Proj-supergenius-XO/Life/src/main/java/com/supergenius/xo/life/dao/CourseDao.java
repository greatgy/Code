package com.supergenius.xo.life.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.life.entity.Course;

/**
 * 课程Dao
 *@Author:JiaShitao
 *@Date:2018年5月7日下午5:50:28
 */
@Component("lifeCoursesDao")
public interface CourseDao extends BaseDao<Course> {

}
