package com.supergenius.web.front.life.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.News;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CollectSO;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.life.service.ProblemSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.life.service.VideoSO;
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

	private static CommentsSO commentsSO;

	private static NewsSO newsSO;

	private static UserSO userSO;

	private static TopicSO topicSO;

	private static VideoSO videoSO;

	private static ProblemSO problemSO;

	public static ProblemSO getProblemSO() {
		if (problemSO == null) {
			problemSO = (ProblemSO) spring.getBean(ProblemSO.class);
		}
		return problemSO;
	}
	public static CommentsSO getCommentsSO() {
		if (commentsSO == null) {
			commentsSO = (CommentsSO) spring.getBean(CommentsSO.class);
		}
		return commentsSO;
	}

	public static VideoSO getVideoSO() {
		if (videoSO == null) {
			videoSO = (VideoSO) spring.getBean(VideoSO.class);
		}
		return videoSO;
	}

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

	public static TopicSO getTopicSO() {
		if (topicSO == null) {
			topicSO = (TopicSO) spring.getBean(TopicSO.class);
		}
		return topicSO;
	}

	/**
	 * 获取我的文章
	 * 
	 * @param
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:44:57
	 */
	public static Map<String, Object> getArticles(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> draftmap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> result = new HashMap<>();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> publishedList = getArticleSO().getList(map);
		draftmap.put(MapperDict.useruid, useruid);
		draftmap.put(MapperDict.type, EStatus.disable);
		List<Article> draftList = getArticleSO().getList(draftmap);
		ArticleHP.initArticleList(publishedList);
		ArticleHP.initArticleList(draftList);
		result.put(ViewKeyDict.published, publishedList);
		result.put(ViewKeyDict.draft, draftList);
		return result;
	}
	
	/**
	 * 手机端获取我的文章
	 * 
	 * @param
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月16日15:44:57
	 */
	public static List<Article> getArticlesMobile(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> publishedList = getArticleSO().getList(map);
		ArticleHP.initArticleList(publishedList);
		return publishedList;
	}
	
	
	/**
	 * 获取我的文章更多
	 * 
	 * @param uid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:45:55
	 */
	public static List<Article> getMoreArticles(String uid, int pagenum, int pagesize, String channel) {
		List<Article> list = null;
		if (ViewKeyDict.published.equals(channel)) {
			Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
			map.put(MapperDict.useruid, uid);
			map.put(MapperDict.type, EStatus.enable);
			list = getArticleSO().getList(map);
		} else if (ViewKeyDict.draft.equals(channel)) {
			Map<String, Object> draftmap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
			draftmap.put(MapperDict.useruid, uid);
			draftmap.put(MapperDict.type, EStatus.disable);
			list = getArticleSO().getList(draftmap);
		}
		return list;
	}

	/**
	 * 获取我的视频
	 * 
	 * @param
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:44:57
	 */
	public static Map<String, Object> getVideos(String useruid, int pagenum, int pagesize) {
		Map<String, Object> testMap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> stageMap = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		Map<String, Object> result = new HashMap<>();
		testMap.put(ViewKeyDict.useruid, useruid);
		testMap.put(ViewKeyDict.cid, ECatalogue.test);
		List<Video> testList = getVideoSO().getList(testMap);
		VideoHP.organized(testList);
		stageMap.put(ViewKeyDict.useruid, useruid);
		stageMap.put(ViewKeyDict.cid, ECatalogue.stage);
		List<Video> stageList = getVideoSO().getList(stageMap);
		VideoHP.organized(stageList);
		result.put(ViewKeyDict.testList, testList);
		result.put(ViewKeyDict.stageList, stageList);
		return result;
	}

	/**
	 * @author 雍雪振
	 * @time 2018年5月22日下午4:16:55
	 * @description: 获取我的视频更多
	 */
	public static List<Video> getMoreVideos(String uid, int pagenum, int myVideoSize, ECatalogue eCatalogue) {
		List<Video> list = null;
		if (ECatalogue.test.equals(eCatalogue)) {
			Map<String, Object> testMap = getParamMap(Pager.getNewInstance(pagenum, myVideoSize));
			testMap.put(ViewKeyDict.useruid, uid);
			testMap.put(ViewKeyDict.cid, ECatalogue.test);
			list = getVideoSO().getList(testMap);
		} else if (ECatalogue.stage.equals(eCatalogue)) {
			Map<String, Object> stageMap = getParamMap(Pager.getNewInstance(pagenum, myVideoSize));
			stageMap.put(ViewKeyDict.useruid, uid);
			stageMap.put(ViewKeyDict.cid, ECatalogue.stage);
			list = getVideoSO().getList(stageMap);
		}
		return list;
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:49:47
	 */
	public static List<Article> getCollects(String useruid, int pagenum, int pagesize) {
		List<Article> list = getArticleSO().getCollectList(useruid, Pager.getNewInstance(pagenum, pagesize));
		ArticleHP.initArticleList(list);
		return list;
	}

	/**
	 * 获取我的提问
	 * 
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:49:47
	 */
	public static List<Problem> getProblems(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(ViewKeyDict.useruid, useruid);
		List<Problem> list = getProblemSO().getList(map);
		return list;
	}
	/**
	 * 获取我的评论,一级，二级
	 *
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:49:47
	 */
	public static List<Comments> getComments(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type,EComment.comment);
		map.put(MapperDict.status,EStatus.enable);
		List<Comments> list = getCommentsSO().getList(map);

		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTouid() != null){
				Comments comments1 = getCommentsSO().get(list.get(i).getTouid());
				if (comments1.getStatus() == EStatus.deleted){
					list.remove(list.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * 获取我的话题
	 * 
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:49:47
	 */
	public static List<Topic> getTopics(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(ViewKeyDict.useruid, useruid);
		List<Topic> list = getTopicSO().getList(map);
		return list;
	}
	/**
	 * @author 雍雪振
	 * @time 2018年5月22日下午5:01:23
	 * @description: 获取我参与的话题 
	 */
	public static List<Topic> getJoinTopics(String uid, int pagenum, int topicLoadSize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, topicLoadSize));
		map.put(MapperDict.useruid, uid);
		List<Topic> list =getTopicSO().getCommentsTopic(map);
		return list;
	}

	/**
	 * 获取我的消息
	 * 
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:47:26
	 */
	public static List<News> getMsgs(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.touid, useruid);
		List<News> list = getNewsSO().getList(map);
		User user;
		for (News news : list) {
			user = getUserSO().get(news.getFromuid());
			if (user != null) {
				news.setFromusername(user.getUsername());
			}
		}
		return list;
	}
	/**
	 * 获取更多我的评论
	 *
	 * @param useruid
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日15:47:26
	 */
	public static List<Comments> getCommentList(String useruid, int pagenum, int pagesize) {
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type,EComment.comment);
		List<Comments> list = getCommentsSO().getList(map);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTouid() != null){
				Comments comments1 = getCommentsSO().get(list.get(i).getTouid());
				if (comments1.getStatus() == EStatus.deleted){
					list.remove(list.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * 删除消息
	 * 
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
	 * 
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
		map.put(MapperDict.isread, false);
		return getNewsSO().getCount(map);
	}

	

}