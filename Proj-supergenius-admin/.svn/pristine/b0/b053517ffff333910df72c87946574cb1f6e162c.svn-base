package com.supergenius.web.admin.moral.controller;

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
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.ExamHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.service.ExamSO;

/**
 * 考试管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ExamAdminer extends BaseController{
	
	@Autowired
	ExamSO so;
	
	@RequestMapping(value = { "/moral/exam" }, method = RequestMethod.GET)
	public String exam(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.exam.name());
		model.put(ViewKeyDict.site, EChannel.moral.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.exam, Locale.CHINA));
		return "doexam";
	}

	/**
	 * 查询时组织的数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/exam/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> exam_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ExamHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

}
