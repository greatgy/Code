package com.supergenius.web.front.life.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.life.util.TopicRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.enums.ECollectType;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

public class TopicHP extends BaseHP {

	private static TopicSO so;

	public static TopicSO getSO() {
		if (so == null) {
			so = (TopicSO) spring.getBean(TopicSO.class);
		}
		return so;
	}

	/**
	 * 通过oid获取话题
	 * 
	 * @param id
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:42:31
	 */
	public static Topic getTopic(int id) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.oid, id);
		return getSO().getOne(map);
	}
	/**
	 * 通过uid获取话题
	 *
	 * @param uid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:42:31
	 */
	public static Topic getOneTopic(String uid) {
		return getSO().get(uid);
	}

	/**
	 * 增加点击计数
	 * 
	 * @param uid
	 * @param user
	 * @return
	 * @author 2017年8月29日14:45:35
	 */
	public static void incrClickCount(HttpServletRequest request, HttpServletResponse response, String uid) {
		boolean isfirst = true;
		Cookie cookie = CookieUtil.getCookie(request, uid);
		if (cookie != null) {
			isfirst = false;
		}
		if (isfirst) {
			TopicRedisUtil.incr(uid, ViewKeyDict.clickcount);
			CookieUtil.addCookieSess(response, uid, EChannel.finance.name());
		}
	}

	/**
	 * 获取往期话题
	 * 
	 * @param pagesize
	 * @param pagenum
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日12:30:22
	 */
	public static List<Topic> getRelateTopic(int pagenum, int pagesize, Long cid, Integer isreview) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		if (isreview != null && isreview != 0) {
			map.put(MapperDict.examine, false);
		} else {
			map.put(MapperDict.examine, true);
		}
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Topic> list = getSO().getList(map);
		initTopicList(list);
		return list;
	}

	/**
	 * 获取话题list
	 * 
	 * @param pagesize
	 * @param pagenum
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日12:30:27
	 */
	public static List<Topic> getTopics(Long cid, int pagenum, int pagesize) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Topic> list = getSO().getList(map);
		initTopicList(list);
		return list;
	}

	/**
	 * 按热度获取话题
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	public static List<Topic> getTeaseHot(int pagenum, int pagesize, Long cid) {
		String path = SysConf.SerialBasePath + SysConf.SerialTopicsPath;
		List<Topic> result = new ArrayList<>();
		List<Topic> topiclist = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			topiclist = SerialUtil.deserializeFromJson(path, topiclist.getClass(), Json.cacheStrategy);
		}
		if (topiclist.size() == 0) {
			Map<String, Object> map = getParamMap();
			map.put(MapperDict.cid, cid);
			map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
			topiclist = getSO().getList(map);
			List<Topic> tempList = new ArrayList<>();
			tempList.addAll(topiclist);
			SerialUtil.serializeToJson(tempList, path, Json.cacheStrategy);
		}
		initTopicList(topiclist);
		topiclist.sort(COMPARATOR);
		int maxHotsize = pagenum * pagesize;
		if (maxHotsize > topiclist.size()) {
			maxHotsize = topiclist.size();
		}
		result = topiclist.subList((pagenum - 1) * pagesize, maxHotsize);
		return result;
	}

	/**
	 * 删除序列化的话题
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static void deleteTopic() {
		FileUtil.delete(SysConf.SerialBasePath + SysConf.SerialTopicsPath);
	};

	private static final Comparator<Topic> COMPARATOR = new Comparator<Topic>() {
		public int compare(Topic o1, Topic o2) {
			int i = o1.compareTo(o2);
			return i;// 运用Tease类的compareTo方法比较两个对象
		}
	};

	/**
	 * 初始化话题
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日14:13:51
	 * @return boolean
	 */
	public static boolean initTopicList(List<Topic> list) {
		boolean flag = true;
		String uid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		for (Topic topic : list) {
			uid = topic.getUid();
			if (TopicRedisUtil.isInit(uid)) {
				topic.setClickcount(TopicRedisUtil.getInt(uid, ViewKeyDict.clickcount));
				topic.setCollectcount(TopicRedisUtil.getInt(uid, ViewKeyDict.collectcount));
				topic.setCommentscount(TopicRedisUtil.getInt(uid, ViewKeyDict.commentscount));
				topic.setPrizecount(TopicRedisUtil.getInt(uid, ViewKeyDict.prizecount));
				topic.setWeight(getWeight(uid));
			} else {
				map.clear();
				map.put(ViewKeyDict.clickcount, 0);
				map.put(ViewKeyDict.collectcount, 0);
				map.put(ViewKeyDict.commentscount, 0);
				map.put(ViewKeyDict.prizecount, 0);
				flag = TopicRedisUtil.set(uid, map);
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算权重值
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月16日14:16:58
	 * @return Float
	 */
	public static Float getWeight(String uid) {
		Map<String, Float> map = new HashMap<String, Float>();
		map.put(ViewKeyDict.clickcount, SysConf.ClickcountPercent);
		map.put(ViewKeyDict.collectcount, SysConf.CollectcountPercent);
		map.put(ViewKeyDict.commentscount, SysConf.CommentscountPercent);
		map.put(ViewKeyDict.prizecount, SysConf.PrizecountPercent);
		return TopicRedisUtil.getWeight(uid, map);
	}
	
	/**
	 * 获取备选话题
	 * 
	 * @param pagesize
	 * @param pagenum
	 * @return
	 * @author YangGuang
	 * @date 2018年5月22日11:08:08
	 */
	public static List<Topic> getReview(int pagenum, int pagesize, Long cid) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.examine, false);
		map.put(MapperDict.cid, cid);
		List<Topic> list = getSO().getList(map);
		initTopicList(list);
		list.sort(COMPARATOR);
		return list;
	}
	
	/**
	 * 获取手机端思维拓展数据
	 * 
	 * @param pagesize
	 * @param pagenum
	 * @return
	 * @author YangGuang
	 * @date 2018年5月22日11:08:08
	 */
	public static List<Topic> getThinking(int pagenum, int pagesize, Long cid) {
		Pager pager = Pager.getNewInstance(pagenum, pagesize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.examine, true);
		map.put(MapperDict.cid, cid);
		List<Topic> list = getSO().getList(map);
		initTopicList(list);
		list.sort(COMPARATOR);
		return list;
	}
	
	/**
	 * 获取置顶的一个话题
	 * 
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月22日11:54:00
	 */
	public static Topic getTopTopic(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.examine, true);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.istop + MapperDict.desc + MapperDict.comma + MapperDict.toptime + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		return getSO().getOne(map);
	}
	
	/**
	 * 获取最新的的一个话题
	 * 
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月22日11:54:00
	 */
	public static Topic getlastTopic(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.examine, true);
		Topic topic = getSO().getOne(map);
		return topic;
	}
	
	/**
	 * 获取最热的的一个话题
	 * 
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月23日14:26:05
	 */
	public static Topic getHotTopic(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.cid, cid);
		map.put(MapperDict.examine, true);
		//map.put(MapperDict.time, 30);
		List<Topic> list = getSO().getList(map);
		initTopicList(list);
		list.sort(COMPARATOR);
		return list.get(0);
	}
	
	/**
	 * 话题详情页组织数据
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	public static Topic organized(int oid, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		Topic topic = getTopic(oid);
		if (user != null) {// 设置是否赞过
			if (CommentsHP.isPrise(topic.getUid(), user.getUid())) {
				topic.setIsprize(true);
			}
			topic.setIscollect(CollectHP.isCollect(user.getUid(), topic.getUid(), ECollectType.topic));// 是否收藏过
		} else {
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (CommentsHP.isPrise(topic.getUid(), visitor.getUid())) {
				topic.setIsprize(true);
			}
		}
		incrClickCount(request, response, topic.getUid());// 增加计数
		topic.setClickcount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.clickcount));
		topic.setCollectcount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.collectcount));
		topic.setPrizecount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.prizecount));
		topic.setCommentscount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.commentscount));
		return topic;
	}
}
