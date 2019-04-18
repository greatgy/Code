package com.supergenius.web.finance.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.News;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CollectSO;
import com.supergenius.xo.finance.service.NewsSO;
import com.supergenius.xo.finance.service.SubscribeSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 个人中心HP
 * 
 * @author 杨光
 * @date 2017年9月19日17:58:51
 */
public class MycenterHP extends BaseHP {

	private static CollectSO collectSO;
	
	private static ArticleSO articleSO;
	
	private static NewsSO newsSO;
	
	private static UserSO userSO;
	
	private static SubscribeSO subscribeSO;

	public static CollectSO getCollectSO() {
		if (collectSO == null) {
			collectSO = (CollectSO) spring.getBean(CollectSO.class);
		}
		return collectSO;
	}
	
	public static ArticleSO getArticleSO() {
		if (articleSO == null) {
			articleSO = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return articleSO;
	}
	
	public static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}
	
	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	public static SubscribeSO getSubscribeSO() {
		if (subscribeSO == null) {
			subscribeSO = (SubscribeSO) spring.getBean(SubscribeSO.class);
		}
		return subscribeSO;
	}
	
	/**
	 * 获取我的文章
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日18:01:45
	 */
	public static Map<String, Object> getArticles(String useruid, int pagenum, int pagesize, HttpServletRequest request) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> draftmap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> result = new HashMap<>();
		map.put(MapperDict.authoruid, useruid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> publishedList = getArticleSO().getList(map);
		draftmap.put(MapperDict.authoruid, useruid);
		draftmap.put(MapperDict.type, EStatus.disable);
		List<Article> draftList = getArticleSO().getList(draftmap);
		IndexHP.initArticleList(publishedList);
		IndexHP.initArticleList(draftList);
		result.put(ViewKeyDict.published, publishedList);
		result.put(ViewKeyDict.draft, draftList);
		return result;
	}
	
	/**
	 * 获取我的文章更多
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年9月21日10:54:58
	 */
	public static List<Article> getMoreArticles(User user, int pagenum, int pagesize, HttpServletRequest request, String channel) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> draftmap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		List<Article> list = null;
		if (ViewKeyDict.published.equals(channel)) {
			map.put(MapperDict.authoruid, user.getUid());
			map.put(MapperDict.type, EStatus.enable);
			list = getArticleSO().getList(map);
		} else if (ViewKeyDict.draft.equals(channel)) {
			draftmap.put(MapperDict.authoruid, user.getUid());
			draftmap.put(MapperDict.type, EStatus.disable);
			list = getArticleSO().getList(draftmap);
		}
		return list;
	}
	
	/**
	 * 获取他的主页文章
	 * 
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年9月20日18:07:12
	 */
	public static List<Article> getHisArticle(String uid, int pagenum, int pagesize, HttpServletRequest request) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.authoruid, uid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getArticleSO().getList(map);
		for (Article article : list) {
			if (article != null) {
				IndexHP.setSummary(article);
				article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
				article.setCollectcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.collectcount));
				article.setCommentscount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.commentscount));
				article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
			}
		}
		return list;
	}
	
	/**
	 * 获取我的收藏
	 * 
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年8月28日16:18:56
	 */
	public static List<Article> getCollects(String useruid, int pagenum, int pagesize, HttpServletRequest request) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		List<Article> list = getArticleSO().getCollectList(useruid, Pager.getNewInstance(pagenum, pagesize));
		for (Article article : list) {
			if (article != null) {
				IndexHP.setSummary(article);
				article.setClickcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.clickcount));
				article.setCollectcount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.collectcount));
				article.setCommentscount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.commentscount));
				article.setPrizecount(ArticleRedisUtil.getInt(article.getUid(), ViewKeyDict.prizecount));
			}
		}
		return list;
	}
	
	/**
	 * 获取我的订阅
	 * 
	 * @param useruid
	 * @param request
	 * @return
	 * @author ChenQi
	 * @date 2018年1月6日14:10:35
	 */
	public static Map<String, Object> getMySubscribe(String useruid) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> mySubMap = getParamMap();
		mySubMap.put(MapperDict.useruid, useruid);
		List<Subscribe> mySubList = getSubscribeSO().getList(mySubMap);
		Map<String, Object> submeMap = getParamMap();
		submeMap.put(MapperDict.refuseruid, useruid);
		List<Subscribe> submeList = getSubscribeSO().getList(submeMap);
		organizeMySubscribe(mySubList);
		organizeSubscribeme(submeList);
		result.put(ViewKeyDict.mySubscribe, mySubList);
		result.put(ViewKeyDict.subme, submeList);
		return result;
	}
	
	/**
	 * 获取我的消息
	 * 
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日18:33:26
	 */
	public static List<News> getMsgs(String useruid, int pagenum, int pagesize, HttpServletRequest request) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.touid, useruid);
		List<News> list = getNewsSO().getList(map);
		User user;
		for (News news : list) {
			user = getUserSO().get(news.getFromuid());
			if (user != null) {
				if (StrUtil.isNotEmpty(user.getShowname())) {
					news.setFromusername(user.getShowname());
				} else {
					news.setFromusername(user.getName());
				}
			}
		}
		return list;
	}
	
	/**
	 * 删除消息
	 * @param newsuid
	 * @return
	 * @author 杨光
	 * @date 2017年9月20日09:31:34
	 */
	public static boolean deleteMsg(String newsuid) {
		if (StringUtils.isNotEmpty(newsuid)) {
			News news = getNewsSO().get(newsuid);
			news.setStatus(EStatus.disable);
			if (getNewsSO().update(news)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 清空消息框
	 * @param usersuid
	 * @return
	 * @author 杨光
	 * @date 2017年9月20日09:51:23
	 */
	public static boolean deleteAllMsg(String usersuid) {
		if (StringUtils.isNotEmpty(usersuid)) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.touid, usersuid);
			map.put(MapperDict.status, EStatus.disable);
			if (getNewsSO().updateByUseruid(map)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 阅读消息
	 * 
	 * @param uid
	 * @return
	 * @author 杨光
	 */
	public static boolean readMsg(String uid) {
		if (StringUtils.isNotEmpty(uid)) {
			News news = getNewsSO().get(uid);
			news.setIsread(true);
			if (getNewsSO().update(news)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 未阅读消息数量
	 * 
	 * @param uid
	 * @return
	 * @author 杨光
	 */
	public static int unreadMsg(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.touid, uid);
		map.put(MapperDict.isread, EStatus.disable);
		return getNewsSO().getCount(map);
	}
	
	/**
	 * 初始化我的订阅
	 * 
	 * @param useruid
	 * @param request
	 * @return
	 * @author ChenQi
	 * @date 2018年1月6日14:10:35
	 */
	public static void organizeMySubscribe(List<Subscribe> list) {
		for (Subscribe subscribe : list) {
			subscribe.setRefuser(getUserSO().get(subscribe.getRefuseruid()));
			subscribe.setArticlecount(ArticleHP.getHisCount(subscribe.getRefuseruid()));
		}
	}
	
	/**
	 * 初始化订阅我的
	 * 
	 * @param useruid
	 * @param request
	 * @return
	 * @author ChenQi
	 * @date 2018年1月6日14:10:35
	 */
	public static void organizeSubscribeme(List<Subscribe> list) {
		for (Subscribe subscribe : list) {
			subscribe.setRefuser(getUserSO().get(subscribe.getUseruid()));
			subscribe.setArticlecount(ArticleHP.getHisCount(subscribe.getUseruid()));
		}
	}
	
	/**
	 * 获得订阅
	 * 
	 * @param useruid
	 * @param refuseruid
	 * @return
	 * @author ChenQi
	 * @date 2018年1月6日14:10:35
	 */
	public static Subscribe getSubscribe(String useruid, String refuseruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.refuseruid, refuseruid);
		return getSubscribeSO().getOne(map);
	}
}