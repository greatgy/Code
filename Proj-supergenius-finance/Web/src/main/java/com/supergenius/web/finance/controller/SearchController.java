package com.supergenius.web.finance.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.finance.util.FinanceLabelRedisUtil;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.service.LabelSO;

/**
 * 搜索controller
 * 
 * @author 杨光
 * @date 2017年9月20日13:37:12
 */
@Controller
public class SearchController extends BaseController{
	
	@Autowired
	LabelSO labelSO;
	
	/**
	 * 查询
	 * 
	 * @param model
	 * @param channel
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Map<String, Object> model, String keyword, String uid, HttpServletRequest request){
		if (StringUtils.isNotEmpty(uid)) {
			FinanceLabelRedisUtil.incr(uid);
			FinanceLabelRedisUtil.incr(uid, ViewKeyDict.LabelClickCount);
			Label label = labelSO.get(uid);
			int count = label.getCount() + SysConf.ClickLable;
			label.setCount(count);
			labelSO.update(label);
		}
		String content = "";
		try {
			content = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String channel = ESite.finance.name();
		return redirectPrefix + WebConf.SolrSearchUrl + "/search?content=" + content + "&uid=" + uid + "&channel=" + channel;
	}
}
