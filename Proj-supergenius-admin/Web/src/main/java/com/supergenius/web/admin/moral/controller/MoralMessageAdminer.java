package com.supergenius.web.admin.moral.controller;

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
import com.supergenius.web.admin.moral.helper.MessageHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.service.MessageSO;

/**
 * 系统消息管理
 * @author YuYingJie
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralMessageAdminer extends BaseController {
	
	@Autowired
	MessageSO so;
	
	/**
	 * 打开系统消息管理
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = "/moral/moralmessage", method = RequestMethod.GET)
	public String message(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.moralmessage.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.moralmessage, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		return "domoralmessage";
	}
	
	/**
	 * 查询组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = {"/moral/ajax/moralmessage/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> message_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = MessageHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 发送消息
	 * @param message
	 * @param who
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = "/moral/ajax/moralmessage/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> message_add(Message message, EUser who){
		if (who == null) {
			return result(MsgKeyDict.addFailed);
		}
		if (MessageHP.sendSysMsg(message, who)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 系统消息删除
	 * @param ids
	 * @return
	 * @author YuYingJie
	 */
	@RequestMapping(value = { "/moral/ajax/moralmessage/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> message_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
}
