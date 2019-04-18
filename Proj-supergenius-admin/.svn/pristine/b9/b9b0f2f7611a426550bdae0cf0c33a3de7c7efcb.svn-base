package com.supergenius.web.admin.career.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.BannerHP;
import com.supergenius.xo.career.entity.Content;
import com.supergenius.xo.career.service.ContentSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * banner管理
 * 
 * @author YangGuang
 * @date 2018年3月6日16:55:03
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerBannerAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 内容页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 * @createtime 2018年1月30日16:01:56
	 * @return String
	 */
	@RequestMapping(value = "/career/careerbanner", method = RequestMethod.GET)
	public String enterprisercontent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerbanner.name());
		model.put(ViewKeyDict.channelname, EChannel.careerbanner.getName());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.photopath, SysConf.CareerPhotoPath);
		return "docareerbanner";
	}

	/**
	 * 查询数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 * @createtime 2018年1月30日16:06:33
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = { "/career/ajax/careerbanner/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = BannerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 修改banner
	 * 
	 * @param content
	 * @param uid
	 * @return
	 * @author YangGuang
	 * @createtime 2018年1月30日16:09:06
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/career/ajax/careerbanner/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> photo_edit(String title, String url, String[] imgdata, String oid) {
		if (StrUtil.isEmpty(title)) {
			return result(MsgKeyDict.updateFailed);
		}
		String content = "";
		if (StrUtil.isNotEmpty(imgdata)) {
			content = imgdata[0];
		}
		Content entity = BannerHP.update(title, content, url, oid);
		if(so.update(entity)){
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}
}
