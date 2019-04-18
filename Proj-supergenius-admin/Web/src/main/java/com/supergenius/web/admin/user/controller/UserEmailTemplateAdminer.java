package com.supergenius.web.admin.user.controller;

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
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.user.helper.EmailTemplateHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/**
 * 系统邮件模板管理
 * 
 * @author XieMing
 * @date 2016-5-19 上午11:47:45
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class UserEmailTemplateAdminer extends BaseController {

	@Autowired
	ContentSO so;

	/**
	 * 跳转到列表页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-19 下午12:00:15
	 */
	@RequestMapping(value = "/user/emailtemplate", method = RequestMethod.GET)
	public String emailtemplate(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.emailtemplate.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.emailtemplate, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.user.name());
		Map<String, String> typeMap = EContent.getEmailEnum();
		model.put(ViewKeyDict.map, typeMap);
		return "douseremailtemplate";
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-19 下午12:02:07
	 */
	@RequestMapping(value = { "/user/ajax/emailtemplate/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> emailtemplate_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EmailTemplateHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加模板
	 * 
	 * @param content
	 * @param file
	 * @return
	 * @author XieMing 2016-5-19 下午5:28:43
	 */
	@RequestMapping(value = "/user/ajax/emailtemplate/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> emailtemplate_add(Content content, String file) {
		if (so.add(content)) {
			return result(MsgKeyDict.addSuccess);
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 编辑模板
	 * 
	 * @param content
	 * @param file
	 * @return
	 * @author XieMing 2016-5-19 下午5:28:56
	 */
	@RequestMapping(value = "/user/ajax/emailtemplate/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> emailtemplate_edit(Content content, String file) {
		if (so.update(content)) {
			return result(MsgKeyDict.editSuccess);
		} else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 删除模板
	 * 
	 * @param ids
	 * @return
	 * @author XieMing 2016-5-19 下午5:18:25
	 */
	@RequestMapping(value = "/user/ajax/emailtemplate/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> emailtemplate_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		} else {
			return result(MsgKeyDict.deleteFailed);
		}
	}

}
