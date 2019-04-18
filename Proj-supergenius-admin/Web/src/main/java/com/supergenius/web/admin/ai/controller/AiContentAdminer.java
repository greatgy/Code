package com.supergenius.web.admin.ai.controller;

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
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.ai.helper.ContentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.ai.entity.Content;
import com.supergenius.xo.ai.enums.EContent;
import com.supergenius.xo.ai.service.ContentSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 内容管理controller
 * 
 * @author ChenQi
 * @date 2017年8月23日17:35:38
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AiContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ai/aicontent" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.aicontent.name());
		model.put(ViewKeyDict.site, ESite.ai.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.aicontent, Locale.CHINA));
		return "doaicontent";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/ai/ajax/aicontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加内容
	 * 
	 * @author xuzhixiang
	 * @date 2017年8月23日19:18:30
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/ai/ajax/aicontent/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow,
			String type, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl) && StrUtil.isNotEmpty(isshow)
				&& StrUtil.isNotEmpty(type)) {
			Content content = new Content();
			content.setName(name);
			content.setOriginurl(originurl);
			content.setStatus(EStatus.get(isshow));
			content.setType(EContent.get(type));
			content.setContent(imgdata[2]);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.aicontent.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addAiContent.getName());
			adminLog.setData(EAdminLog.addAiContent.getName());
			adminLog.setOperation(EAdminLog.addAiContent.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑题目
	 * 
	 * @author xuzhixiang
	 * @date 2017年9月19日11:29:24
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/ai/ajax/aicontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, String uid, String name, String originurl,
			String isshow, String type, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl)
				&& StrUtil.isNotEmpty(isshow) && StrUtil.isNotEmpty(type)) {
			Content content = so.get(uid);
			if (content != null) {
				content.setName(name);
				content.setOriginurl(originurl);
				content.setStatus(EStatus.get(isshow));
				content.setType(EContent.get(type));
				if (StrUtil.isEmpty(imgdata) || imgdata == null) {
					content.setContent(content.getContent());
				} else {
					content.setContent(imgdata[2]);
				}
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					content.setAdminuid(AdminHP.getAdminUid());
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.aicontent.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.addAiContent.getName());
				adminLog.setData(EAdminLog.updateAiContent.getName());
				adminLog.setOperation(EAdminLog.updateAiContent.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author xuzhixiang
	 * @typename Map<String,Object>
	 * @date 2017年9月19日11:30:04
	 */
	@RequestMapping(value = "/ai/ajax/aicontent/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			if (so.updateStatusByUid(uid)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
}
