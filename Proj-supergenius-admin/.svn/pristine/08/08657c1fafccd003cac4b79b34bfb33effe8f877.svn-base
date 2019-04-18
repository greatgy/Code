package com.supergenius.web.admin.moralnews.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict; 
import com.supergenius.server.moralnews.util.ArticleRedisUtil;
import com.supergenius.web.admin.moralnews.helper.CommentsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.moralnews.entity.Comments;
import com.supergenius.xo.moralnews.enums.EComment;
import com.supergenius.xo.moralnews.rule.MoralnewsSecondCommentCountRule;
import com.supergenius.xo.moralnews.service.CollectSO;
import com.supergenius.xo.moralnews.service.CommentsSO;

/**
 * 评论管理controller
 * 
 * @author ChenQi
 * @date 2017年11月14日12:27:35
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralNewsCommentsAdminer extends BaseController {

	@Autowired
	private CommentsSO so;

	@Autowired
	private CollectSO collectSO;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/moralnews/moralnewscomments" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.moralnewscomments.name());
		model.put(ViewKeyDict.site, ESite.moralnews.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.moralnewscomments, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.collectCount, collectSO.getCount());
		model.put(ViewKeyDict.allCount, CommentsHP.getUserCount());
		return "domoralnewscomments";
	}

	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/moralnews/ajax/moralnewscomments/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> comments_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CommentsHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/moralnews/ajax/moralnewscomments/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comments_delete(String[] ids) {
		Comments comments = so.get(ids[0]);
		if (comments != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.moralnewscomments.toInt());
			adminLog.setOperation(EAdminLog.deleteMoralnewsComments.getName());
			adminLog.setData(EAdminLog.deleteMoralnewsComments.getName());
			adminLog.setDesc(EAdminLog.deleteMoralnewsComments.getName());
			adminLog.setDataid(ids[0]);
			comments.setStatus(EStatus.deleted);
			so.update(comments);
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				Rule rule = new MoralnewsSecondCommentCountRule(comments.getData());
				RedisUtil.decr(rule);
				ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
			} else {
				for (int i = 0; i < comments.getCommentcount() + 1; i++) {
					ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
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
	@RequestMapping(value = "/moralnews/ajax/moralnewscomments/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid, @PathVariable int status) {   //    0
		if (StrUtil.isNotEmpty(uid)) {
			Comments comments = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isEmpty(comments.getTouid())) {         //1ji

				if (EStatus.get(status) == EStatus.enable) {
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
					}
					comments.setStatus(EStatus.enable);
				} else {
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
					}
					comments.setStatus(EStatus.disable);
				}
			} else {
				Comments firstComments = so.get(comments.getTouid());
				if (firstComments != null) {
					if (firstComments.getStatus() == EStatus.enable) {
						Rule rule = new MoralnewsSecondCommentCountRule(comments.getData());
						if (EStatus.get(status) == EStatus.enable) {
							comments.setStatus(EStatus.enable);
							if (RedisUtil.decr(rule) >= 0) {
								ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
							}
						} else {
							comments.setStatus(EStatus.disable);
							if (RedisUtil.incr(rule) >= 0) {
								ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
							}
						}
					}
				}
			}
			comments.setAdminuid(adminUid);
			if (so.update(comments)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);

	}
}
