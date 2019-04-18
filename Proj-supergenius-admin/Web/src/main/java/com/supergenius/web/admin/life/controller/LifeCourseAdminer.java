package com.supergenius.web.admin.life.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeCourseHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Course;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.service.CourseSO;

/**
 * 课程管理页面
 * 
 * @author YangGuang
 * @date 2018年5月11日09:56:17
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeCourseAdminer extends BaseController {

	@Autowired
	AdminLogSO adminLogSO;

	@Autowired
	private CourseSO so;

	/**
	 * 进入后台科目管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/lifecourse" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifecourse.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifecourse, Locale.CHINA));
		return "dolifecourse";
	}

	/**
	 * 得到课程list
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/ajax/lifecourse/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeCourseHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加课程
	 * 
	 * @param model
	 * @param name
	 * @param grade
	 * @param request
	 * @author YangGuang
	 * @date 2018年5月11日09:59:27
	 * @return
	 */
	@RequestMapping(value = "/life/ajax/lifecourse/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String[] imgdata, int grade, String press, String useadress, String publishedtime, int subject, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(grade) && StrUtil.isNotEmpty(subject) && StrUtil.isNotEmpty(imgdata)) {
			Course course = new Course();
			course.setImgoriginal(imgdata[0]);
			course.setImgbig(imgdata[1]);
			course.setImgmedium(imgdata[2]);
			course.setImglittle(imgdata[3]);
			course.setName(name);
			course.setPress(press);
			course.setUseadress(useadress);
			if (StrUtil.isNotEmpty(publishedtime)) {
				course.setPublishtime(DateUtil.parse(publishedtime));
			}
			course.setGrade(EGrade.get(grade));
			course.setSid(subject);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				course.setAdminuid(AdminHP.getAdminUid());
			}
			if (so.add(course)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑科目
	 * 
	 * @author YangGuang
	 * @date 2018年5月11日10:09:49
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifecourse/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, String uid, String name, String[] imgdata, int grade,String press, String useadress, String publishtimeStr, int subject, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(grade) && StrUtil.isNotEmpty(subject)) {
			Course course = so.get(uid);
			if (StrUtil.isNotEmpty(imgdata)) {
				course.setImgoriginal(imgdata[0]);
				course.setImgbig(imgdata[1]);
				course.setImgmedium(imgdata[2]);
				course.setImglittle(imgdata[3]);
			}
			course.setName(name);
			course.setGrade(EGrade.get(grade));
			course.setSid(subject);
			course.setPress(press);
			course.setUseadress(useadress);
			course.setPublishtime(DateUtil.parse(publishtimeStr));
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				course.setAdminuid(AdminHP.getAdminUid());
			}
			if (so.update(course)) {
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @typename Map<String,Object>
	 * @date 2018年5月11日10:10:14
	 */
	@RequestMapping(value = "/life/ajax/lifecourse/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			if (so.updateStatusByUid(uid)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 根据年级返回科目
	 * 
	 * @author YangGuang
	 * @typename Map<String,Object>
	 * @date 2018年5月11日10:18:55
	 */
	@RequestMapping(value = "/life/ajax/lifecourse/subjects", method = RequestMethod.GET)
	@ResponseBody
	public String getSubjects(int grade, HttpServletResponse response) {
		List<Subject> list = LifeCourseHP.getSubject(grade);
		response.setContentType("text/html;charset=UTF-8");
		return JsonUtil.toJson(list, Json.webStrategy);
	}

}
