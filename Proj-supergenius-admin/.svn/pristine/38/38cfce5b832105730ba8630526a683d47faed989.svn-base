package com.supergenius.web.admin.tpi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
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
import com.supergenius.web.admin.tpi.helper.EmailTemplateHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.EmailTemplate;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.tpi.service.EmailTemplateSO;

/**
 * 模板管理控制器
 * 
 * @author LiuXiaoke
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EmailTemplateAdminer extends BaseController {
	
	@Autowired
	EmailTemplateSO so;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/emailtemplate", method = RequestMethod.GET)
	public String emailtemplate(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.emailtemplate.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.emailtemplate));
		Map<String, String> typeMap = new HashMap<>();
		for (ESysEmailType item : ESysEmailType.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, typeMap);
		return "doemailtemplate";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = {"ajax/emailtemplate/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> emailtemplate_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EmailTemplateHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加模板
	 * @param article
	 * @param file
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/ajax/emailtemplate/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> emailtemplate_add(EmailTemplate emailTemplate, String file) {
		emailTemplate.setUpdatetime(DateTime.now());
		emailTemplate.setCreatetime(DateTime.now());
		if (emailTemplate.getType() != null) {
			emailTemplate.setTypename(emailTemplate.getType().getName());	
		}
		if (so.add(emailTemplate)) {
			return result(MsgKeyDict.addSuccess);
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}
	
	/**
	 * 保存编辑文章
	 * @param article
	 * @param file
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/ajax/emailtemplate/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> emailtemplate_edit(EmailTemplate emailTemplate, String file) {
		if (emailTemplate.getType() != null) {
			emailTemplate.setTypename(emailTemplate.getType().getName());	
		}
		if (so.update(emailTemplate)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 删除文章
	 * @param ids
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/ajax/emailtemplate/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> emailtemplate_delete(String[] ids) {
		if(so.delete(ids)){
			return success();
		}else{
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
}
