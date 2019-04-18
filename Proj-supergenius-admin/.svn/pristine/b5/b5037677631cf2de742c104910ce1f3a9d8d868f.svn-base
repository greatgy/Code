package com.supergenius.web.admin.tpi.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.WishHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * 并购方案管理 && 投资计划管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class WishAdminer extends BaseController{
	
	/**
	 * 进入管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/wish/{type:\\d+}"}, method = RequestMethod.GET)
	public String mprogram(Map<String, Object> model, @PathVariable String type) {
		model.put(ViewKeyDict.type, EWishType.get(type));
		model.put(ViewKeyDict.typename, EWishType.getName(EWishType.get(type),  Locale.CHINA));
		model.put(ViewKeyDict.channel, EChannel.wishdetail.name());
		return "dowish";
	}
	
	/**
	 * 查询组织语句
	 * @param model
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"ajax/wish/list/{type:\\d+}"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> mprogram_list(Map<String, Object> model, @PathVariable String type, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		model.put(ViewKeyDict.type, EWishType.get(type));
		Map<String, Object> searchMap = WishHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

}
