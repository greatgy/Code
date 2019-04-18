package com.supergenius.web.front.life.helper;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.service.CourseSO;
import com.supergenius.xo.life.service.SubjectSO;

public class CourseHP extends BaseHP {

	private static CourseSO so;
	private static SubjectSO subjectSO;

	public static CourseSO getSO() {
		if (so == null) {
			so = (CourseSO) spring.getBean(CourseSO.class);
		}
		return so;
	}
	
	public static SubjectSO getSubjectSO() {
		if (subjectSO == null) {
			subjectSO = (SubjectSO) spring.getBean(SubjectSO.class);
		}
		return subjectSO;
	}

	/**
	 * 获得课程
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年5月16日12:00:00
	 */
	public static List<Course> getCourse(EGrade grade, int sid, int pagesize) {
		Pager pager = Pager.getNewInstance(0, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.grade, grade);
		map.put(MapperDict.sid, sid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.press + MapperDict.asc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		return getSO().getList(map);
	}

	/**
	 * 获得科目
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年5月16日12:00:00
	 */
	public static List<Subject> getSubject(EGrade grade) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.grade, grade);
		return getSubjectSO().getList(map);
	}
	/**
	 * @author 雍雪振
	 * @time 2018年5月23日下午4:27:40
	 * @description: 获得单个科目
	 */
	public static Object getOneCourse(String uid) {
		return getSO().get(uid);
	}
	
}
