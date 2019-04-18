package com.supergenius.web.admin.career.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.supergenius.web.admin.career.helper.CareerArticleHP;
import com.supergenius.xo.career.enums.ECatalogue;
import com.supergenius.xo.career.service.ArticleSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 职场心得管理
 * 
 * @author 杨光
 * @date 2017年9月19日11:35:16
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ExperienceArticleAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;

	/**
	 * 职场心得管理页面
	 * 
	 * @author 杨光
	 * @date 2017年11月14日11:44:53
	 * @return String
	 */
	@RequestMapping(value = "/career/experiencearticle", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerarticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerarticle, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.cid, ECatalogue.experience);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.catelogueList, CareerArticleHP.getCatalogueList());
		model.put(ViewKeyDict.photopath, SysConf.CareerPhotoPath);
		return "doexperiencearticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/experiencearticle/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CareerArticleHP.query(model, ECatalogue.experience);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 获取置顶的个数
	 * 
	 * @author yangguang
	 * @date 2017年11月14日12:38:14
	 */
	@RequestMapping(value = "/career/ajax/experiencearticle/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> problem_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, CareerArticleHP.getTopArticleCount(ECatalogue.experience));
		return json(model, Json.webStrategy);
	}
}
