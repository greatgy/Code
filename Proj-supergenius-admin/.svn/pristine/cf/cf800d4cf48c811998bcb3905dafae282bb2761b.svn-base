package com.supergenius.web.admin.startup.controller;

import java.util.HashMap;
import java.util.List;
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
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.startup.helper.QuestionHP;
import com.supergenius.web.admin.startup.helper.StatisticsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.entity.Question;
import com.supergenius.xo.startup.entity.Statistics;
import com.supergenius.xo.startup.service.StatisticsSO;

/**
 * 统计信息controller
 * 
 * @author ChenQi
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class StatisticsAdminer extends BaseController {

	@Autowired
	private StatisticsSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台statistics管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/startup/statistic" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.statistic.name());
		model.put(ViewKeyDict.site, ESite.startup.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.statistic, Locale.CHINA));
		model.put(ViewKeyDict.total, so.getCount(EStatus.enable));
		model.put(ViewKeyDict.countMap, so.getCountByRuler());
		return "dostatistic";
	}

	/**
	 * 得到statisticslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/startup/ajax/statistic/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> statistic_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StatisticsHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 得到questionlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/startup/ajax/statistic/question/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> question_list(Map<String, Object> model, HttpServletRequest request) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put(MapperDict.status, EStatus.enable);
		searchMap.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.sql_order + MapperDict.asc);
		List<Question> allQuestionList = QuestionHP.getQuestionList(searchMap);
		StatisticsHP.querys(allQuestionList);
		searchMap.put(ViewKeyDict.rows, allQuestionList);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除测试数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/startup/ajax/statistic/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> statistics_delete(String[] ids) {
		Statistics statistics = so.get(ids[0]);
		if (statistics != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.statistic.toInt());
			adminLog.setOperation(EAdminLog.deleteStatistics.getName());
			adminLog.setData(EAdminLog.deleteStatistics.getName());
			adminLog.setDesc(EAdminLog.deleteStatistics.getName());
			adminLog.setDataid(ids[0]);
			so.delete(statistics.getUid());
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
}
