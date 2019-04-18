package com.supergenius.web.admin.manager.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.core.rule.CommentCountPkRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Comments;
import com.supergenius.xo.manager.service.CommentsSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.manager.service.VideoSO;
import com.supergenius.xo.user.enums.EComment;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 后台评论HP
 * @author chenminchang
 * @date 2016-11-4 下午3:24:30 
 */
public class CommentsHP extends BaseHP {
	
	private static CommentsSO so;
	private static UserSO userSO;
	private static VideoSO videoSO;
	private static PkScheduleSO pkScheduleSO;
	
	private static CommentsSO getSO() {
		if (so == null) {
			so = spring.getBean(CommentsSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static VideoSO getVideoSO() {
		if (videoSO == null) {
			videoSO = spring.getBean(VideoSO.class);
		}
		return videoSO;
	}
	
	private static PkScheduleSO getPkScheduleSO() {
		if (pkScheduleSO == null) {
			pkScheduleSO = spring.getBean(PkScheduleSO.class);
		}
		return pkScheduleSO;
	}
	
	/**
	 * 获取查询结果
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		map.put(MapperDict.type, EComment.comment);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, EChannel.get(Integer.valueOf(model.get(ViewKeyDict.channel).toString())));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.keywords))) {
			map.put(MapperDict.refname + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
			map.put(MapperDict.content + MapperDict.suffix_like_key, model.get(ViewKeyDict.keywords).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		List<Comments> list = getSO().search(map);
		for (Comments comments : list) {
			comments.setFromUser(getUserSO().get(comments.getFromuseruid()));
			if (EChannel.video.equals(comments.getChannel())) 
				comments.setFromName(getVideoSO().get(comments.getFromuid()).getName());
			else
				comments.setFromName(getPkScheduleSO().get(comments.getFromuid()).getName());
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 解冻冻结评论
	 * @param string
	 * @param eStatus
	 * @return
	 * @author chenminchang
	 * @create 2016-11-8下午5:51:33
	 */
	public static boolean updateStatus(Comments comments, EStatus status) {
		String directory = comments.getChannel().name();
		deleteSerialFile(directory, comments.getFromuid());
		if (StrUtil.isNotEmpty(comments.getTouid())) {
			if (EStatus.enable.equals(status)) {//解冻
				return incrCommentCount(comments);//评论数加1
			} else if (EStatus.disable.equals(status)) {//冻结
				return decrCommentCount(comments);//评论数减1
			}
		}
		return true;
	}
	
	/**
	 * 删除序列化文件
	 * @param directory
	 * @param fromuid
	 */
	public static void deleteSerialFile(String directory, String fromuid) {
		FileUtil.delete(getSerialPath(directory, fromuid));
	}
	
	/**
	 * 获取序列化文件的路径
	 * @param directory
	 * @param uid 记录的uid
	 * @return
	 * @author ShangJianguo
	 */
	private static String getSerialPath(String directory, String uid) {
		if (StringUtils.isEmpty(directory)) {
			directory = "others";
		}
		return SysConf.SerialBasePath + SysConf.SerialManagerCommentPath + SysConf.Separator_Directory + uid + SysConf.dot + SysConf.SerialCommentsExt;
	}
	
	/**
	 * 对评论计数减1
	 * @param comment
	 * @return
	 */
	public static boolean decrCommentCount(Comments comments) {
		Rule rule = null;
		if (comments.getType() == EComment.comment) {
			rule = new CommentCountPkRule(comments.getTouid());
			return RedisUtil.decr(rule) >= 0;
		} 
		return false;
	}
	
	/**
	 * 对评论计数加1
	 * @param comment
	 * @return
	 */
	public static boolean incrCommentCount(Comments comments) {
		Rule rule = null;
		if (comments.getType() == EComment.comment) {
			rule = new CommentCountPkRule(comments.getTouid());
			return RedisUtil.incr(rule) >= 0;
		} 
		return false;
	}

}
