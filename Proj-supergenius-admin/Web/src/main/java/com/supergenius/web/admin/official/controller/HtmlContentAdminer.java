package com.supergenius.web.admin.official.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.official.helper.HtmlContentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.official.entity.Content;
import com.supergenius.xo.official.enums.EType;
import com.supergenius.xo.official.service.ContentSO;

/**
 * 网站内容管理管理控制器
 * 
 * @author LiuXiaoke
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class HtmlContentAdminer extends BaseController {
	
	@Autowired
	ContentSO ContentSO;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/html/content", method = RequestMethod.GET)
	public String htmlcontent(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.htmlcontent);
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.htmlcontent));
		Map<String, String> typeMap = new HashMap<>();
		for (EType item : EType.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, typeMap);
		return "dohtmlcontent";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = {"/ajax/html/content/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> htmlcontent_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = HtmlContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	/**
	 * 添加文章
	 * @param article
	 * @param file
	 * @return 
	 * @author liuwenhao
	 */
	@RequestMapping(value = "/ajax/html/content/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(Content content,HttpServletRequest request) {
		if (ContentSO.add(content)) {
		
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	
	
	/**
	 * 保存编辑信息
	 * @param content
	 * @param file
	 * @return 
	 * @author Liuxiaoke
	 */
	@RequestMapping(value = "/ajax/html/content/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> htmlcontent_edit(Content content) {
		if (ContentSO.update(content)) {
			return result(MsgKeyDict.editSuccess);
		} else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
}
