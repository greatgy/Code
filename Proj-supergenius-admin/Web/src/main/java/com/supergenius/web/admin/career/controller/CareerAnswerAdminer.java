package com.supergenius.web.admin.career.controller;

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
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.core.rule.CareerAnswerSecondCommentCountRule;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.career.util.ArticleRedisUtil;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.AnswerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Answer;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.career.service.AnswerSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 鬼话、吐槽管理controller
 * 
 * @author ChenQi
 * @date 2017年11月14日12:27:35
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerAnswerAdminer extends BaseController {

	@Autowired
	private AnswerSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/careerpuzzledanswer" }, method = RequestMethod.GET)
	public String tease(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerpuzzledanswer.name());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerpuzzledanswer, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.allCount, String.valueOf(so.getCountByfromuseruid()));
		return "docareerpuzzledanswer";
	}

	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/ajax/careerpuzzledanswer/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tease_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AnswerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledanswer/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tease_delete(String[] ids) {
		Answer answer = so.get(ids[0]);
		if (answer != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careerpuzzledanswer.toInt());
			adminLog.setOperation(EAdminLog.deleteCareerAnswer.getName());
			adminLog.setData(EAdminLog.deleteCareerAnswer.getName());
			adminLog.setDesc(EAdminLog.deleteCareerAnswer.getName());
			adminLog.setDataid(ids[0]);
			answer.setStatus(EStatus.deleted);
			so.update(answer);
			if (StrUtil.isNotEmpty(answer.getTouid())) {
				Rule rule = new CareerAnswerSecondCommentCountRule(answer.getData());
				RedisUtil.decr(rule);
				ArticleRedisUtil.decr(answer.getFromuid(), ViewKeyDict.commentscount);
			} else {
				for (int i = 0; i < answer.getCommentcount() + 1; i++) {
					ArticleRedisUtil.decr(answer.getFromuid(), ViewKeyDict.commentscount);
				}
			}
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author ChenQi
	 * @param Map<String,Object>
	 * @data 2017年11月14日12:27:12
	 */
	@RequestMapping(value = "/career/ajax/careerpuzzledanswer/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTease(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			Answer answer = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isEmpty(answer.getTouid())) {
				if (answer.getStatus() == EStatus.enable) {
					for (int i = 0; i < answer.getCommentcount() + 1; i++) {
						ArticleRedisUtil.decr(answer.getFromuid(), ViewKeyDict.commentscount);
					}
					answer.setStatus(EStatus.disable);
				} else {
					for (int i = 0; i < answer.getCommentcount() + 1; i++) {
						ArticleRedisUtil.incr(answer.getFromuid(), ViewKeyDict.commentscount);
					}
					answer.setStatus(EStatus.enable);
				}
			} else {
				Answer firstAnswer = so.get(answer.getTouid());
				if (firstAnswer != null) {
					if (firstAnswer.getStatus() == EStatus.enable) {
						Rule rule = new CareerAnswerSecondCommentCountRule(answer.getData());
						if (answer.getStatus() == EStatus.enable) {
							if (RedisUtil.decr(rule) >= 0) {
								ArticleRedisUtil.decr(answer.getFromuid(), ViewKeyDict.commentscount);
							}
							answer.setStatus(EStatus.disable);
						} else {
							if (RedisUtil.incr(rule) >= 0) {
								ArticleRedisUtil.incr(answer.getFromuid(), ViewKeyDict.commentscount);
							}
							answer.setStatus(EStatus.enable);
						}
					}
				}
			}
			answer.setAdminuid(adminUid);
			if (so.update(answer)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);

	}
}
