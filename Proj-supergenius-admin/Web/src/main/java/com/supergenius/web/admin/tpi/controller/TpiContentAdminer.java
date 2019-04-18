package com.supergenius.web.admin.tpi.controller;

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
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.TpiContentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.TpiContent;
import com.supergenius.xo.tpi.service.TpiContentSO;

/**
 * 网站内容管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class TpiContentAdminer extends BaseController{
	
	@Autowired
	TpiContentSO so;
	
	/**
	 * 进入content管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/tpicontent"}, method = RequestMethod.GET)
	public String tpicontent(Map<String, Object> model) {
		model.put(ViewKeyDict.enums, TpiContentHP.getEContent());
		model.put(ViewKeyDict.channel, EChannel.tpicontent.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.tpicontent, Locale.CHINA));
		return "dotpicontent";
	}
	
	/**
	 * 查询组织数据
	 * @param model
	 * @param request
	 * @param type
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"ajax/tpicontent/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tpicontent_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = TpiContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加content
	 * @param tpiContent
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/tpicontent/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpicontent_add(TpiContent tpiContent) {
		tpiContent.setAdminuid(AdminHP.getAdminUid());
		if (so.add(tpiContent)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 编辑
	 * @param tpiContent
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/tpicontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpicontent_edit(TpiContent tpiContent) {
		TpiContent con	= so.get(tpiContent.getUid());
		con.setAdminuid(AdminHP.getAdminUid());
		con.set(tpiContent);
		if (so.updateFields(con)) {
			return success();
		} else {
			return result(MsgKeyDict.updateFailed);
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/tpicontent/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tpicontent_delete(String[] ids) {
		so.delete(ids);
		return success();
	}


}
