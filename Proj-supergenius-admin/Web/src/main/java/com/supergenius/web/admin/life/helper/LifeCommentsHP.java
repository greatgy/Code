package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.life.service.ContentSO;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 评论管理HP
 * 
 * @author ChenQi
 * @date 2018年5月10日19:02:30
 */
public class LifeCommentsHP extends BaseHP {

	private static CommentsSO so;
	private static ArticleSO articleSO;
	private static TopicSO topicSO;
	private static VideoSO videoSO;
	private static ContentSO contentSO;
	private static UserSO userSO;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static VisitorSO visitorSO;
	private static NewsSO newsSO;

	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = (CatalogueSO) spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}
	private static CommentsSO getSO() {
		if (so == null) {
			so = (CommentsSO) spring.getBean(CommentsSO.class);
		}
		return so;
	}

	private static ArticleSO getArticleSO() {
		if (articleSO == null) {
			articleSO = spring.getBean(ArticleSO.class);
		}
		return articleSO;
	}

	private static TopicSO getTopicSO() {
		if (topicSO == null) {
			topicSO = spring.getBean(TopicSO.class);
		}
		return topicSO;
	}
	
	private static VideoSO getVideoSO() {
		if (videoSO == null) {
			videoSO = spring.getBean(VideoSO.class);
		}
		return videoSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static ContentSO getContentSO() {
		if (contentSO == null) {
			contentSO = spring.getBean(ContentSO.class);
		}
		return contentSO;
	}
	
	private static VisitorSO getVisitorSO() {
		if (visitorSO == null) {
			visitorSO = spring.getBean(VisitorSO.class);
		}
		return visitorSO;
	}


	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}
	
	private static NewsSO getNewsSO() {
		if (newsSO == null) {
			newsSO = (NewsSO) spring.getBean(NewsSO.class);
		}
		return newsSO;
	}

	/**
	 * 查询评论时组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.search))) {
			map.put(ViewKeyDict.fromusername + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			map.put(BaseMapperDict.content + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			Article article = getArticleSO().getOneByField(BaseMapperDict.title + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			if (article != null) {
				map.put(BaseMapperDict.fromuid, article.getUid());
			} else {
				map.put(BaseMapperDict.fromuid, "");
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String startTime = model.get(ViewKeyDict.createtimestart).toString().trim() + MapperDict.starttimeformat;
			map.put(MapperDict.createtimestart, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String endTime = model.get(ViewKeyDict.createtimeend).toString().trim() + MapperDict.endtimeformat;
			map.put(MapperDict.createtimeend, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.type + MapperDict.asc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.type, EComment.comment);
		List<Comments> list = getSO().search(map);
		for (Comments comments : list) {
			if (StrUtil.isNotEmpty(comments.getAdminuid())) {
				Admin admin = getAdminSO().get(comments.getAdminuid());
				if (admin != null) {
					comments.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(comments.getFromuid())) {
				if (comments.getChannel() == ELifeChannel.articlecomments) {
					Article article = getArticleSO().get(comments.getFromuid());
					if (article != null) {
						comments.setTitle(article.getTitle());
						comments.setCataloguename(getCatalogueSO().getOneByCid(article.getFirstCid()).getName()); 
					}
				} else if (comments.getChannel() == ELifeChannel.topiccomments) {
					Topic topic = getTopicSO().get(comments.getFromuid());
					if (topic != null) {
						comments.setTitle(topic.getTitle());
						comments.setCataloguename(getCatalogueSO().getOneByCid(topic.getCid()).getName()); 
					}
				} else if (comments.getChannel() == ELifeChannel.videocomments) {
					Video video = getVideoSO().get(comments.getFromuid());
					if (video != null) {
						comments.setTitle(video.getTitle());
						comments.setCataloguename(getCatalogueSO().getOneByCid(video.getCid()).getName()); 
					}
				} else if (comments.getChannel() == ELifeChannel.contentcomments) {
					Content content = getContentSO().get(comments.getFromuid());
					if (content != null) {
						comments.setTitle(content.getName());
						comments.setCataloguename(getCatalogueSO().getOneByCid(content.getCid()).getName()); 
					}
				}
				
			}
			User user = getUserSO().get(comments.getFromuseruid());
			if (user != null) {
				comments.setIp(user.getLastloginip());
			} else {
				Visitor visitor = getVisitorSO().get(comments.getFromuseruid());
				if (visitor != null) {
					comments.setIp(visitor.getLoginip());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 获取参与人数的数量
	 * 
	 * @return String
	 * @author ChenQi
	 */
	public static String getUserCount() {
		int commentsCount = getSO().getCountByfromuseruid();
		return String.valueOf(commentsCount);
	}

	/**
	 * 删除序列化的评论
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static void deleteComments(String fromuid) {
		FileUtil.delete(SysConf.SerialBasePath + "/life/" + fromuid + SysConf.SerialCommentsPath);
	};
	
	/**
	 * 点评回复之后发送消息
	 * 
	 * @param comment
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Comments comment, String touseruid, String title, ELifeMsg eMsg, String href) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.title, title);
		map.put(MapperDict.touid, touseruid);
		map.put(MapperDict.content, comment.getContent());
		map.put(MapperDict.type, eMsg);
		map.put(MapperDict.href, href);
		map.put(MapperDict.fromuseruid, comment.getFromuseruid());
		getNewsSO().add(map);
	}
}