package com.supergenius.web.admin.tpi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.CommentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.service.CommentSO;

/**
 * 机构会员管理控制器
 * 
 * @author ShangJianguo
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CommentAdminer extends BaseController {
	
	@Autowired
	CommentSO so;
	
	/**
	 * 跳转到列表页
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request){
		model.put(ViewKeyDict.channel, EChannel.comment.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.comment));
		return "docomment";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = {"ajax/comment/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tpiuser_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CommentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/comment/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tpiuser_delete(String[] ids) {
		if(so.delete(ids)){
			return success();
		}else{
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
	/**
	 * 更新状态
	 * @param ids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/comment/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpiuser_status(String[] ids, @PathVariable int status) {
		if (so.setStatus(ids, EStatus.get(status))) {
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

}
