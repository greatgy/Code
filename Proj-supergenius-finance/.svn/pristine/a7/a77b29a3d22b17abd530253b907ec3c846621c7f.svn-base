package com.supergenius.web.finance.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserConfigHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Comments;
import com.supergenius.xo.finance.entity.News;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CommentsSO;
import com.supergenius.xo.finance.service.NewsSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 消息HP
 * 
 * @author yangguang
 * @date 2017年8月29日17:18:18
 */
public class NewsHP extends BaseHP {

	private static ArticleSO articleSO;

	private static NewsSO so;
	
	private static CommentsSO commentsSO;
	
	private static UserSO userSO;
	
	
	private static NewsSO getSO() {
		if (so == null) {
			so = (NewsSO) spring.getBean(NewsSO.class);
		}
		return so;
	}

	private static ArticleSO getArticleSO() {
		if (articleSO == null) {
			articleSO = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return articleSO;
	}

	private static CommentsSO getCommentsSO() {
		if (commentsSO == null) {
			commentsSO = (CommentsSO) spring.getBean(CommentsSO.class);
		}
		return commentsSO;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	/**
	 * 评论之后发送消息
	 * 
	 * @param comment
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Comments comment, int cid, EFinanceMsg eMsg) {
		if (comment==null) {
			return;
		}
		Article finance = new Article();
		if (eMsg == EFinanceMsg.praisecomments) {
			finance = getArticleSO().get(getCommentsSO().get(comment.getFromuid()).getFromuid());
		} else {
			finance = getArticleSO().get(comment.getFromuid());
		}
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, eMsg);
		if (StrUtil.isEmpty(comment.getTouseruid())) {
			map.put(MapperDict.touid, finance.getAuthoruid());
		} else {
			map.put(MapperDict.touid, comment.getTouseruid());
		}
		map.put(MapperDict.content, comment.getContent());
		if (comment.getType() == EComment.praise) {
			map.put(MapperDict.title, comment.getType().getName());
			map.put(MapperDict.href, WebConf.baseRootPath + MapperDict.slash + MapperDict.article + MapperDict.slash + cid + MapperDict.slash + finance.getOid());
		}else {
			if (StrUtil.isEmpty(comment.getTouid())) {
				map.put(MapperDict.title, comment.getContent());
			} else {
				map.put(MapperDict.title, getCommentsSO().get(comment.getTouid()).getContent());
			}
			map.put(MapperDict.href, WebConf.baseRootPath + MapperDict.slash + MapperDict.article + MapperDict.slash + cid + MapperDict.slash + finance.getOid());
		}
		map.put(MapperDict.fromuseruid, comment.getFromuseruid());
		String userIgnoreFilePath = SysConf.getUserIgnoreFilePath();
		sendInbox(map, userIgnoreFilePath);
	}
	
	/**
	 * 订阅之后发送消息
	 * 
	 * @param comment
	 * @return
	 * @author yangguang
	 */
	public static void sendMsg(Subscribe subscribe, EFinanceMsg eMsg) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, eMsg);
		map.put(MapperDict.fromuseruid, subscribe.getUseruid());
		map.put(MapperDict.touid, subscribe.getRefuseruid());
		map.put(MapperDict.title, "前往我的订阅查看");
		map.put(MapperDict.href, WebConf.baseRootPath + MapperDict.slash + ViewKeyDict.my + MapperDict.slash + ViewKeyDict.center + MapperDict.slash + ViewKeyDict.subscribe);
		map.put(MapperDict.content, "");
		String userIgnoreFilePath = SysConf.getUserIgnoreFilePath();
		sendInbox(map, userIgnoreFilePath);
	}
	
	/**
	 * 发送消息同时也会将消息注入inbox中
	 * 
	 * @param map
	 * @return
	 * @author yangguang
	 */
	public static boolean sendInbox(Map<String, Object> map, String userIgnoreFilePath) {
		String touid = (String) map.get(MapperDict.touid);
		if (touid != null) {
			User user = getUserSO().get(touid);
			if (user != null) {
				News msg = getSO().add(map);
				BaseUserConfigHP.updateUserIgnoreMsgFile(user.getOid(), userIgnoreFilePath, msg.getType().name() + MapperDict.msgcount, EChannel.finance.name());
			}
		}
		return false;
	}
}
