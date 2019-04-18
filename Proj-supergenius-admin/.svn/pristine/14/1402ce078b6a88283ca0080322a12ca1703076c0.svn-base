package com.supergenius.web.admin.life.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.service.CourseSO;
import com.supergenius.xo.life.service.SubjectSO;

/**
 * 广告位管理HP
 * 
 * @author YangGuang
 * @date 2018年5月9日18:22:39
 */
public class LifeCourseHP extends BaseHP {

	private static CourseSO so;
	private static SubjectSO subjectSO;
	private static AdminSO adminSO;
	
	private static CourseSO getSO() {
		if (so == null) {
			so = (CourseSO) spring.getBean(CourseSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}
	
	private static SubjectSO getSubjectSO() {
		if (subjectSO == null) {
			subjectSO = spring.getBean(SubjectSO.class);
		}
		return subjectSO;
	}

	/**
	 * 查询组织数据
	 * 
	 * @return map
	 * @author YangGuang
	 * @date 2018年5月11日10:13:21
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(BaseMapperDict.name + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.grade))) {
			map.put(MapperDict.grade, model.get(MapperDict.grade).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Course> list = getSO().getList(map);
		for (Course course : list) {
			if (StrUtil.isNotEmpty(course.getAdminuid())) {
				Admin admin = getAdminSO().get(course.getAdminuid());
				if (admin != null) {
					course.setAdminname(admin.getName());
				}
			}
			Subject subject = getSubjectSO().get(course.getSid());
			course.setSubjectname(subject.getName());
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 根据年级得到科目
	 * 
	 * @return map
	 * @author YangGuang
	 * @date 2018年5月11日10:24:49
	 */
	public static List<Subject> getSubject(int grade) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.grade, grade);
		return getSubjectSO().getList(map);
	}
}
