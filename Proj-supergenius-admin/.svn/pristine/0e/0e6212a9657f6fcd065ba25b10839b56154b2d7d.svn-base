package com.supergenius.web.admin.manger.controller;

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
import com.genius.core.base.utils.WebUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.manager.helper.ContentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Content;
import com.supergenius.xo.manager.service.ContentSO;
import com.supergenius.xo.manager.service.MajorSO;

/** 
 * manager内容管理controller
 * @author chenminchang
 * @date 2016-11-2 下午4:03:32 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;
	@Autowired
	private MajorSO majorSO;
	@Autowired
	AdminLogSO adminLogSO;
	
	/**
	 * 跳转managerhtml内容管理
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午4:10:12
	 */
	@RequestMapping(value = "/manager/htmlcontent", method = RequestMethod.GET)
	public String manager_content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.htmlcontent.name());
		model.put(ViewKeyDict.channelname, EChannel.htmlcontent.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		return "domhtmlcontent";
	}
	
	/**
	 * 查询
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午4:43:04
	 */
	@RequestMapping(value = { "/manager/ajax/htmlcontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 编辑内容
	 * @param content
	 * @param uid
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午7:35:41
	 */
	@RequestMapping(value = { "/manager/ajax/htmlcontent/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(String content, String uid) {
		Content contentObj = so.get(uid);
		if (contentObj != null) {
			so.updateContent(uid, content);
		} else {
			String summary = "";
			if (StrUtil.isNotEmpty(content)) {
				String ctx = WebUtil.clearHtml(content).toString();
				if (ctx != null && ctx.length() > 150) {
					summary = ctx.substring(0, 150);
				} else {
					summary = ctx;
				}
			}
			majorSO.updateContent(uid, content, summary);
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.htmlcontent.toInt());
		adminLog.setOperation(EAdminLog.updateManagerContent.getName());
		adminLog.setData(EAdminLog.updateManagerContent.getName());
		adminLog.setDesc(EAdminLog.updateManagerContent.getName());
		adminLog.setDataid(uid);
		adminLogSO.add(adminLog);
		return success();
	}
	
}
