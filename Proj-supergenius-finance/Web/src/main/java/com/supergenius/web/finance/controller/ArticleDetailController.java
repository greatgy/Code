package com.supergenius.web.finance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.ArticleHP;
import com.supergenius.web.finance.helper.CollectHP;
import com.supergenius.web.finance.helper.CommentsHP;
import com.supergenius.web.finance.helper.IndexHP;
import com.supergenius.web.finance.helper.MycenterHP;
import com.supergenius.web.finance.helper.NewsHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Catalogue;
import com.supergenius.xo.finance.entity.Comments;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CatalogueSO;
import com.supergenius.xo.finance.service.CommentsSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 首页controller
 * 
 * @author: ChenQi
 * @date 2017年11月14日10:24:34
 */
@Controller
public class ArticleDetailController extends BaseController {

	@Autowired
	private ArticleSO articleSO;

	@Autowired
	private CommentsSO so;

	@Autowired
	private VisitorSO visitorso;

	@Autowired
	private CatalogueSO catalogueSO;

	/**
	 * 文章查看详情页面
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年1月3日11:43:31
	 */
	@RequestMapping(value = "/article/{cid:\\d+}/{oid:\\d+}", method = RequestMethod.GET)
	public String article_detial(Map<String, Object> model, @PathVariable int cid, @PathVariable int oid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		Article article = ArticleHP.getArticle(oid);
		IndexHP.setSummary(article);
		Catalogue catalogue = catalogueSO.get(cid);
		if (catalogue == null || article == null) {
			return response404(response);
		}
		if (user != null) {// 设置是否赞过
			if (CommentsHP.isPrise(article.getUid(), user.getUid())) {
				article.setIsprize(true);
			}
		} else {
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (CommentsHP.isPrise(article.getUid(), visitor.getUid())) {
				article.setIsprize(true);
			}
		}
		if (user != null) {// 是否收藏过
			article.setIscollect(CollectHP.isCollect(user.getUid(), article.getUid()));
		}
		ArticleHP.incrClickCount(request, response, article);// 增加计数
		article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
		article.setCollectcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.collectcount));
		article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
		article.setCommentscount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.commentscount));
		User author = BaseUserHP.get(article.getAuthoruid());
		if (author != null) {
			if (user != null) {// 是否订阅过
				Subscribe subscribe = MycenterHP.getSubscribe(user.getUid(), author.getUid());
				if (subscribe != null) {
					model.put(ViewKeyDict.isSubscribe, true);
				} else {
					model.put(ViewKeyDict.isSubscribe, false);
				}
			}
			model.put(ViewKeyDict.count, ArticleHP.getHisCount(author.getUid()));
			model.put(ViewKeyDict.hisArticle, ArticleHP.getHisArticle(author.getUid(), 0, 3, request));
			model.put(ViewKeyDict.author, author);
		}
		//Pager pager = Pager.getNewInstance(0, SysConf.RelatedArticleSize);
		//List<Article> articleList = articleSO.getRelatecarticleList(pager, article.getAuthoruid());
		List<Article> recommendList = ArticleHP.getRecommendArticle(article.getUid());
		if (recommendList.size() > 0) {
			model.put(ViewKeyDict.RelatecarticleList, recommendList);
		} else {
			model.put(ViewKeyDict.HotArticle, IndexHP.getHotArticle(SysConf.RelatedArticleSize, article.getCid()));// 获得热门文章
		}
		model.put(ViewKeyDict.HotLabel, IndexHP.getHotLabel(SysConf.HotLabelSize));
		model.put(ViewKeyDict.bean, article);
		model.put(ViewKeyDict.catalogue, catalogue);
		model.put(ViewKeyDict.cid, cid);
		return "articledetail";
	}

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
	@RequestMapping(value = { "/ajax/my/comment/{fromuid:.{32}}" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> comment_add(Comments comments, String isnick, @PathVariable String fromuid, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("jinlail");
		User user = BaseUserHP.getCurrUser(request);
		comments.setFromuid(fromuid);
		int cid = Integer.parseInt(request.getParameter(ViewKeyDict.cid));
		comments.setCid(cid);
		comments.setCataloguename(catalogueSO.get(cid).getName());
		Visitor visitor = null;
		if (StrUtil.isEmpty(isnick)) {
			comments.setFromUser(user);
			comments.setFromuseruid(user.getUid());
			comments.setFromuseroid(user.getOid());
			comments.setFromusername(user.getUsername());
		} else {
			visitor = CommentsHP.getNickVisitor(request, response);
			comments.setFromuseruid(visitor.getUid());
			comments.setFromuseroid(visitor.getOid());
			comments.setFromusername(visitor.getUsername());
			comments.setFromVisitorAvatar(visitor.getAvatar());
			comments.setFromVisitorName(visitor.getNickname());
		}
		if (StrUtil.isNotEmpty(comments.getTouseruid())) {
			User toUser = BaseUserHP.get(comments.getTouseruid());
			if (toUser != null) {
				comments.setTousername(toUser.getUsername());
				comments.setTouseroid(toUser.getOid());
			} else {
				Visitor tovisitor = visitorso.get(comments.getTouseruid());
				if (tovisitor != null) {
					comments.setTousername(tovisitor.getNickname());
				}
				comments.setTouseroid(tovisitor.getOid());
			}
		}
		comments.setType(EComment.comment);
		comments.setChannel(EChannel.finance);
		comments.setContent(WebUtil.clearXSS(comments.getContent()));
		String topuid = request.getParameter(ViewKeyDict.topuid);
		if (StringUtils.isEmpty(topuid)) {
			topuid = request.getParameter(ViewKeyDict.touid);
		}
		if (StrUtil.isNotEmpty(topuid)) {
			comments.setData(topuid);
		}
		if (StrUtil.isEmpty(comments.getTouid())) {
			comments.setTouid(null);
		}
		boolean addresult = CommentsHP.add(comments, request);
		Map<String, Object> result = new HashMap<String, Object>();
		if (addresult) {
			ArticleRedisUtil.incr(fromuid, ViewKeyDict.commentscount);
			if (StrUtil.isNotEmpty(comments.getTouid())) {
				NewsHP.sendMsg(comments, cid, EFinanceMsg.replycomments);
			} else {
				NewsHP.sendMsg(comments, cid, EFinanceMsg.commentsarticle);
			}
		}
		result.put(ViewKeyDict.success, addresult);
		result.put(ViewKeyDict.bean, comments);
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
	@RequestMapping(value = ("/ajax/comment/{channel:[a-z]+}_{fromuid:.{32}}_{num:\\d+}"), method = RequestMethod.GET)
	public String comments_list(Map<String, Object> model, @PathVariable String channel, @PathVariable String fromuid, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		EChannel eChannel = EChannel.get(channel);
		List<Comments> list = new ArrayList<>();
		if (num == null || num <= 0) {
			num = 1;
		}
		if (eChannel != null) {
			User user = BaseUserHP.getCurrUser(request);
			list = so.getCommentList(EComment.comment, fromuid, SysConf.GetCommentsSize, num);
			CommentsHP.organized(list);
			List<String> prizes = new ArrayList<>();
			if (user != null) {
				prizes = CommentsHP.getListPrize(user);
				for (Comments comments : list) {
					for (String item : prizes) {
						if (comments.getUid().equals(item)) {
							comments.setIsprize(true);
							break;
						}
					}
				}
			} else {// 从cookie中获取
				Visitor visitor;
				for (Comments item : list) {
					visitor = CommentsHP.getVisitor(request, response);
					if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
						item.setIsprize(true);
					}
				}
			}
		}
		Article article = articleSO.get(fromuid);
		model.put(ViewKeyDict.cid, article.getCid());
		model.put(ViewKeyDict.channel, channel);
		model.put(ViewKeyDict.list, list);
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		} else {
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		return "ajaxcomments";
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
	@RequestMapping(value = ("/ajax/comment/secondComment_{firstuid:.{32}}_{cid:\\d+}_{pagenum:\\d+}"), method = RequestMethod.GET)
	public String second_list(Map<String, Object> model, @PathVariable String firstuid, @PathVariable int cid, @PathVariable Integer pagenum, HttpServletRequest request,
			HttpServletResponse response) {
		List<Comments> list = new ArrayList<>();
		if (pagenum == null || pagenum <= 0) {
			pagenum = 1;
		}
		User user = BaseUserHP.getCurrUser(request);
		list = so.getSecondList(EComment.comment, firstuid, SysConf.GetCommentsSize, pagenum);
		CommentsHP.organized(list);
		List<String> prizes = new ArrayList<>();
		if (user != null) {
			prizes = CommentsHP.getListPrize(user);
			for (Comments comments : list) {
				for (String item : prizes) {
					if (comments.getUid().equals(item)) {
						comments.setIsprize(true);
						break;
					}
				}
			}
		} else {// 从cookie中获取
			for (Comments item : list) {
				Visitor visitor = CommentsHP.getVisitor(request, response);
				if (CommentsHP.isPrise(item.getUid(), visitor.getUid())) {
					item.setIsprize(true);
				}
			}
		}
		model.put(ViewKeyDict.firstuid, firstuid);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.secondList, list);
		if (user != null) {
			model.put(ViewKeyDict.useruid, user.getUid());
		} else {
			model.put(ViewKeyDict.useruid, CookieUtil.get(request, ViewKeyDict.visitors));
		}
		return "ajaxsecond";
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
	@RequestMapping(value = "/ajax/comments/delete/{uid:.{32}}/{fromuseruid:.{32}}/{useruid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean comments_delete(Map<String, Object> model, @PathVariable String uid, @PathVariable String fromuseruid, @PathVariable String useruid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			if (!user.getUid().equals(fromuseruid) && !user.getUid().equals(useruid)) {
				return false;
			}
		} else {
			String visitorUid = CookieUtil.get(request, ViewKeyDict.visitors);
			if (StrUtil.isNotEmpty(visitorUid) && !visitorUid.equals(fromuseruid)) {
				return false;
			}
		}
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
	public @ResponseBody boolean answer_prize(Map<String, Object> model, @PathVariable String channel, @PathVariable int oid, @PathVariable int cid, @PathVariable String uid,
			HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		boolean bool = false;
		if (user != null) {
			if (!so.isNotPrized(uid, user.getUid(), EChannel.get(channel))) {// 取消赞
				CommentsHP.cancelPrize(user.getUid(), uid, channel);
				bool = false;
			} else {// 加赞
				Comments comment = new Comments();
				comment.setFromuid(uid);
				comment.setFromuseruid(user.getUid());
				comment.setFromuseroid(user.getOid());
				comment.setFromusername(user.getShowname());
				comment.setContent("");
				comment.setType(EComment.praise);
				comment.setChannel(EChannel.get(channel));
				bool = CommentsHP.add(comment, request);
				if (bool) {
					if (EChannel.get(channel) == EChannel.finance) {
						NewsHP.sendMsg(comment, cid, EFinanceMsg.praisearticle);
					} else {
						NewsHP.sendMsg(comment, cid, EFinanceMsg.praisecomments);
					}
				}
			}
		} else {// 非会员
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (!so.isNotPrized(uid, visitor.getUid(), EChannel.get(channel))) {// 已经点过赞，再次点击取消赞
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
				comment.setChannel(EChannel.get(channel));
				bool = CommentsHP.add(comment, request);
			}
		}
		return bool;
	}
}