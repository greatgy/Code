package com.supergenius.web.front.life.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.WebConf;
import com.supergenius.xo.common.enums.ESite;

/**
 * 搜索controller
 * 
 * @author 杨光
 * @date 2017年9月20日13:37:12
 */
@Controller
public class SearchController extends BaseController{
	
	/**
	 * 查询
	 * 
	 * @param model
	 * @param channel
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Map<String, Object> model, String keyword, HttpServletRequest request){
		String content = "";
		try {
			content = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String channel = ESite.life.name();
		return redirectPrefix + WebConf.SolrSearchUrl + "/search?content=" + content + "&channel=" + channel;
	}
}
