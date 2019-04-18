package com.supergenius.web.front.life.helper;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.server.life.util.TopicRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.rule.LifeSecondCommentCountRule;
import com.supergenius.xo.life.rule.PrizeCountCommentsLifeRule;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 评论HP
 * 
 * @author yangguang
 * @date 2018年5月15日14:44:41
 */
public class CommentsHP extends BaseHP {

	private static CommentsSO so;

	private static VisitorSO visitorSO;

	private static UserSO userSO;

	private static CommentsSO getSO() {
		if (so == null) {
			so = (CommentsSO) spring.getBean(CommentsSO.class);
		}
		return so;
	}

	private static VisitorSO getVisitorSO() {
		if (visitorSO == null) {
			visitorSO = (VisitorSO) spring.getBean(VisitorSO.class);
		}
		return visitorSO;
	}

	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 根据用户uid获取该用户的评论总数
	 * @param uid
	 * @return
	 */
	public static int getcommentComment(String uid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuseruid,uid);
		map.put(MapperDict.type,EComment.comment);
		map.put(MapperDict.status,EStatus.enable);
		List<Comments> list = getSO().getList(map);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTouid() != null){
				Comments comments1 = getSO().get(list.get(i).getTouid());
				if (comments1.getStatus() == EStatus.deleted){
					list.remove(list.get(i));
				}
			}
		}
		return list.size();
	}
	/**
	 * 插入数据（赞、评论） 修改相应的redis 插入数据赞 redis中对应的赞数+1
	 * 
	 * @param comment
	 * @return
	 */
	public static boolean add(Comments comments, HttpServletRequest request) {
		if (getSO().add(comments)) {
			incrCommentCount(comments, request);
			return true;
		}
		return false;
	}

	/**
	 * 对评论、赞计数加1
	 * 
	 * @param comment
	 * @return
	 */
	public static boolean incrCommentCount(Comments comments, HttpServletRequest request) {
		Rule rule = null;
		if (comments.getType() == EComment.comment) {
			if (StrUtil.isNotEmpty(comments.getData())) {
				rule = new LifeSecondCommentCountRule(comments.getData());// 一级评论uid为key
				return RedisUtil.incr(rule) >= 0;
			}
		} else if (comments.getType() == EComment.praise) {
			if (comments.getChannel() == ELifeChannel.articlepraise || comments.getChannel() == ELifeChannel.contentpraise) {
				return ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.prizecount) >= 0;
			} else if (comments.getChannel() == ELifeChannel.commentspraise) {
				rule = new PrizeCountCommentsLifeRule(comments.getFromuid());// 指被赞的评论uid
				return RedisUtil.incr(rule) >= 0;
			} else if (comments.getChannel() == ELifeChannel.topicpraise) {
				return TopicRedisUtil.incr(comments.getFromuid(), ViewKeyDict.prizecount) >= 0;
			}
		}
		return false;
	}

	/**
	 * 得到会员所有的赞
	 * 
	 * @param user
	 * @return
	 */
	public static List<String> getListPrize(String uid) {
		return getSO().getList(uid, EComment.praise);
	}

	/**
	 * 删除评论
	 * 
	 * @param uid
	 * @return
	 */
	public static void deleteComments(String uid) {
		Comments comment = getSO().get(uid);
		if (StrUtil.isEmpty(comment.getTouid())) {
			for (int i = 0; i < comment.getCommentcount() + 1; i++) {
				if (comment.getChannel() == ELifeChannel.articlecomments || comment.getChannel() == ELifeChannel.contentcomments) {
					ArticleRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
				} else if (comment.getChannel() == ELifeChannel.topiccomments) {
					TopicRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
				}
			}
		} else {
			Rule rule = new LifeSecondCommentCountRule(comment.getData());
			RedisUtil.decr(rule);
			if (comment.getChannel() == ELifeChannel.articlecomments || comment.getChannel() == ELifeChannel.contentcomments) {
				ArticleRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
			} else if (comment.getChannel() == ELifeChannel.topiccomments) {
				TopicRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
			}
		}
		comment.setStatus(EStatus.deleted);
		getSO().update(comment);
	}

	/**
	 * 取消赞 对redis中对应的赞数-1
	 * 
	 * @param fromuseruid
	 * @param fromuid
	 * @param channel
	 * @return
	 */
	public static boolean cancelPrize(String fromuseruid, String fromuid, String channel) {
		if (getSO().deleteByPraise(fromuseruid, fromuid, ELifeChannel.get(channel))) {
			Rule rule = null;
			if (ELifeChannel.get(channel) == ELifeChannel.articlepraise || ELifeChannel.get(channel) == ELifeChannel.contentpraise) {
				return ArticleRedisUtil.decr(fromuid, ViewKeyDict.prizecount) >= 0;
			} else if (ELifeChannel.get(channel) == ELifeChannel.topicpraise) {
				return TopicRedisUtil.decr(fromuid, ViewKeyDict.prizecount) >= 0;
			} else if (ELifeChannel.get(channel) == ELifeChannel.commentspraise) {
				rule = new PrizeCountCommentsLifeRule(fromuid);
				return RedisUtil.decr(rule) >= 0;
			}
		}
		return false;
	}

	/**
	 * 对评论list设置fromuser
	 * 
	 * @param list
	 * @return
	 */
	public static void organized(List<Comments> list) {
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.start);
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.wait);
		map.put(MapperDict.statuslist, liststatus);
		List<User> users = getUserSO().getList(map);
		boolean isvisitor = true;
		Visitor visitor;
		for (Comments item : list) {
			isvisitor = true;
			for (User user : users) {
				if (user.getUid().equals(item.getFromuseruid())) {
					item.setFromUser(user);
					item.setUseruid(user.getUid());
					isvisitor = false;
				}
			}
			if (isvisitor) {
				visitor = getVisitorSO().get(item.getFromuseruid());
				item.setFromVisitor(visitor);
				item.setUseruid(visitor.getUseruid());
			}
		}
	}

	/**
	 * 获取游客
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author yangguang
	 */
	public static Visitor getNickVisitor(HttpServletRequest request, HttpServletResponse response) {
		String ip = NetUtil.getIPAddr(request);
		Visitor visitor = getRandomVisitor();
		if (StrUtil.isEmpty(visitor.getAvatar()) || StrUtil.isEmpty(visitor.getNickname())) {
			visitor = getRandomVisitor();
		}
		visitor.setLoginip(ip);
		visitor.setCreatetime(new DateTime());
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			visitor.setUseruid(user.getUid());
		}
		getVisitorSO().add(visitor);
		return visitor;
	}

	/**
	 * 获取游客
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author yangguang
	 */
	public static Visitor getVisitor(HttpServletRequest request, HttpServletResponse response) {
		String visitorUid = CookieUtil.get(request, ViewKeyDict.visitors);
		Visitor visitor;
		Visitor temp;
		if (StrUtil.isNotEmpty(visitorUid)) {
			visitor = getVisitorSO().get(visitorUid);
			if (visitor != null) {
				if (StrUtil.isEmpty(visitor.getNickname()) || StrUtil.isEmpty(visitor.getAvatar())) {
					temp = getRandomVisitor();
					if (temp != null) {
						visitor.setNickname(temp.getNickname());
						visitor.setAvatar(temp.getAvatar());
						getVisitorSO().update(visitor);
					}
				}
			} else {
				String ip = NetUtil.getIPAddr(request);
				visitor = getRandomVisitor();
				visitor.setLoginip(ip);
				visitor.setCreatetime(new DateTime());
				Boolean bool = getVisitorSO().add(visitor);
				if (bool) {
					CookieUtil.addCookie(response, ViewKeyDict.visitors, visitor.getUid(), Integer.MAX_VALUE);
				}
			}
		} else {
			String ip = NetUtil.getIPAddr(request);
			visitor = getRandomVisitor();
			visitor.setLoginip(ip);
			visitor.setCreatetime(new DateTime());
			Boolean bool = getVisitorSO().add(visitor);
			if (bool) {
				CookieUtil.addCookie(response, ViewKeyDict.visitors, visitor.getUid(), Integer.MAX_VALUE);
			}
		}
		return visitor;
	}

	/**
	 * 获取随机昵称和头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 */
	@SuppressWarnings("unchecked")
	public static Visitor getRandomVisitor() {
		String path = SysConf.SerialBasePath + SysConf.SerialUserVisitorPath;
		List<Visitor> list = new ArrayList<Visitor>();
		list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
		Visitor visitor = new Visitor();
		if (list == null || list.size() < 1) {
			visitor.setNickname("匿名");
			visitor.setAvatar("/imgs/webdata/user/avatar/180118164053917f66_300_200.jpg");
		} else {
			visitor = list.get((int) (Math.random() * list.size()));
		}
		return visitor;
	}

	/**
	 * 获取用户是否点赞了
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static boolean isPrise(String uid, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, uid);
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, EComment.praise);
		Comments comments = getSO().getOne(map);
		if (comments != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 按热度获取静态页面的评论
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @param fromuid
	 * 
	 * @return List<Comments>
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Comments> getCommentsHot(int pagenum, int pagesize, Long cid, String fromuid) {
		String path = SysConf.SerialBasePath + "/life/" + fromuid + SysConf.SerialCommentsPath;
		List<Comments> result = new ArrayList<>();
		List<Comments> list = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
		}
		if (list.size() == 0) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.type, EComment.comment);
			map.put(MapperDict.cid, cid);
			map.put(MapperDict.fromuid, fromuid);
			map.put(MapperDict.touidnull, true);
			list = getSO().getList(map);
			List<Comments> tempList = new ArrayList<>();
			tempList.addAll(list);
			SerialUtil.serializeToJson(tempList, path, Json.cacheStrategy);
		}
		list.sort(COMPARATOR);
		
		int maxHotsize = pagenum * pagesize;
		if (maxHotsize > list.size()) {
			maxHotsize = list.size();
		}
		result = list.subList((pagenum - 1) * pagesize, maxHotsize);
		return result;
	}
	
	/**
	 * 删除序列化的评论
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static void deleteCommentsFile(String fromuid) {
		FileUtil.delete(SysConf.SerialBasePath + "/life/" + fromuid + SysConf.SerialCommentsPath);
	};
	
	/**
	 * 获得专家点评
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static Comments getMajor(String fromuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EComment.major);
		map.put(MapperDict.fromuseruid, ViewKeyDict.defaultuid);
		map.put(MapperDict.fromuid, fromuid);
		map.put(MapperDict.touidnull, true);
		return getSO().getOne(map);
	};
	
	/**
	 * 获得专家点评二级列表
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static List<Comments> getSecondMajor(Video video) {
		List<Comments> list = new ArrayList<Comments>();
		Map<String, Object> majormap = getParamMap();
		majormap.put(MapperDict.type, EComment.major);
		majormap.put(MapperDict.fromuseruid, ViewKeyDict.defaultuid);
		majormap.put(MapperDict.fromuid, video.getUid());
		majormap.put(MapperDict.touseruid, video.getUseruid());
		majormap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Comments> majorlist = getSO().getList(majormap);
		Map<String, Object> usermap = getParamMap();
		usermap.put(MapperDict.type, EComment.major);
		usermap.put(MapperDict.fromuseruid, video.getUseruid());
		usermap.put(MapperDict.fromuid, video.getUid());
		usermap.put(MapperDict.touseruid, ViewKeyDict.defaultuid);
		usermap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Comments> userlist = getSO().getList(usermap);
		list.addAll(majorlist);
		list.addAll(userlist);
		for (Comments comments : list) {
			comments.setIsmajor(1);
		}
		list.sort(COMPARATOR);
		return list;
	};
	
	private static final Comparator<Comments> COMPARATOR = new Comparator<Comments>() {
		public int compare(Comments o1, Comments o2) {
			int i = o1.compareTo(o2);
			return i;//
		}
	};

	public static ECatalogue getFirstCid(long cid) {
		List<ECatalogue> list = ECatalogue.getMatch(cid);
		if (list.size() != 0) {
			return list.get(0);
		}
		return ECatalogue.get(cid);
	}

}
