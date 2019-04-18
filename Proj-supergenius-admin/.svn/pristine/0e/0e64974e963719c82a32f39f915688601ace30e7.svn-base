package com.supergenius.web.admin.life.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeSubjectHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Subject;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.service.SubjectSO;

/**
 * 科目管理页面
 * 
 * @author YangGuang
 * @date 2018年5月10日15:28:24
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class SubjectAdminer extends BaseController {


	@Autowired
	AdminLogSO adminLogSO;

	@Autowired
	private SubjectSO so;

	/**
	 * 进入后台科目管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/lifesubject" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifesubject.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifesubject, Locale.CHINA));
		return "dolifesubject";
	}

	/**
	 * 得到科目list
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/ajax/lifesubject/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeSubjectHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加科目
	 * 
	 * @param model
	 * @param name
	 * @param grade
	 * @param request
	 * @author YangGuang
	 * @date 2018年5月9日18:20:06
	 * @return
	 */
	@RequestMapping(value = "/life/ajax/lifesubject/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String[] grade, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(grade)) {
			Subject subject = new Subject();
			int grades = 0;
			for (String item : grade) {
				grades = grades | Integer.valueOf(EGrade.get(item).toString());
			}
			subject.setGrade(grades);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				subject.setAdminuid(AdminHP.getAdminUid());
			}
			subject.setName(name);
			if (so.add(subject)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑科目
	 * 
	 * @author YangGuang
	 * @date 2018年5月9日18:19:35
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifesubject/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, int sid, String name, String[] grade, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(sid) && StrUtil.isNotEmpty(grade)) {
			Subject subject = so.get(sid);
			if (subject != null) {
				subject.setName(name);
				int grades = 0;
				for (String item : grade) {
					grades = grades | Integer.valueOf(EGrade.get(item).toString());
				}
				subject.setGrade(grades);
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					subject.setAdminuid(AdminHP.getAdminUid());
				}
				if (so.update(subject)) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @typename Map<String,Object>
	 * @date 2018年5月9日18:19:22
	 */
	@RequestMapping(value = "/life/ajax/lifesubject/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(int sid) {
		if (StrUtil.isNotEmpty(sid)) {
			if (so.updateStatusBySid(sid)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}

}
