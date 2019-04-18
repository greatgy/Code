package com.supergenius.web.front.life.mobile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genius.model.base.enums.EStatus;
import com.supergenius.xo.common.constants.MapperDict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.server.life.util.TopicRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.CommentsHP;
import com.supergenius.web.front.life.helper.NewsHP;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 目录controller
 * 
 * @author YangGuang
 * @date 2018年5月14日18:31:35
 */
@Controller
@RequestMapping(value = BaseUriConf.baseMobilePath)
public class CommentsMobile extends BaseController {

	@Autowired
	private ArticleSO so;

	@Autowired
	CatalogueSO catalogueSO;
	
	@Autowired
	private CommentsSO commentsSO;
	
	@Autowired
	private TopicSO topicSO;
	
	@Autowired
	private VideoSO videoSO;

	/**
	 * 添加评论
	 * 
	 * @param fromuid
	 * @param request
	 * @param response
	 * @author yangguang
	 * @date 2017年8月28日12:02:10
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/my/lifecomment/{channel:[a-z]+}/{fromuid:.{32}}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> comment_add(Comments comments, @PathVariable String channel, String isnick, @PathVariable String fromuid, HttpServletRequest request,
			HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (StrUtil.isEmpty(isnick)) {
			comments.setFromUser(user);
			comments.setFromuseruid(user.getUid());
			comments.setFromuseroid(user.getOid());
			comments.setFromusername(user.getUsername());
		} else {// 匿名评论的处理
			Visitor visitor = CommentsHP.getNickVisitor(request, response);
			comments.setFromuseruid(visitor.getUid());
			comments.setFromuseroid(visitor.getOid());
			comments.setFromusername(user.getUsername());
			comments.setFromVisitorAvatar(visitor.getAvatar());
			comments.setFromVisitorName(visitor.getNickname());
		}
		long cid = Long.parseLong(request.getParameter(ViewKeyDict.cid)); 
		String href = request.getParameter(ViewKeyDict.href);
		comments.setCid(cid);
		comments.setFromuid(fromuid);
		comments.setCataloguename(CommentsHP.getFirstCid(cid).getName());
		if (ECatalogue.get(cid) == ECatalogue.test && ViewKeyDict.defaultuid.equals(comments.getTouseruid())) {
			comments.setType(EComment.major);
			comments.setTouid(ViewKeyDict.defaultuid);
			comments.setIsmajor(1);
			Video video = videoSO.get(fromuid);
			video.setState(EState.waitReply);
			videoSO.update(video);
		} else {
			comments.setType(EComment.comment);
		}
		comments.setChannel(ELifeChannel.get(channel));
		comments.setContent(WebUtil.clearXSS(comments.getContent()));
		String topuid = request.getParameter(ViewKeyDict.topuid);
		if (StringUtils.isEmpty(topuid)) {
			topuid = request.getParameter(ViewKeyDict.touid);
		}
		if (StrUtil.isNotEmpty(topuid)) {
			comments.setData(topuid);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommentsHP.add(comments, request)) {
			if (ELifeChannel.get(channel) == ELifeChannel.articlecomments) {
				ArticleRedisUtil.incr(fromuid, ViewKeyDict.commentscount);
				if (StrUtil.isEmpty(comments.getTouid())) {
					Article article = so.get(fromuid);
					href=href + "?commentuid="+comments.getUid();
					NewsHP.sendMsg(comments, article.getUseruid(), article.getTitle(), ELifeMsg.commentsarticle, href);
				}
			} else if (ELifeChannel.get(channel) == ELifeChannel.topiccomments) {
				TopicRedisUtil.incr(fromuid, ViewKeyDict.commentscount);
				if (StrUtil.isEmpty(comments.getTouid())){
					Topic topic	= topicSO.get(fromuid);
					NewsHP.sendMsg(comments, topic.getUseruid(), topic.getTitle(), ELifeMsg.commentstopic, href);
				}
			} else if(ELifeChannel.get(channel) == ELifeChannel.contentcomments){
				ArticleRedisUtil.incr(fromuid, ViewKeyDict.commentscount);
			} else if(ELifeChannel.get(channel) == ELifeChannel.videocomments){
				if (StrUtil.isEmpty(comments.getTouid())){
					Video video	= videoSO.get(fromuid);
					NewsHP.sendMsg(comments, video.getUseruid(), video.getTitle(), ELifeMsg.commentstopic, href);
				}
			}
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				Comments comment = commentsSO.get(topuid);
				NewsHP.sendMsg(comments, comment.getFromuseruid(), comment.getContent(), ELifeMsg.replycomments, href);
			}
			if (ELifeChannel.get(channel) == ELifeChannel.contentcomments || ELifeChannel.get(channel) == ELifeChannel.videocomments) {
				CommentsHP.deleteCommentsFile(comments.getFromuid());
			}
			result.put(ViewKeyDict.success, true);
			result.put(ViewKeyDict.bean, comments);
		}
		return json(result, Json.webStrategy);
	}

	/**
	 * 获取一级评论
	 * 
	 * @param model
	 * @param fromuid文章的uid
	 * @param num当前页数
	 * @author yangguang
	 * @date 2017年8月28日15:58:13
	 */
	@RequestMapping(value = ("/ajax/lifecomment/{channel:[a-z]+}_{cid:\\d+}_{fromuid:.{32}}_{num:\\d+}"), method = RequestMethod.GET)
	public String comments_list(Map<String, Object> model, String commentuid, @PathVariable String channel, @PathVariable String fromuid, @PathVariable Long cid, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		ELifeChannel eChannel = ELifeChannel.get(channel);
		List<Comments> list = new ArrayList<>();
		if (num == null || num <= 0) {
			num = 1;
		}
		User user = BaseUserHP.getCurrUser(request);
		if (eChannel != null) {
			list = commentsSO.getCommentList(EComment.comment, fromuid, SysConf.GetCommentsSize, num);
			if (!commentuid.equals("com") ) {
				Comments comment =commentsSO.get(commentuid);
				if (comment.getTouid() != null){
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.type,EComment.comment);
					map.put(MapperDict.status,EStatus.enable);
					map.put(MapperDict.touid,comment.getTouid());
					comment = commentsSO.getOne(map);
				}
				list.remove(comment);
				list.add(0, comment);
			}
			CommentsHP.organized(list);
			List<String> prizes = new ArrayList<>();
			if (user != null) {
				prizes = CommentsHP.getListPrize(user.getUid());
				for (Comments comments : list) {
					for (String item : prizes) {
						if (comments.getUid().equals(item)) {
							comments.setIsprize(true);
							break;
						}
					}
				}
			} else {// 从cookie中获取
				Visitor visitor = CommentsHP.getVisitor(request, response);
				for (Comments item : list) {
					if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
						item.setIsprize(true);
					}
				}
			}
		}
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.list, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		} else {
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		if (eChannel == ELifeChannel.videocomments || eChannel == ELifeChannel.topiccomments || ECatalogue.get(cid) == ECatalogue.duty) {
			return "majaxvideocomments";
		}
		return "majaxcomments";
	}

	/**
	 * 获取二级评论
	 * 
	 * @param model
	 * @param firstuid一级评论的uid
	 * @param pagenum当前页数
	 * @author yangguang
	 * @date 2017年8月28日17:44:09
	 */
	@RequestMapping(value = ("/ajax/lifecomment/secondComment_{firstuid:.{32}}_{cid:\\d+}_{pagenum:\\d+}"), method = RequestMethod.GET)
	public String second_list(Map<String, Object> model, @PathVariable String firstuid, @PathVariable long cid, @PathVariable Integer pagenum, HttpServletRequest request,
			HttpServletResponse response) {
		if (pagenum == null || pagenum <= 0) {
			pagenum = 1;
		}
		User user = BaseUserHP.getCurrUser(request);
		List<Comments> list = commentsSO.getSecondList(EComment.comment, firstuid, SysConf.GetCommentsSize, pagenum);
		CommentsHP.organized(list);
		List<String> prizes = new ArrayList<>();
		if (user != null) {
			prizes = CommentsHP.getListPrize(user.getUid());
			for (Comments comments : list) {
				for (String item : prizes) {
					if (comments.getUid().equals(item)) {
						comments.setIsprize(true);
						break;
					}
				}
			}
		} else {// 从cookie中获取
			Visitor visitor = CommentsHP.getVisitor(request, response);
			for (Comments item : list) {
				if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
					item.setIsprize(true);
				}
			}
		}
		model.put(ViewKeyDict.firstuid, firstuid);
		model.put(ViewKeyDict.cid, cid);
		Catalogue catalogue = catalogueSO.getOneByCid(cid);
		model.put(ViewKeyDict.pcid, catalogue.getPcid());
		model.put(ViewKeyDict.secondList, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		} else {
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		if (ECatalogue.get(cid) == ECatalogue.test || ECatalogue.get(cid) == ECatalogue.stage || ECatalogue.get(cid) == ECatalogue.duty || ECatalogue.get(cid) == ECatalogue.insight || ECatalogue.get(cid) == ECatalogue.thinking) {
			return "majaxsecondvideocomments";
		}
		return "majaxsecond";
	}

	/**
	 * 删除评论
	 * 
	 * @param model
	 * @param useruid
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ajax/lifecomment/delete/{uid:.{32}}/{fromuseruid:.{32}}/{useruid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean comments_delete(Map<String, Object> model, @PathVariable String uid, @PathVariable String fromuseruid, @PathVariable String useruid, HttpServletRequest request,
			HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			if (!user.getUid().equals(fromuseruid) && !user.getUid().equals(useruid)) {
				return false;
			}
		}
		Comments comments = commentsSO.get(uid);
		CommentsHP.deleteCommentsFile(comments.getFromuid());
		CommentsHP.deleteComments(uid);
		return true;
	}

	/**
	 * 点赞的处理
	 * 
	 * @param model
	 * @param channel
	 * @param oid
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ajax/prize/btnprize_{channel:[a-z]+}_{oid:\\d+}_{cid:\\d+}_{uid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean answer_prize(Map<String, Object> model, @PathVariable String channel, @PathVariable int oid, @PathVariable Long cid, @PathVariable String uid,
			HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		boolean bool = false;
		if (user != null) {
			if (!commentsSO.isNotPrized(uid, user.getUid(), ELifeChannel.get(channel))) {// 取消赞
				CommentsHP.cancelPrize(user.getUid(), uid, channel);
				bool = false;
			} else {// 加赞
				String href = request.getParameter(ViewKeyDict.href);
				Comments comment = new Comments();
				comment.setFromuid(uid);
				comment.setFromuseruid(user.getUid());
				comment.setFromuseroid(user.getOid());
				comment.setFromusername(user.getShowname());
				comment.setContent("");
				comment.setType(EComment.praise);
				comment.setChannel(ELifeChannel.get(channel));
				bool = CommentsHP.add(comment, request);
				if (bool) {
					if (ELifeChannel.get(channel) == ELifeChannel.articlepraise) {
						Article article = so.get(uid);
						if (StrUtil.isNotEmpty(article.getUseruid())) {
							NewsHP.sendMsg(comment, article.getUseruid(), article.getTitle(), ELifeMsg.praisearticle, href);
						}
					} else if (ELifeChannel.get(channel) == ELifeChannel.topicpraise) {
						Topic topic = topicSO.get(uid);
						if (StrUtil.isNotEmpty(topic.getUseruid())) {
							NewsHP.sendMsg(comment, topic.getUseruid(), topic.getTitle(), ELifeMsg.praisetopic, href);
						}
					} else if (ELifeChannel.get(channel) == ELifeChannel.commentspraise) {
						Comments comments = commentsSO.get(uid);
						NewsHP.sendMsg(comment, comments.getFromuseruid(), comments.getContent(), ELifeMsg.praisecomments, href);
					}
				}
			}
		} else {// 非会员
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (!commentsSO.isNotPrized(uid, visitor.getUid(), ELifeChannel.get(channel))) {// 已经点过赞，再次点击取消赞
				CommentsHP.cancelPrize(visitor.getUid(), uid, channel);
				bool = false;
			} else {
				Comments comment = new Comments();
				comment.setFromuid(uid);
				comment.setFromuseruid(visitor.getUid());
				comment.setFromuseroid(0);
				comment.setFromusername(WebConf.DefaultAnonymousName);// WebConf.defaultAnonymousName
				comment.setContent("");
				comment.setType(EComment.praise);
				comment.setChannel(ELifeChannel.get(channel));
				bool = CommentsHP.add(comment, request);
			}
		}
		CommentsHP.deleteCommentsFile(uid);
		return bool;
	}
	
	/**
	 * 按照热度获取静态页面的评论
	 * 
	 * @param model
	 * @param fromuid
	 * @param num
	 * @author yangguang
	 * @date 2018年5月18日16:21:02
	 */
	@RequestMapping(value = ("/ajax/lifecomment/hotcomment"), method = RequestMethod.GET)
	public String comments_list(Map<String, Object> model, int pagenum, String fromuid, Long cid, HttpServletRequest request,
			HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		List<Comments> list = CommentsHP.getCommentsHot(pagenum, SysConf.GetCommentsSize, cid, fromuid);
		CommentsHP.organized(list);
		List<String> prizes = new ArrayList<>();
		if (user != null) { 
			prizes = CommentsHP.getListPrize(user.getUid());
			for (Comments comments : list) {
				for (String item : prizes) {
					if (comments.getUid().equals(item)) {
						comments.setIsprize(true);
						break;
					}
				}
			}
		} else {// 从cookie中获取
			Visitor visitor = CommentsHP.getVisitor(request, response);
			for (Comments item : list) {
				if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
					item.setIsprize(true);
				}
			}
		}
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.list, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		} else {
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		if (ECatalogue.get(cid) == ECatalogue.test || ECatalogue.get(cid) == ECatalogue.stage || ECatalogue.get(cid) == ECatalogue.duty) {
			return "majaxvideocomments";
		}
		return "majaxcomments";
	}
}