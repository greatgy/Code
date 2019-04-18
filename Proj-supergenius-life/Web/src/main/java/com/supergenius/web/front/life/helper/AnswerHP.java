package com.supergenius.web.front.life.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Answer;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.rule.LifePbmSecondCmtCountRlue;
import com.supergenius.xo.life.rule.LifeProblemCommentCountRlue;
import com.supergenius.xo.life.rule.LifeProblemPrizeCountRlue;
import com.supergenius.xo.life.rule.PrizeCountAnswerLifeRule;
import com.supergenius.xo.life.service.AnswerSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 回答HP
 * 
 * @author ChenQi
 * @date 2018年5月17日11:07:16
 */
public class AnswerHP extends BaseHP {

	private static AnswerSO so;

	private static VisitorSO visitorSO;

	private static UserSO userSO;

	private static AnswerSO getSO() {
		if (so == null) {
			so = (AnswerSO) spring.getBean(AnswerSO.class);
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
	public static boolean add(Answer answer, HttpServletRequest request) {
		if (getSO().add(answer)) {
			incrCommentCount(answer, request);
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
	public static boolean incrCommentCount(Answer answer, HttpServletRequest request) {
		Rule rule = null;
		if (answer.getType() == EComment.comment) {
			if (StrUtil.isNotEmpty(answer.getData())) {
				rule = new LifePbmSecondCmtCountRlue(answer.getData());// 一级评论uid为key
				return RedisUtil.incr(rule) >= 0;
			}
		} else if (answer.getType() == EComment.praise) {
			if (answer.getChannel() == ELifeChannel.problempraise) {
				rule = new LifeProblemPrizeCountRlue(answer.getFromuid());
				return RedisUtil.incr(rule) >= 0;
			} else if (answer.getChannel() == ELifeChannel.answerpraise) {
				rule = new PrizeCountAnswerLifeRule(answer.getFromuid());// 指被赞的评论uid
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
	public static void deleteAnswer(String uid, String useruid) {
		List<String> uids = new ArrayList<>();
		Map<String, Object> updatemap = getParamMap();
		Answer answer = getSO().get(uid);
		if (StrUtil.isEmpty(answer.getTouid())) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.data, uid);
			map.put(MapperDict.type, EComment.comment);
			List<Answer> list = getSO().getList(map);
			for (Answer temp : list) {
				uids.add(temp.getUid());
			}
			for (int i = 0 ; i < answer.getCommentcount() + 1 ; i++) {
				Rule rule = new LifeProblemCommentCountRlue(answer.getFromuid());// 指被赞的评论uid
				RedisUtil.decr(rule);
			}
		}else {
			Rule rule = new LifePbmSecondCmtCountRlue(answer.getData());
			RedisUtil.decr(rule);
			rule = new LifeProblemCommentCountRlue(answer.getFromuid());// 指被赞的评论uid
			RedisUtil.decr(rule);
		}
		uids.add(uid);
		updatemap.put(MapperDict.uidlist, uids);
		updatemap.put(MapperDict.status, EStatus.deleted);
		getSO().updateFields(updatemap);
		AnswerHP.deleteAnswerFile(answer.getFromuid());
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
			if (ELifeChannel.get(channel) == ELifeChannel.problempraise) {
				rule = new LifeProblemPrizeCountRlue(fromuid);
				return RedisUtil.decr(rule) >= 0;
			} else if (ELifeChannel.get(channel) == ELifeChannel.answerpraise) {
				rule = new PrizeCountAnswerLifeRule(fromuid);// 指被赞的评论uid
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
	public static void organized(List<Answer> list) {
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.start);
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.wait);
		map.put(MapperDict.statuslist, liststatus);
		List<User> users = getUserSO().getList(map);
		boolean isvisitor = true;
		Visitor visitor;
		for (Answer item : list) {
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
	 * @author ChenQi
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
	 * @author ChenQi
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
				visitor = new Visitor();
				visitor.setLoginip(ip);
				visitor.setCreatetime(new DateTime());
				temp = getRandomVisitor();
				if (temp != null) {
					visitor.setNickname(temp.getNickname());
					visitor.setAvatar(temp.getAvatar());
				}
				Boolean bool = getVisitorSO().add(visitor);
				if (bool) {
					CookieUtil.addCookie(response, ViewKeyDict.visitors, visitor.getUid(), Integer.MAX_VALUE);
				}
			}
		} else {
			String ip = NetUtil.getIPAddr(request);
			visitor = new Visitor();
			visitor.setLoginip(ip);
			visitor.setCreatetime(new DateTime());
			temp = getRandomVisitor();
			if (temp != null) {
				visitor.setNickname(temp.getNickname());
				visitor.setAvatar(temp.getAvatar());
			}
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
		visitor = list.get((int)(Math.random() * list.size()));
		return visitor;
	}
	
	/**
	 * 获取用户是否点赞了该文章
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static boolean isPrise(String problemUid, String useruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, problemUid);
		map.put(MapperDict.fromuseruid, useruid);
		map.put(MapperDict.type, EComment.praise);
		Answer answer = getSO().getOne(map);
		if (answer != null) {
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
	 * @return List<Answer>
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Answer> getAnswerHot(int pagenum, int pagesize, Long cid, String fromuid) {
		String path = SysConf.SerialBasePath + "/life/" + fromuid + SysConf.SerialAnswerPath;
		List<Answer> result = new ArrayList<>();
		List<Answer> list = new ArrayList<>();
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
			List<Answer> tempList = new ArrayList<>();
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
	public static void deleteAnswerFile(String fromuid) {
		FileUtil.delete(SysConf.SerialBasePath + "/life/" + fromuid + SysConf.SerialAnswerPath);
	};
	
	/**
	 * 获得专家点评
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static Answer getMajor(String fromuid) {
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
	public static List<Answer> getSecondMajor(Problem problem) {
		List<Answer> list = new ArrayList<Answer>();
		Map<String, Object> majormap = getParamMap();
		majormap.put(MapperDict.type, EComment.major);
		majormap.put(MapperDict.fromuseruid, ViewKeyDict.defaultuid);
		majormap.put(MapperDict.fromuid, problem.getUid());
		majormap.put(MapperDict.touseruid, problem.getUseruid());
		majormap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Answer> majorlist = getSO().getList(majormap);
		Map<String, Object> usermap = getParamMap();
		usermap.put(MapperDict.type, EComment.major);
		usermap.put(MapperDict.fromuseruid, problem.getUseruid());
		usermap.put(MapperDict.fromuid, problem.getUid());
		usermap.put(MapperDict.touseruid, ViewKeyDict.defaultuid);
		usermap.put(MapperDict.touid, ViewKeyDict.defaultuid);
		List<Answer> userlist = getSO().getList(usermap);
		list.addAll(majorlist);
		list.addAll(userlist);
		for (Answer answer : list) {
			answer.setIsmajor(1);
		}
		list.sort(COMPARATOR);
		return list;
	};
	
	private static final Comparator<Answer> COMPARATOR = new Comparator<Answer>() {
		public int compare(Answer o1, Answer o2) {
			int i = o1.compareTo(o2);
			return i;//
		}
	};
}
