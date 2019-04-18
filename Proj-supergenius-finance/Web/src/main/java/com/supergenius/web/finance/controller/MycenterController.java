package com.supergenius.web.finance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.ContributeHP;
import com.supergenius.web.finance.helper.MycenterHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.News;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.service.UserInfoSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 进入和个人相关的页面：个人资料，消息通知，我的收藏，我的文章
 * 
 * @author ChenQi
 * @date 2018年1月4日15:32:25
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
		if (EChannel.article.name().equals(channel)) {
			Map<String, Object> map = MycenterHP.getArticles(user.getUid(), 1, SysConf.MyArticleSize, request);
			model.put(ViewKeyDict.article, map);
			model.put(ViewKeyDict.channel, EChannel.article.name());
		} else if (EChannel.msg.name().equals(channel)) {
			List<News> newslist = MycenterHP.getMsgs(user.getUid(), 1, SysConf.MyMsgPageSize, request);
			model.put(ViewKeyDict.msg, newslist);
			model.put(ViewKeyDict.channel, EChannel.msg.name());
		} else if (EChannel.collect.name().equals(channel)) {
			List<Article> collectList = MycenterHP.getCollects(user.getUid(), 1, SysConf.MyCollectPageSize, request);
			model.put(ViewKeyDict.collect, collectList);
			model.put(ViewKeyDict.channel, EChannel.collect.name());
		} else if (EChannel.info.name().equals(channel)) {
			model.put(ViewKeyDict.user, user);
			model.put(ViewKeyDict.channel, EChannel.info.name());
		} else if (EChannel.subscribe.name().equals(channel)) {
			Map<String, Object> map = MycenterHP.getMySubscribe(user.getUid());
			model.put(ViewKeyDict.subscribe, map);
			model.put(ViewKeyDict.channel, EChannel.subscribe.name());
		}
		if (StrUtil.isNotEmpty(request.getAttribute(ViewKeyDict.phone)) && StrUtil.isNotEmpty(phone)) {
			return "phonecenter";
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
		List<Article> list = MycenterHP.getMoreArticles(user, pagenum, SysConf.MyArticleSize, request, channel);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}

	/**
	 * 消息通知
	 * 
	 * @param pagenum
	 * @param request
	 * @return
	 * @author ChenQi
	 * @dete 2018年1月4日15:32:30
	 */
	@RequestMapping(value = "/ajax/my/msg", method = RequestMethod.GET)
	public String ajax_msg(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<News> msgs = MycenterHP.getMsgs(BaseUserHP.getCurrUser(request).getUid(), pagenum, SysConf.MyMsgPageSize, request);
		model.put(ViewKeyDict.msg, msgs);
		return "ajaxmsg";
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
		return MycenterHP.deleteAllMsg(BaseUserHP.getCurrUser(request).getUid());
	}

	/**
	 * 我的收藏加载更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param type
	 * @param request
	 * @return
	 * @author ChenQi
	 */
	@RequestMapping(value = "/ajax/my/collect", method = RequestMethod.GET)
	public String ajax_collect(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Article> collects = MycenterHP.getCollects(BaseUserHP.getCurrUser(request).getUid(), pagenum, SysConf.MyCollectPageSize, request);
		model.put(ViewKeyDict.list, collects);
		return "ajaxarticle";
	}

	/**
	 * 阅读消息
	 * 
	 * @param uid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月4日15:32:35
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
	 * @author ChenQi
	 * @date 2018年1月4日15:32:37
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
	@RequestMapping(value = "/finance/my/article/{articleuid:.{32}}", method = RequestMethod.GET)
	public String draft_detail(Map<String, Object> model, @PathVariable String articleuid, HttpServletRequest request) {
		model.put(ViewKeyDict.bean, articleSO.get(articleuid));
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
	public String draft_publish(Map<String, Object> model, String uid, String type, String title, String content, String tags, String kind, String cid, String author,  String contributeimg, HttpServletRequest request) {
		Article article = articleSO.get(uid); 
		if (StrUtil.isNotEmpty(contributeimg)) {
			String[] imgdata = contributeimg.split(BaseStrDict.comma);
			article.setImgoriginal(imgdata[0]);
			article.setImgbig(imgdata[1]);
			article.setImgmedium(imgdata[2]);
			article.setImglittle(imgdata[3]);
		}
		article.setCid(Integer.parseInt(cid));
		article.setTitle(title);
		article.setContent(content);
		article.setTags(tags);
		article.setKind(Integer.parseInt(kind));
		article.setAuthor(author);
		article.setType(Integer.parseInt(type));
		article.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		articleSO.update(article);
		Article newArticle = articleSO.get(article.getUid());
		if (article.getType() == 1) {
			ContributeHP.Cache(article);
			if (StrUtil.isNotEmpty(article.getTags())) {
				ContributeHP.add(article.getUid(), article.getTags());
			}
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.finance.name());
			ContributeHP.getEngine().add(map);
			return redirectPrefix + "/article" + BaseStrDict.slash + newArticle.getCid() + BaseStrDict.slash
					+ newArticle.getOid();
		}
		return redirectPrefix + "/my/center/article";
	}
}
