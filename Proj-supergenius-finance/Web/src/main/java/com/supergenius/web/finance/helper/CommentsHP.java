package com.supergenius.web.finance.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.entity.Comments;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.rule.FinanceSecondCommentCountRule;
import com.supergenius.xo.finance.rule.PrizeCountCommentsFinanceRule;
import com.supergenius.xo.finance.service.CommentsSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 评论HP
 * 
 * @author yangguang
 * @date 2017年8月29日14:29:39
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
				rule = new FinanceSecondCommentCountRule(comments.getData());// 一级评论uid为key
				return RedisUtil.incr(rule) >= 0;
			}
		} else if (comments.getType() == EComment.praise) {
			if (comments.getChannel() == EChannel.finance) {
				return ArticleRedisUtil.incr(comments.getFromuid(), ViewKeyDict.prizecount) >= 0;
			} else if (comments.getChannel() == EChannel.comments) {
				rule = new PrizeCountCommentsFinanceRule(comments.getFromuid());// 指被赞的评论uid
				return RedisUtil.incr(rule) >= 0;
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
	public static List<String> getListPrize(User user) {
		if (user == null)
			return null;
		List<String> praises = new ArrayList<>();
		praises = getSO().getList(user.getUid(), EComment.praise);
		return praises;
	}
	
	/**
	 * 删除评论
	 * 
	 * @param uid
	 * @return
	 */
	public static void deleteComments(String uid) {
		List<String> uids = new ArrayList<>();
		Map<String, Object> updatemap = getParamMap();
		Comments comment = getSO().get(uid);
		if (StrUtil.isEmpty(comment.getTouid())) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.data, uid);
			map.put(MapperDict.type, EComment.comment);
			List<Comments> list = getSO().getList(map);
			for (Comments comments : list) {
				uids.add(comments.getUid());
			}
			for (int i = 0 ; i < comment.getCommentcount() + 1 ; i++) {
				ArticleRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
			}
		}else {
			Rule rule = new FinanceSecondCommentCountRule(comment.getData());
			RedisUtil.decr(rule);
			ArticleRedisUtil.decr(comment.getFromuid(), ViewKeyDict.commentscount);
		}
		uids.add(uid);
		updatemap.put(MapperDict.uidlist, uids);
		updatemap.put(MapperDict.status, EStatus.deleted);
		getSO().updateFields(updatemap);
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
		if (getSO().deleteByPraise(fromuseruid, fromuid, EChannel.get(channel))) {
			Rule rule = null;
			if (EChannel.get(channel) == EChannel.finance) {
				return ArticleRedisUtil.decr(fromuid, ViewKeyDict.prizecount) >= 0;
			} else if (EChannel.get(channel) == EChannel.comments) {
				rule = new PrizeCountCommentsFinanceRule(fromuid);
				return RedisUtil.decr(rule) >= 0;
			}
		}
		return false;
	}

	/**
	 * 反序列化list 赞
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> deserializeFromJson(String serialpath, String useruid) {
		String fullpath = SysConf.SerialBasePath + serialpath + SysConf.Separator_Directory + useruid;
		List<String> praiseuids = (List<String>) SerialUtil.deserializeFromJson(fullpath, List.class);
		return praiseuids;
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
		if (StrUtil.isEmpty(visitor.getAvatar()) || StrUtil.isEmpty(visitor.getNickname()) ) {
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
			visitor = list.get((int)(Math.random() * list.size()));
		}
		return visitor;
	}
	
	/**
	 * 获取用户是否点赞了该文章
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static boolean isPrise(String articleUid, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, articleUid);
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, EComment.praise);
		Comments comments = getSO().getOne(map);
		if (comments != null) {
			return true;
		}
		return false;
	}
}
