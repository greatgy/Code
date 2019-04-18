package com.supergenius.web.front.solr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.front.solr.helper.IndexHP;
import com.supergenius.xo.solr.entity.Terms;
import com.supergenius.xo.solr.service.TermsSO;

/**
 * 搜索controller
 * 
 * @author 杨光
 * @date 2017年9月20日13:37:12
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	TermsSO termSO;

	/**
	 * 查询
	 * 
	 * @param model
	 * @param channel
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String channel_search(Map<String, Object> model, String target, String content, String uid, String channel, HttpServletRequest request) {
		Terms Terms = null;
		if (StrUtil.isNotEmpty(uid)) {
			Terms = termSO.get(uid);
		}
		if (Terms == null && StrUtil.isNotEmpty(content)) {
			Terms = IndexHP.getTermByContent(content);
		}
		Terms.setCount(Terms.getCount() + 1);
		termSO.update(Terms);
		model.put(ViewKeyDict.list, IndexHP.getHotLabel(SysConf.HotLabelSize));// 获得热门标签
		model.put(ViewKeyDict.ADContent, IndexHP.getADContent());// 获得广告位
		model.put(ViewKeyDict.searchkeyword, content);
		model.put(ViewKeyDict.SearchUrl, WebConf.SearchUrl);
		if (StrUtil.isNotEmpty(target) && target.equals(ViewKeyDict.author)) {
			model.put(ViewKeyDict.author, content);
		}
		if (!"all".equals(channel) && StrUtil.isNotEmpty(channel)) {
			model.put(ViewKeyDict.channel, channel);
		}
		return "result";
	}

	/**
	 * 首页
	 * 
	 * @param model
	 * @param channel
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.ADContent, IndexHP.getADContent());// 获得广告位
		model.put(ViewKeyDict.list, IndexHP.getHotLabel(SysConf.HotLabelSize));// 获得热门标签
		return "index";
	}
}