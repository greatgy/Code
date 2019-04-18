package com.supergenius.web.admin.finance.controller;

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
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.web.admin.finance.helper.CommentsHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Comments;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.rule.FinanceSecondCommentCountRule;
import com.supergenius.xo.finance.service.CollectSO;
import com.supergenius.xo.finance.service.CommentsSO;

/**
 * 评论管理controller
 * 
 * @author ChenQi
 * @date 2017年11月14日12:27:35
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class FinanceCommentsAdminer extends BaseController {

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
	@RequestMapping(value = { "/finance/financecomments" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.financecomments.name());
		model.put(ViewKeyDict.site, ESite.finance.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.financecomments, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.collectCount, collectSO.getCount());
		model.put(ViewKeyDict.allCount, CommentsHP.getUserCount());
		return "dofinancecomments";
	}

	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/finance/ajax/financecomments/list" }, method = RequestMethod.GET)
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
	@RequestMapping(value = "/finance/ajax/financecomments/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> comments_delete(String[] ids) {
		Comments comments = so.get(ids[0]);
		if (comments != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.financecomments.toInt());
			adminLog.setOperation(EAdminLog.deleteFinanceComments.getName());
			adminLog.setData(EAdminLog.deleteFinanceComments.getName());
			adminLog.setDesc(EAdminLog.deleteFinanceComments.getName());
			adminLog.setDataid(ids[0]);
			comments.setStatus(EStatus.deleted);
			so.update(comments);
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				Rule rule = new FinanceSecondCommentCountRule(comments.getData());
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
	@RequestMapping(value = "/finance/ajax/financecomments/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid, @PathVariable int status) {
		System.out.println("1111111");
		if (StrUtil.isNotEmpty(uid)) {
			Comments comments = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isEmpty(comments.getTouid())) {
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
						Rule rule = new FinanceSecondCommentCountRule(comments.getData());
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