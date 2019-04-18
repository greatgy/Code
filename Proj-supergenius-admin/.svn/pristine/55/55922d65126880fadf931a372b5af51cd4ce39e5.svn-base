package com.supergenius.web.admin.ai.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.ai.helper.AiArticleHP;
import com.supergenius.xo.ai.service.ArticleSO;
import com.supergenius.xo.ai.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 文章管理
 * 
 * @author 杨光
 * @date 2017年9月19日11:35:16
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AiContributeAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;
	
	@Autowired
	private CatalogueSO catalogueSO;

	/**
	 * 投稿管理页面
	 * 
	 * @author 杨光
	 * @date 2017年9月21日18:42:45
	 * @return String
	 */
	@RequestMapping(value = "/ai/aicontribute", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.aiarticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.aiarticle, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.ai.name());
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.catagory, EStatus.disable);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.AiPhotoPath);
		model.put(ViewKeyDict.catelogueList, catalogueSO.getList()); // 获取目录列表
		return "doaicontribute";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/ai/ajax/aicontribute/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AiArticleHP.query(model, EStatus.disable);
		return json(searchMap, Json.webStrategy);
	}
}
