package com.supergenius.web.front.life.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.life.util.TopicRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.CommentsHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.web.front.life.helper.TopicHP;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 首页controller
 * 
 * @author: YangGuang
 * @date 2018年5月16日12:03:43
 */
@Controller
public class TopicController extends BaseController {

	/**
	 * 话题查看详情页面
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:41:09
	 */
	@RequestMapping(value = "/topic/{cid:\\d+}/{oid:\\d+}", method = RequestMethod.GET)
	public String topic_detial(Map<String, Object> model, @PathVariable Long cid, @PathVariable int oid, HttpServletRequest request, HttpServletResponse response) {
		Topic topic = TopicHP.organized(oid, request, response);
		List<Topic> relateList = TopicHP.getRelateTopic(0, SysConf.RelateSize, cid, 0);
		Catalogue catalogue = IndexHP.getOneCatalogue(cid);
		long pcid=catalogue.getPcid();
		model.put(ViewKeyDict.bean, topic);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.relateList, relateList);
		if (ECatalogue.get(cid) == ECatalogue.thinking) {
			List<Topic> reviewList = TopicHP.getReview(0, SysConf.ReviewFirstSize, cid);
			Topic topTopic = TopicHP.getTopTopic(cid);
			model.put(ViewKeyDict.topic, topTopic);
			model.put(ViewKeyDict.reviewList, reviewList);
			return "thinking";
		}
		return "topicdetail";
	}
	
	/**
	 * 备选话题查看详情页面
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日14:41:09
	 */
	@RequestMapping(value = "/topic/review/{cid:\\d+}/{oid:\\d+}", method = RequestMethod.GET)
	public String topic_review(Map<String, Object> model, @PathVariable Long cid, @PathVariable int oid, HttpServletRequest request, HttpServletResponse response) {
		Topic topic = TopicHP.getTopic(oid);
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {// 设置是否赞过
			if (CommentsHP.isPrise(topic.getUid(), user.getUid())) {
				topic.setIsprize(true);
			}
		} else {
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (CommentsHP.isPrise(topic.getUid(), visitor.getUid())) {
				topic.setIsprize(true);
			}
		}
		TopicHP.incrClickCount(request, response, topic.getUid());// 增加计数
		topic.setClickcount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.clickcount));
		topic.setPrizecount(TopicRedisUtil.getInt(topic.getUid(), ViewKeyDict.prizecount));
		List<Topic> relateList = TopicHP.getRelateTopic(0, SysConf.RelateSize, cid, 1);
		Catalogue catalogue = IndexHP.getOneCatalogue(cid);
		long pcid=catalogue.getPcid();
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.bean, topic);
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.relateList, relateList);
		return "reviewdetail";
	}
	
	/**
	 * 备选话题頁
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/topic/review/moretopic", method = RequestMethod.GET)
	public String more_reviewTopic(Map<String, Object> model, Long cid, HttpServletRequest request) {
		List<Topic> list = TopicHP.getReview(1, SysConf.ReviewTopicSize, cid);
		List<Topic> relateList = TopicHP.getRelateTopic(0, SysConf.RelateSize, cid, 0);
		Topic topTopic = TopicHP.getTopTopic(cid);
		model.put(ViewKeyDict.relateList, relateList);
		model.put(ViewKeyDict.topic, topTopic);
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "reviewtopic";
	}
	
	/**
	 * 按照话题热度加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/hottopic", method = RequestMethod.GET)
	public String ajax_hotTopic(Map<String, Object> model, int pagenum, Long cid, HttpServletRequest request) {
		List<Topic> list = TopicHP.getTeaseHot(pagenum, SysConf.TopicLoadSize, cid);
		model.put(ViewKeyDict.list, list);
		return "ajaxtopic";
	} 
	
	/**
	 * 加载更多话题
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/moretopic", method = RequestMethod.GET)
	public String ajax_moreTopic(Map<String, Object> model, int pagenum, Long cid, HttpServletRequest request) {
		List<Topic> list = TopicHP.getTopics(cid, pagenum, SysConf.TopicLoadSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxtopic";
	}
	
	/**
	 * 往期话题换一批
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/relatetopic", method = RequestMethod.GET)
	public String ajax_relateTopic(Map<String, Object> model, int pagenum, Long cid, Integer isreview, HttpServletRequest request) {
		List<Topic> list = TopicHP.getRelateTopic(pagenum, SysConf.RelateSize, cid, isreview);
		model.put(ViewKeyDict.list, list);
		return "ajaxrelatetopic";
	}
	
	/**
	 * 加载更多备选话题
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/review/moretopic", method = RequestMethod.GET)
	public String ajax_reviewTopic(Map<String, Object> model, int pagenum, Long cid, HttpServletRequest request) {
		List<Topic> list = TopicHP.getReview(pagenum, SysConf.ReviewTopicSize, cid);
		model.put(ViewKeyDict.list, list);
		return "ajaxreviewtopic";
	}
	
	/**
	 * 加载思维拓展最热的一篇
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/hotthinking", method = RequestMethod.GET)
	public String ajax_hotTopic(Map<String, Object> model, Long cid, HttpServletRequest request, HttpServletResponse response) {
		Topic topic = TopicHP.getHotTopic(cid);
		topic = TopicHP.organized(topic.getOid(), request, response);
		model.put(ViewKeyDict.bean, topic);
		return "ajaxthinking";
	}
	
	/**
	 * 加载思维拓展最新的一篇
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/topic/lastthinking", method = RequestMethod.GET)
	public String ajax_lastTopic(Map<String, Object> model, Long cid, HttpServletRequest request, HttpServletResponse response) {
		Topic topic = TopicHP.getlastTopic(cid);
		topic = TopicHP.organized(topic.getOid(), request, response);
		model.put(ViewKeyDict.bean, topic);
		return "ajaxthinking";
	}
}
