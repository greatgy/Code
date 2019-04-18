package com.supergenius.web.admin.life.controller;

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
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.server.life.util.TopicRedisUtil;
import com.supergenius.web.admin.life.helper.LifeCommentsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.rule.LifeSecondCommentCountRule;
import com.supergenius.xo.life.service.CommentsSO;

/**
 * 评论管理controller
 * 
 * @author ChenQi
 * @date 2018年5月11日10:32:38
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeCommentsAdminer extends BaseController {

	@Autowired
	private CommentsSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @author ChenQi
	 * @date 2018年5月11日10:32:41
	 */
	@RequestMapping(value = { "/life/lifecomments" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifecomments.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifecomments, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.allCount, LifeCommentsHP.getUserCount());
		return "dolifecomments";
	}

	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author ChenQi
	 * @date 2018年5月11日10:32:45
	 */
	@RequestMapping(value = { "/life/ajax/lifecomments/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> comments_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeCommentsHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 * @author ChenQi
	 * @date 2018年5月11日10:32:48
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/life/ajax/lifecomments/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comments_delete(String[] ids) {
		Comments comments = so.get(ids[0]);
		LifeCommentsHP.deleteComments(comments.getFromuid());
		if (comments != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.lifecomments.toInt());
			adminLog.setOperation(EAdminLog.deleteLifeComments.getName());
			adminLog.setData(EAdminLog.deleteLifeComments.getName());
			adminLog.setDesc(EAdminLog.deleteLifeComments.getName());
			adminLog.setDataid(ids[0]);
			comments.setStatus(EStatus.deleted);
			so.update(comments);
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				Rule rule = new LifeSecondCommentCountRule(comments.getData());
				RedisUtil.decr(rule);
				if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
					ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
				} else if (comments.getChannel() == ELifeChannel.topiccomments) {//话题的评论
					TopicRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
				}
			} else {
				if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
					}
				} else if (comments.getChannel() == ELifeChannel.topiccomments) {//帖子的评论
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						TopicRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
					}
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
	 * @data 2018年5月11日10:32:48
	 */
	@RequestMapping(value = "/life/ajax/lifecomments/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid, @PathVariable int status) {
		if (StrUtil.isNotEmpty(uid)) {
			Comments comments = so.get(uid);
			LifeCommentsHP.deleteComments(comments.getFromuid());
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isEmpty(comments.getTouid())) {
				if (EStatus.get(status) == EStatus.enable) {
					if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					} else if (comments.getChannel() == ELifeChannel.topiccomments) {//帖子的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							TopicRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					}
					comments.setStatus(EStatus.enable);
				} else {
					if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					} else if (comments.getChannel() == ELifeChannel.topiccomments) {//帖子的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							TopicRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					}
					comments.setStatus(EStatus.disable);
				}
			} else {
				Comments firstComments = so.get(comments.getTouid());
				if (firstComments != null) {
					if (firstComments.getStatus() == EStatus.enable) {
						Rule rule = new LifeSecondCommentCountRule(comments.getData());
						if (EStatus.get(status) == EStatus.enable) {
							comments.setStatus(EStatus.enable);
							if (RedisUtil.decr(rule) >= 0) {
								if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
									ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
								} else if (comments.getChannel() == ELifeChannel.topiccomments) {//帖子的评论
									TopicRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
								}
							}
						} else {
							comments.setStatus(EStatus.disable);
							if (RedisUtil.incr(rule) >= 0) {
								if (comments.getChannel() == ELifeChannel.articlecomments) {// 文章的评论
									ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
								} else if (comments.getChannel() == ELifeChannel.topiccomments) {//帖子的评论
									TopicRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
								}
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
