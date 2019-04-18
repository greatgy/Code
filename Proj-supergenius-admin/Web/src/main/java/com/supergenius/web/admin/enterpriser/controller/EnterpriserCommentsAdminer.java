package com.supergenius.web.admin.enterpriser.controller;

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
import com.supergenius.server.enterpriser.util.ArticleRedisUtil;
import com.supergenius.server.enterpriser.util.ForumRedisUtil;
import com.supergenius.web.admin.enterpriser.hellper.CommentsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Comments;
import com.supergenius.xo.enterpriser.enums.EComment;
import com.supergenius.xo.enterpriser.rule.EnterpriserSecondCommentCountRule;
import com.supergenius.xo.enterpriser.service.CommentsSO;

/**
 * 评论管理controller
 * 
 * @author loupengyu
 * @date 2018年1月30日16:50:01
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserCommentsAdminer extends BaseController {

	@Autowired
	private CommentsSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @author LouPengYu
	 * @date 2018年2月1日上午10:52:54
	 */
	@RequestMapping(value = { "/enterpriser/enterprisercomments" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterprisercomments.name());
		model.put(ViewKeyDict.site, ESite.enterpriser.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.enterprisercomments, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.allCount, CommentsHP.getUserCount());
		return "doenterprisercomments";
	}

	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author LouPengYu
	 * @date 2018年2月1日上午11:02:07
	 */
	@RequestMapping(value = { "/enterpriser/ajax/enterprisercomments/list" }, method = RequestMethod.GET)
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
	 * @author LouPengYu
	 * @date 2018年2月1日上午11:02:31
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercomments/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comments_delete(String[] ids) {
		Comments comments = so.get(ids[0]);
		if (comments != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.enterprisercomments.toInt());
			adminLog.setOperation(EAdminLog.deleteEnterpriserComments.getName());
			adminLog.setData(EAdminLog.deleteEnterpriserComments.getName());
			adminLog.setDesc(EAdminLog.deleteEnterpriserComments.getName());
			adminLog.setDataid(ids[0]);
			comments.setStatus(EStatus.deleted);
			so.update(comments);
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				Rule rule = new EnterpriserSecondCommentCountRule(comments.getData());
				RedisUtil.decr(rule);
				if (comments.getChannel() == EChannel.finance) {// 文章的评论
					ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
				} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
					ForumRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
				}
			} else {
				if (comments.getChannel() == EChannel.finance) {// 文章的评论
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
					}
				} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
					for (int i = 0; i < comments.getCommentcount() + 1; i++) {
						ForumRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
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
	 * @author loupengyu
	 * @param Map<String,Object>
	 * @data 2018年1月30日16:48:26
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercomments/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid, @PathVariable int status) {
		if (StrUtil.isNotEmpty(uid)) {
			Comments comments = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isEmpty(comments.getTouid())) {
				if (EStatus.get(status) == EStatus.enable) {
					if (comments.getChannel() == EChannel.finance) {// 文章的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ForumRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					}
					comments.setStatus(EStatus.enable);
				} else {
					if (comments.getChannel() == EChannel.finance) {// 文章的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
						for (int i = 0; i < comments.getCommentcount() + 1; i++) {
							ForumRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
						}
					}
					comments.setStatus(EStatus.disable);
				}
			} else {
				Comments firstComments = so.get(comments.getTouid());
				if (firstComments != null) {
					if (firstComments.getStatus() == EStatus.enable) {
						Rule rule = new EnterpriserSecondCommentCountRule(comments.getData());
						if (EStatus.get(status) == EStatus.enable) {
							comments.setStatus(EStatus.enable);
							if (RedisUtil.decr(rule) >= 0) {
								if (comments.getChannel() == EChannel.finance) {// 文章的评论
									ArticleRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
								} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
									ForumRedisUtil.decr(comments.getFromuid(), ViewKeyDict.commentscount);
								}
							}
						} else {
							comments.setStatus(EStatus.disable);
							if (RedisUtil.incr(rule) >= 0) {
								if (comments.getChannel() == EChannel.finance) {// 文章的评论
									ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
								} else if (comments.getChannel() == EChannel.enterpriserforum) {//帖子的评论
									ForumRedisUtil.incr(comments.getFromuid(), ViewKeyDict.commentscount);
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