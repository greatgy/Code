package com.supergenius.web.admin.enterpriser.controller;

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
import com.supergenius.web.admin.enterpriser.hellper.PhotoHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.service.ContentSO;

/**
 * 引资购商图片管理
 * 
 * @author YangGuang
 * @date 2016-10-28 下午12:33:57
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserPhotoAdminer extends BaseController {

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
	@RequestMapping(value = "/enterpriser/enterpriserphoto", method = RequestMethod.GET)
	public String enterprisercontent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterpriserphoto.name());
		model.put(ViewKeyDict.channelname, EChannel.enterpriserphoto.getName());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.photopath, SysConf.EnterpriserPhotoPath);
		return "doenterpriserphoto";
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
	@RequestMapping(value = { "/enterpriser/ajax/enterpriserphoto/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = PhotoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 修改引资购商图片
	 * 
	 * @param content
	 * @param uid
	 * @return
	 * @author YangGuang
	 * @createtime 2018年1月30日16:09:06
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserphoto/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> photo_edit(String title, String[] imgdata, String oid) {
		if (StrUtil.isEmpty(title)) {
			return result(MsgKeyDict.updateFailed);
		}
		String content = "";
		if (StrUtil.isNotEmpty(imgdata)) {
			content = imgdata[2];
		}
		Content entity = PhotoHP.update(title, content, oid);
		if(so.update(entity)){
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}
}
