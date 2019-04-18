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
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.MessageHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Message;
import com.supergenius.xo.tpi.enums.EMsg;
import com.supergenius.xo.tpi.service.MessageSO;

/**
 * 消息管理控制器
 * 
 * @author LiuXiaoke
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MessageAdminer extends BaseController {
	
	@Autowired
	MessageSO messageSO;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String message(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.message.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.message));
		Map<String, String> channelMap = new HashMap<>();
		for (EMsg item : EMsg.values()) {
			channelMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, channelMap);
		return "domessage";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author LiuXiaoke
	 */
	@RequestMapping(value = {"/ajax/message/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> message_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = MessageHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加消息
	 * @param article
	 * @param file
	 * @return
	 * @author Liuxiaoke
	 */
	@RequestMapping(value = "/ajax/message/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> message_add(Message message) {
		message.setCreatetime(DateTime.now());
		message.setType(EMsg.sysmsg);
		message.setFromuseravatar(SysConf.TpiSysMsgImg);
		if (messageSO.add(message)) {
			return result(MsgKeyDict.addSuccess);
		}else {
			return result(MsgKeyDict.addFailed);
		}
	}
	
	/**
	 * 保存编辑信息
	 * @param article
	 * @param file
	 * @return
	 * @author Liuxiaoke
	 */
	@RequestMapping(value = "/ajax/message/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> message_edit(Message message) {
		if (messageSO.update(message)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 删除消息
	 * @param ids
	 * @return
	 * @author Liuxiaoke
	 */
	@RequestMapping(value = "/ajax/message/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> message_delete(String[] ids) {
		if(messageSO.delete(ids)){
			return success();
		}else{
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
}
