package com.supergenius.web.front.life.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.front.life.helper.CourseHP;
import com.supergenius.web.front.life.helper.ProblemHP;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.enums.EGrade;

/**
 * 课程controller
 * 
 * @author: ChenQi
 * @date 2018年5月16日10:57:19
 */
@Controller
public class CourseController extends BaseController {
	
	/**
	 * 课程推荐页展示数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author: ChenQi 2018年5月16日11:53:18
	 */
	@RequestMapping(value = "/course/{cid:\\d+}", method = RequestMethod.GET)
	public String index(Map<String, Object> model, @PathVariable Long cid, Long pcid, HttpServletRequest request) {
		//进行获取课程
		List<Subject> list = CourseHP.getSubject(EGrade.six);
		List<Subject> sevenList = CourseHP.getSubject(EGrade.seven);
		List<Subject> eightList = CourseHP.getSubject(EGrade.eight);
		List<Subject> nineList = CourseHP.getSubject(EGrade.nine);
		model.put(ViewKeyDict.sixList, list);
		model.put(ViewKeyDict.sevenList, sevenList);
		model.put(ViewKeyDict.eightList, eightList);
		model.put(ViewKeyDict.nineList, nineList);
		//进行获取课程相对应的课本
		if (!list.isEmpty()) {
			model.put(ViewKeyDict.sixCourseList, CourseHP.getCourse(EGrade.six, list.get(0).getSid(), SysConf.bookGetSize));
		}
		if (!sevenList.isEmpty()) {
			model.put(ViewKeyDict.sevenCourseList, CourseHP.getCourse(EGrade.seven, sevenList.get(0).getSid(), SysConf.bookGetSize));
		}
		if (!eightList.isEmpty()) {
			model.put(ViewKeyDict.eightCourseList, CourseHP.getCourse(EGrade.eight, eightList.get(0).getSid(), SysConf.bookGetSize));
		}
		if (!nineList.isEmpty()) {
			model.put(ViewKeyDict.nineCourseList, CourseHP.getCourse(EGrade.nine, nineList.get(0).getSid(), SysConf.bookGetSize));
		}
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.problemList, ProblemHP.getProblemList(1, SysConf.DesignProblemSize, SysConf.CourseCid, SysConf.notmember));
		return "course";
	}
	
	/**
	 * 更多课程页
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author: ChenQi 2018年5月16日11:53:18
	 */
	@RequestMapping(value = "/course/morecourse", method = RequestMethod.GET)
	public String morecourse(Map<String, Object> model, int grade, int subid, HttpServletRequest request) {
		//进行获取课程
		List<Subject> list = CourseHP.getSubject(EGrade.six);
		List<Subject> sevenList = CourseHP.getSubject(EGrade.seven);
		List<Subject> eightList = CourseHP.getSubject(EGrade.eight);
		List<Subject> nineList = CourseHP.getSubject(EGrade.nine);
		model.put(ViewKeyDict.sixList, list);
		model.put(ViewKeyDict.sevenList, sevenList);
		model.put(ViewKeyDict.eightList, eightList);
		model.put(ViewKeyDict.nineList, nineList);
				
		model.put(ViewKeyDict.courseList, CourseHP.getCourse(EGrade.get(grade), subid, SysConf.bookSize));
		model.put(ViewKeyDict.subid, subid);
		model.put(ViewKeyDict.grade, grade);
		return "morecourse";
	}
	
	/**
	 * 异步获取课程列表
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/ajax/course", method = RequestMethod.GET)
	public String ajax_course(Map<String, Object> model, int grade, int sid, HttpServletRequest request) {
		List<Course> list = CourseHP.getCourse(EGrade.get(grade), sid, SysConf.bookGetSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxcourse";
	}
	
	/**
	 * 异步获取更多课程
	 */
	@RequestMapping(value = "/ajax/morecourse", method = RequestMethod.GET)
	public String ajax_morecourse(Map<String, Object> model, int grade, int subid) {
		model.put(ViewKeyDict.courseList, CourseHP.getCourse(EGrade.get(grade), subid, SysConf.bookSize));
		return "ajaxmorecourse";
	}
	
	/**
	 * @author 雍雪振
	 * @time 2018年5月23日下午4:27:00
	 * @description: 根据uid获取到课程信息
	 */
	@RequestMapping(value = "/ajax/course/detail", method = RequestMethod.GET)
	public String ajax_coursedetail(Map<String, Object> model, String uid, HttpServletRequest request) {
		model.put(ViewKeyDict.course, CourseHP.getOneCourse(uid));
		return "ajaxcoursedetail";
	}
}
