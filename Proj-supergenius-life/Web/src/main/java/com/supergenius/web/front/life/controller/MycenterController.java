package com.supergenius.web.front.life.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supergenius.web.front.life.helper.*;
import com.supergenius.xo.life.entity.*;
import com.supergenius.xo.life.enums.ELifeChannel;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 进入和个人相关的页面：个人资料，消息通知，我的收藏，我的文章,我的提问，我的视频，我的话题
 * 
 * @author YangGuang
 * @date 2018年5月16日15:21:06
 *
 */
@Controller
public class MycenterController extends BaseController {

	@Autowired
	private UserSO userSO;

	@Autowired
	private UserInfoSO userInfoSO;

	@Autowired
	private ArticleSO articleSO;

	/**
	 * 进入我的个人中心
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/my/center/{channel:[a-z]+}" }, method = RequestMethod.GET)
	public String center(Map<String, Object> model, @PathVariable String channel, String phone, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		//我的文章
		if (EChannel.article.name().equals(channel)) {
			Map<String, Object> map = MycenterHP.getArticles(user.getUid(), 1, SysConf.MyArticleSize);
			model.put(ViewKeyDict.article, map);
			model.put(ViewKeyDict.channel, EChannel.article.name());
		//我的消息
		} else if (EChannel.msg.name().equals(channel)) {
			List<News> newslist = MycenterHP.getMsgs(user.getUid(), 1, SysConf.MyMsgPageSize);
			model.put(ViewKeyDict.msg, newslist);
			model.put(ViewKeyDict.channel, EChannel.msg.name());
		//我的收藏
		} else if (EChannel.collect.name().equals(channel)) {
			List<Article> collectList = MycenterHP.getCollects(user.getUid(), 1, SysConf.MyCollectPageSize);
			model.put(ViewKeyDict.collect, collectList);
			model.put(ViewKeyDict.channel, EChannel.collect.name());
		//个人资料
		} else if (EChannel.info.name().equals(channel)) {
			model.put(ViewKeyDict.user, user);
			model.put(ViewKeyDict.channel, EChannel.info.name());
		//我的话题        主要获取已发布的话题
		} else if (EChannel.topic.name().equals(channel)) {
			List<Topic> collectList = MycenterHP.getTopics(user.getUid(), 1, SysConf.TopicLoadSize);
			model.put(ViewKeyDict.topic, collectList);
			model.put(ViewKeyDict.channel, EChannel.topic.name());
		//我的视频
		} else if (EChannel.video.name().equals(channel)) {
			Map<String, Object> videos = MycenterHP.getVideos(user.getUid(), 1, SysConf.MyVideoSize);
			model.put(ViewKeyDict.video, videos);
			model.put(ViewKeyDict.channel, EChannel.video.name());
		//我的提问
		} else if (EChannel.question.name().equals(channel)) {
			List<Problem> problem = MycenterHP.getProblems(user.getUid(), 1, SysConf.MyProblemSize);
			model.put(ViewKeyDict.problem, problem);
			model.put(ViewKeyDict.channel, EChannel.question.name());
			//我的评论
		} else if (EChannel.comment.name().equals(channel)){
			List<Comments> commentsList = MycenterHP.getComments(user.getUid(), 1, SysConf.MyCommentSize);
			for(Comments comments:commentsList){
				if (comments.getChannel()== ELifeChannel.articlecomments) {
					Article article = ArticleHP.getArticleOne(comments.getFromuid());
					comments.setTitle(article.getTitle());
					comments.setArticleoid(article.getOid());
				}else if(comments.getChannel()==ELifeChannel.topiccomments){
					Topic topic = TopicHP.getOneTopic(comments.getFromuid());
					comments.setTitle(topic.getTitle());
					comments.setTopicoid(topic.getOid());
				}else if(comments.getChannel()==ELifeChannel.videocomments){
					Video video = VideoHP.getOneVideo(comments.getFromuid());
					comments.setTitle(video.getTitle());
				}else if(comments.getChannel()==ELifeChannel.contentcomments){
					Content content = ContentHP.getOneContent(comments.getFromuid());
					comments.setTitle(content.getName());
				}
			}
			model.put(ViewKeyDict.commentscount,CommentsHP.getcommentComment(user.getUid()));
			model.put(ViewKeyDict.commentsList, commentsList);
			model.put(ViewKeyDict.channel, EChannel.comment.name());
		}
		return "mycenter";
	}

	/**
	 * 我的文章加载更多
	 * 
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/ajax/my/article/{channel:[a-z]+}", method = RequestMethod.GET)
	public String ajax_article(Map<String, Object> model, int pagenum, @PathVariable String channel, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Article> list = MycenterHP.getMoreArticles(user.getUid(), pagenum, SysConf.MyArticleSize, channel);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}

	/**
	 * 加载消息
	 * 
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 * @dete 2018年5月16日15:48:13
	 */
	@RequestMapping(value = "/ajax/my/msg", method = RequestMethod.GET)
	public String ajax_msg(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<News> msgs = MycenterHP.getMsgs(BaseUserHP.getCurrUser(request).getUid(), pagenum, SysConf.MyMsgPageSize);
		model.put(ViewKeyDict.msg, msgs);
		return "ajaxmsg";
	}
	/**
	 * 加载我的评论
	 *
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 * @dete 2018年5月16日15:48:13
	 */
	@RequestMapping(value = "ajax/my/comments", method = RequestMethod.GET)
	public String ajax_comment(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Comments> commentsList = MycenterHP.getCommentList(BaseUserHP.getCurrUser(request).getUid(), pagenum, SysConf.MyCommentSize);
		for(Comments comments:commentsList){
			Article article = ArticleHP.getArticleOne(comments.getFromuid());
			comments.setTitle(article.getTitle());
			comments.setArticleoid(article.getOid());
		}
		model.put(ViewKeyDict.commentsList, commentsList);
		return "ajaxcomment";
	}

	/**
	 * 删除消息
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/ajax/my/msg/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteInbox(String uid) {
		return MycenterHP.deleteMsg(uid);
	}

	/**
	 * 清空消息框
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/ajax/my/msg/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteAllInbox(HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		return MycenterHP.deleteAllMsg(user.getUid());
	}

	/**
	 * 加载我的视频
	 * 
	 * @param model
	 * @param pagenum
	 * @param type
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/mycenter/video/{channel}", method = RequestMethod.GET)
	public String ajax_video(Map<String, Object> model, int pagenum, @PathVariable String channel, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		System.err.println(channel);
		long cid = 0;
		if(channel=="stagelist") {
			cid = Long.parseLong("2147483648");  
		}else if(channel=="testlist"){
			cid = 32768;
		}
		List<Video> videos = MycenterHP.getMoreVideos(user.getUid(), pagenum, SysConf.MyVideoSize, ECatalogue.get(cid));
		model.put(ViewKeyDict.list, videos);
		return "ajaxvideo";
	}
	
	/**
	 * 我的收藏加载更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/collect", method = RequestMethod.GET)
	public String ajax_collect(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Article> collects = MycenterHP.getCollects(user.getUid(), pagenum, SysConf.MyCollectPageSize);
		model.put(ViewKeyDict.list, collects);
		return "ajaxarticle";
	}
	
	/**
	 * 我的提问加载更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/mycenter/problem", method = RequestMethod.GET)
	public String ajax_problem(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Problem> problems = MycenterHP.getProblems(user.getUid(), pagenum, SysConf.MyProblemSize);
		model.put(ViewKeyDict.list, problems);
		return "ajaxmyproblem";
	}
	
	/**
	 * 我的话题加载更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/mycenter/topic", method = RequestMethod.GET)
	public String ajax_topic(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Topic> topics = MycenterHP.getTopics(user.getUid(), pagenum, SysConf.TopicLoadSize);
		model.put(ViewKeyDict.list, topics);
		return "ajaxmytopic";
	}
	/**
	 * 我参与的话题加载更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/mycenter/topic/join", method = RequestMethod.GET)
	public String ajax_topic1(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Topic> topics = MycenterHP.getJoinTopics(user.getUid(), pagenum, SysConf.TopicLoadSize);
		model.put(ViewKeyDict.list, topics);
		return "ajaxmytopic";
	}
	
	/**
	 * @author 雍雪振
	 * @time 2018年5月22日下午4:47:44
	 * @description: 获取我参与的话题
	 */
	@RequestMapping(value = "/ajax/my/mycenter/joinTopic",method = RequestMethod.GET)
	public String jointopic(Map<String, Object> model, String uid, HttpServletRequest request) {
		List<Topic> joinTopic = MycenterHP.getJoinTopics(uid, 1, SysConf.TopicLoadSize);
		model.put(ViewKeyDict.list, joinTopic);
		model.put(ViewKeyDict.channel, EChannel.topic.name());
		return "ajaxmytopic";
	}
	
	/**
	 * 我参与的话题 更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/my/mycenter/joinTopicMore", method = RequestMethod.GET)
	public String moretopic(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		List<Topic> topics = MycenterHP.getJoinTopics(user.getUid(), pagenum, SysConf.TopicLoadSize);
		model.put(ViewKeyDict.list, topics);
		return "ajaxmytopic";
	}


	/**
	 * 阅读消息
	 * 
	 * @param uid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:35:00
	 */
	@RequestMapping(value = "/ajax/my/msg/read", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_msgRead(String uid) {
		if (MycenterHP.readMsg(uid)) {
			return "success";
		}
		return "false";
	}

	/**
	 * 获取未读消息数量
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:34:11
	 */
	@RequestMapping(value = "/ajax/my/unreads", method = RequestMethod.GET)
	@ResponseBody
	public int ajax_unRead(HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		return MycenterHP.unreadMsg(user.getUid());
	}

	/**
	 * 编辑个人信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/center/edit", method = RequestMethod.POST)
	public String editInfo(Map<String, Object> model, @RequestParam MultipartFile original, String showname, String birthday, String gender, String summary, HttpServletRequest request) {
		User oldUser = BaseUserHP.getCurrUser(request);
		Map<String, Object> map = new HashMap<>();
		if (oldUser != null) {
			if (original.getSize() != 0) {
				String[] strs = FileUtil.resizeImage(original, SysConf.ImgUserDataDir_Slash + oldUser.getOid() + ViewKeyDict.slash + ViewKeyDict.avatar, 700, 900);
				String[] paths = FileUtil.resizeImage(strs[0], SysConf.AvatarSizes);
				if (strs != null && paths != null && paths.length >= 2) {
					oldUser.setOriginal(strs[0]);
					oldUser.setAvatarbig(paths[0]);
					oldUser.setAvatar(paths[1]);
					oldUser.setAvatarlittle(paths[2]);
				}
			}
			oldUser.setShowname(showname);
			if (StringUtils.isNotEmpty(birthday)) {
				new DateTime();
				DateTime dateTime = DateTime.parse(birthday);
				oldUser.setBirthday(dateTime);
			}
			if (gender.equals("1")) {
				oldUser.setGender(EGender.gentleman);
			} else {
				oldUser.setGender(EGender.lady);
			}
			oldUser.setSummary(summary);
			boolean result1 = userInfoSO.update(oldUser.getUserInfo());
			boolean result = userSO.update(oldUser);
			if (result && result1) {
				map.put(ViewKeyDict.success, result);
				BaseUserHP.freshSessUser(request, oldUser);
			}
		}
		model.put(ViewKeyDict.channel, EChannel.info.name());
		return redirectPrefix + "/my/center/info";
	}

	/**
	 * 点击草稿箱
	 * 
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/life/my/article/{articleuid:.{32}}", method = RequestMethod.GET)
	public String draft_detail(Map<String, Object> model, @PathVariable String articleuid, HttpServletRequest request) {
		model.put(ViewKeyDict.bean, articleSO.get(articleuid));
		model.put(ViewKeyDict.catalogueList, ContributeHP.getCatalogues());
		return "draftdetail";
	}

	/**
	 * 草稿箱文章发布
	 * 
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/my/article/publish", method = RequestMethod.POST)
	public String draft_publish(Map<String, Object> model, String uid, String type, String title, String content, String tags, String kind, String cid, String author, String contributeimg,
			HttpServletRequest request) {
		Article article = articleSO.get(uid);
		if (StrUtil.isNotEmpty(contributeimg)) {
			String[] imgdata = contributeimg.split(BaseStrDict.comma);
			article.setImgoriginal(imgdata[0]);
			article.setImgbig(imgdata[1]);
			article.setImgmedium(imgdata[2]);
			article.setImglittle(imgdata[3]);
		}
		article.setCid(Long.parseLong(cid));
		article.setTitle(title);
		article.setContent(content);
		article.setKind(Integer.parseInt(kind));
		article.setAuthor(author);
		article.setType(Integer.parseInt(type));
		articleSO.update(article);
		Article newArticle = articleSO.get(article.getUid());
		if (article.getType() == 1) {
			ContributeHP.Cache(article.getCid());
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.life.name());
			ContributeHP.getEngine().add(map);
			return redirectPrefix + "/article" + BaseStrDict.slash + newArticle.getCid() + BaseStrDict.slash + newArticle.getOid();
		}
		return redirectPrefix + "/my/center/article";
	}
}