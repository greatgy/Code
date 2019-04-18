package com.supergenius.web.admin.life.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeAnswerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Answer;
import com.supergenius.xo.life.service.AnswerSO;

/**
 * 天才人回答管理
 * 
 * @author JiaShitao
 * @date 2018年5月11日16:29:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeAnswerAdminer extends BaseController {

	@Autowired
	private AnswerSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 天才人生问题页面
	 * 
	 * @author JiaShitao
	 * @date 2018年5月11日16:21:35
	 * @return String
	 */
	@RequestMapping(value = "/life/lifeanswer", method = RequestMethod.GET)
	public String answer(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifeanswer.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifeanswer, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.life.name());
		Map<String, Object> map = new HashMap<>();
	    List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(ViewKeyDict.count, so.getCount());
		model.put(ViewKeyDict.rows, so.getList());
		// model.put(ViewKeyDict.catelogueList, LifeanswerHP.getCatalogueList());
		return "dolifeanswer";
	}

	/**
	 * 显示列表
	 * 
	 * @author JiaShitao
	 * @date 2018年5月11日16:50:19
	 * @return String
	 */
	@RequestMapping(value = "/life/ajax/lifeanswer/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> answer_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeAnswerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 冻结解冻
	 * 
	 * @param uid
	 * @param status
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月10日17:17:14
	 */
	@RequestMapping(value = "/life/ajax/lifeanswer/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> answer_delete(String uid, @PathVariable int status) {
		Answer answer = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (answer != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.lifeanswer.toInt());
			adminLog.setOperation(EAdminLog.updateLifeAnswer.getName());
			adminLog.setData(EAdminLog.updateLifeAnswer.getName());
			adminLog.setDesc(EAdminLog.updateLifeAnswer.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			answer.setStatus(EStatus.get(status));
			answer.setAdminuid(adminuid);
			answer.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			if (so.update(answer)) {
				if (EStatus.get(status) == EStatus.disable) {
					/* PuzzledProblemHP.getEngine().deleteByID(problem.getUid()); */
				} else if (EStatus.get(status) == EStatus.enable) {
					/*
					 * Map<String, Object> map = MapsUtil.toMap(problem, Maps.searchStrategy);
					 * map.put(MapperSearchDict.table, ESite.career.name());
					 * PuzzledProblemHP.getEngine().add(map);
					 */
				}
				/*
				 * Map<String, Object> map = new HashMap<>(); map.put(MapperDict.uid,
				 * problem.getUid()); PuzzledProblemHP.getEngine().delete(map);
				 */
				/*
				 * Rule rule = new LifeProblemRlue(); MemcacheUtil.remove(rule);
				 */
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月11日12:30:21
	 */
	@RequestMapping(value = "/life/ajax/lifeanswer/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> answer_delete(String[] ids) {
		for (String id : ids) {
			Answer answer = so.get(id);
			if (answer != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.lifeanswer.toInt());
				adminLog.setOperation(EAdminLog.updateLifeAnswer.getName());
				adminLog.setData(EAdminLog.updateLifeAnswer.getName());
				adminLog.setDesc(EAdminLog.updateLifeAnswer.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				answer.setStatus(EStatus.deleted);
				if (so.update(answer)) {
					/*
					 * Map<String, Object> map = new HashMap<>(); map.put(MapperDict.uid,
					 * problem.getUid()); PuzzleanswermHP.getEngine().delete(map);
					 */
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

}