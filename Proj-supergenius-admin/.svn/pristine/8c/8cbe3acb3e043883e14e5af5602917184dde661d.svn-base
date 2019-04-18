package com.supergenius.web.admin.career.controller;

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
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.core.rule.CareerProblemRlue;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.PuzzledProblemHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Problem;
import com.supergenius.xo.career.service.ProblemSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 困惑问题管理
 * 
 * @author yangguang
 * @date 2017年11月14日14:29:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class PuzzledProblemAdminer extends BaseController{
	
	@Autowired
	private ProblemSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 困惑问题页面
	 * 
	 * @author 杨光
	 * @date 2017年11月14日12:21:35
	 * @return String
	 */
	@RequestMapping(value = "/career/careerpuzzledproblem", method = RequestMethod.GET)
	public String problem(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerpuzzledproblem.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerpuzzledproblem, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		return "dopuzzledproblem";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> problem_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = PuzzledProblemHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author 杨光
	 * @data 2017年9月19日12:30:21
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> problem_delete(String[] ids) {
		for (String id : ids) {
			Problem problem = so.get(id);
			if (problem != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.careerpuzzledproblem.toInt());
				adminLog.setOperation(EAdminLog.deleteCareerProblem.getName());
				adminLog.setData(EAdminLog.deleteCareerProblem.getName());
				adminLog.setDesc(EAdminLog.deleteCareerProblem.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				problem.setStatus(EStatus.deleted);
				if (so.update(problem)) {
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.uid, problem.getUid());
					PuzzledProblemHP.getEngine().delete(map);
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 冻结解冻
	 * 
	 * @param ids
	 * @return
	 * @author 杨光
	 * @data 2017年11月14日12:35:14
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> problem_delete(String uid, @PathVariable int status) {
			Problem problem = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (problem != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.careerpuzzledproblem.toInt());
				adminLog.setOperation(EAdminLog.updateCareerProblem.getName());
				adminLog.setData(EAdminLog.updateCareerProblem.getName());
				adminLog.setDesc(EAdminLog.updateCareerProblem.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				problem.setStatus(EStatus.get(status));
				problem.setAdminuid(adminuid);
				problem.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				if (so.update(problem)) {
					if (EStatus.get(status) == EStatus.disable) {
						PuzzledProblemHP.getEngine().deleteByID(problem.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(problem, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.career.name());
						PuzzledProblemHP.getEngine().add(map);
					}
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.uid, problem.getUid());
					PuzzledProblemHP.getEngine().delete(map);
					Rule rule = new CareerProblemRlue();
					MemcacheUtil.remove(rule);
					return success();
				}
			}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 文章编辑
	 * 
	 * @param uid
	 * @param imgdata
	 * @param title
	 * @param author
	 * @param origin
	 * @param origin_radio
	 * @param type_radio
	 * @param cataloguetype
	 * @param summary
	 * @param content
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日12:40:05
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> problem_edit(String uid, String title, String content) {
		Problem problem = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (problem != null) {
			problem.setTitle(title);
			problem.setContent(content);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careerpuzzledproblem.toInt());
			adminLog.setDataid(problem.getUid());
			adminLog.setDesc(EAdminLog.updateCareerProblem.getName());
			adminLog.setData(EAdminLog.updateCareerProblem.getName());
			adminLog.setOperation(EAdminLog.updateCareerProblem.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				problem.setAdminuid(adminUid);
				problem.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(problem)) {
				if (problem.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					PuzzledProblemHP.getEngine().deleteByID(problem.getUid());
					problem.setContent(WebUtil.clearHtmlTag(problem.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(problem, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.career.name());
					PuzzledProblemHP.getEngine().add(map);
				} else {
					PuzzledProblemHP.getEngine().deleteByID(problem.getUid());
				}
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 设置置顶
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日13:53:03
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> problem_top(String[] ids) {
		if (so.update(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日13:57:23
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		if (so.update(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 获取置顶的个数
	 * 
	 * @author yangguang
	 * @date 2017年11月14日12:38:14
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledproblem/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> problem_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, PuzzledProblemHP.getTopArticleCount());
		return json(model, Json.webStrategy);
	}
}
