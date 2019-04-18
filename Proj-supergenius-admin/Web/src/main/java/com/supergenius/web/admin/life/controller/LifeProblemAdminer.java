package com.supergenius.web.admin.life.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.admin.life.helper.LifeArticleHP;
import com.supergenius.web.admin.life.helper.LifeProblemHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Answer;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.service.AnswerSO;
import com.supergenius.xo.life.service.ProblemSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 天才人生问题管理
 * 
 * @author JiaShitao
 * @date 2018年5月9日14:29:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeProblemAdminer extends BaseController {

	@Autowired
	private ProblemSO so;

	@Autowired
	private AnswerSO answerSO;

	@Autowired
	private VisitorSO visitorSO;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 天才人生问题页面
	 * 
	 * @author JiaShitao
	 * @date 2018年5月9日12:21:35
	 * @return String
	 */
	@RequestMapping(value = "/life/lifeproblem", method = RequestMethod.GET)
	public String problem(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifeproblem.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifeproblem, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.life.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(ViewKeyDict.count, so.getCount());
		model.put(ViewKeyDict.rows, so.getList());
		return "dolifeproblem";
	}

	/**
	 * 显示列表
	 * 
	 * @author JiaShitao
	 * @date 2018年5月9日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/life/ajax/lifeproblem/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> problem_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeProblemHP.query(model);
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
	@RequestMapping(value = "/life/ajax/lifeproblem/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> problem_delete(String uid, @PathVariable int status) {
		Problem problem = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (problem != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.lifeproblem.toInt());
			adminLog.setOperation(EAdminLog.updateLifeProblem.getName());
			adminLog.setData(EAdminLog.updateLifeProblem.getName());
			adminLog.setDesc(EAdminLog.updateLifeProblem.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			problem.setStatus(EStatus.get(status));
			problem.setAdminuid(adminuid);
			problem.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			if (so.update(problem)) {
				if (EStatus.get(status) == EStatus.disable) {
					LifeArticleHP.getEngine().deleteByID(problem.getUid());
				} else if (EStatus.get(status) == EStatus.enable) {
					Map<String, Object> map = MapsUtil.toMap(problem, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.life.name());
					LifeArticleHP.getEngine().add(map);
				}
				Map<String, Object> map = new HashMap<>();
				map.put(MapperDict.uid, problem.getUid());
				LifeArticleHP.getEngine().delete(map);
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 点评/回复
	 * 
	 * @author JiaShitao
	 * @date 2018年5月15日11:15:41
	 * @return
	 */
	@RequestMapping(value = "/life/ajax/lifeproblem/comment_reply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> comment_reply(Map<String, Object> model, String uid, String infodata, String notreply, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid)) {
			Problem problem = so.get(uid);
			if (StrUtil.isNotEmpty(infodata)) {
				Answer answer = new Answer();
				answer.setFromuid(problem.getUid());
				answer.setFromuseruid(ViewKeyDict.defaultuid);
				answer.setFromuseroid(ViewKeyDict.defaultoid);
				answer.setFromusername(ViewKeyDict.system);
				User toUser = BaseUserHP.get(problem.getUseruid());
				if (toUser != null) {
					answer.setTousername(toUser.getUsername());
					answer.setTouseroid(toUser.getOid());
					answer.setTouseruid(toUser.getUid());
				} else {
					Visitor tovisitor = visitorSO.get(problem.getUseruid());
					answer.setTousername(tovisitor.getUsername());
					answer.setTouseroid(tovisitor.getOid());
					answer.setTouseruid(tovisitor.getUid());
				}

				answer.setType(EComment.major);
				answer.setChannel(ELifeChannel.problemcomments);
				answer.setContent(WebUtil.clearXSS(infodata));
				if (problem.getState() == EState.waitReply) {
					answer.setTouid(ViewKeyDict.defaultuid);
				}
				answerSO.add(answer);
				problem.setState(EState.over);
			} else if (StrUtil.isNotEmpty(notreply) && notreply.equals("on")) {
				problem.setState(EState.over);
			}
			if (so.update(problem)) {
				LifeProblemHP.sendMsg(problem);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月11日12:30:21
	 */
	@RequestMapping(value = "/life/ajax/lifeproblem/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> problem_delete(String[] ids) {
		for (String id : ids) {
			Problem problem = so.get(id);
			if (problem != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.lifeproblem.toInt());
				adminLog.setOperation(EAdminLog.updateLifeProblem.getName());
				adminLog.setData(EAdminLog.updateLifeProblem.getName());
				adminLog.setDesc(EAdminLog.updateLifeProblem.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				problem.setStatus(EStatus.deleted);
				if (so.update(problem)) {
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.uid, problem.getUid());
					LifeArticleHP.getEngine().delete(map);
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 话题编辑
	 * 
	 * @param uid
	 * @param title
	 * @param content
	 * @return
	 * @author Jiashitao
	 * @date 2018年5月14日18:56:15
	 */
	@RequestMapping(value = "/life/ajax/lifeproblem/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> problem_edit(String uid, String title, String content) {
		Problem problem = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (problem != null) {
			problem.setTitle(title);
			problem.setContent(content);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminUid);
			adminLog.setChannel(EChannel.lifeproblem.toInt());
			adminLog.setDataid(problem.getUid());
			adminLog.setDesc(EAdminLog.updateLifeProblem.getName());
			adminLog.setData(EAdminLog.updateLifeProblem.getName());
			adminLog.setOperation(EAdminLog.updateLifeProblem.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				problem.setAdminuid(adminUid);
			}
			if (so.update(problem)) {
				if (problem.getStatus().equals(EStatus.enable)) {// 删除索引后在添加索引
					LifeArticleHP.getEngine().deleteByID(problem.getUid());
					problem.setContent(WebUtil.clearHtmlTag(problem.getContent()).toString());// 清除格式
					Map<String, Object> map = MapsUtil.toMap(problem, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.life.name());
					LifeArticleHP.getEngine().add(map);
				} else {
					LifeArticleHP.getEngine().deleteByID(problem.getUid());
				}
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 获取点评信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/life/ajax/lifeproblem/answers", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_comments(String uid, HttpServletResponse response) {
		Problem problem = so.get(uid);
		List<Answer> list = LifeProblemHP.getSecondMajor(problem);
		return JsonUtil.toJson(list, Json.webStrategy);
	}
}
