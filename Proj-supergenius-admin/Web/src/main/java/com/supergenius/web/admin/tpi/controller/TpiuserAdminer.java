package com.supergenius.web.admin.tpi.controller;

import java.util.HashMap;
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
import com.supergenius.web.admin.tpi.helper.MessageHP;
import com.supergenius.web.admin.tpi.helper.TpiuserHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.EInvestType;
import com.supergenius.xo.tpi.enums.EMergeType;
import com.supergenius.xo.tpi.enums.EPayType;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;
import com.supergenius.xo.tpi.service.TpiuserSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 机构会员管理控制器
 * 
 * @author ShangJianguo
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class TpiuserAdminer extends BaseController {
	
	@Autowired
	TpiuserSO so;
	
	@Autowired
	WishSO wishSO;
	
	/**
	 * 跳转到列表页
	 * @param model
	 * @param type
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/tpiuser/{type:\\d+}", method = RequestMethod.GET)
	public String article(Map<String, Object> model, @PathVariable int type, HttpServletRequest request){
		ETpiUserType etype = ETpiUserType.get(type);
		model.put(ViewKeyDict.channel, EChannel.tpiuser.name());
		model.put(ViewKeyDict.channelname, etype.getTypeName());
		model.put(ViewKeyDict.type, etype.name());
		Map<String, String> stateMap = new HashMap<String, String>();
		for (ETpiuserState state : ETpiuserState.values()) {
			stateMap.put(state.name(), state.getName());
		}
		model.put(ViewKeyDict.map, stateMap);
		Map<String, String> typeMap = new HashMap<>();
		if (etype == ETpiUserType.investment) {// 投资机构
			for (EInvestType item : EInvestType.values()) {
				typeMap.put(item.name(), item.getName());
			}
		}else if(etype == ETpiUserType.merger){
			for (EMergeType item : EMergeType.values()) {
				typeMap.put(item.name(), item.getName());
			}
		}else if (etype == ETpiUserType.recommend) {
		}
		model.put(ViewKeyDict.map2, typeMap);
		Map<String, String> paytypeMap = new HashMap<String, String>();
		for (EPayType paytype : EPayType.values()) {
			paytypeMap.put(paytype.name(), paytype.getName());
		}
		model.put(ViewKeyDict.map3, paytypeMap);
		return "dotpiuser";
	}
	
	/**
	 * 加载列表
	 * @param model
	 * @param request
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = {"ajax/tpiuser/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tpiuser_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = TpiuserHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tpiuser_delete(String[] ids) {
		if(so.delete(ids)){
			wishSO.deleteByTouid(ids);
			wishSO.deleteByFromuid(ids); 
			return success();
		}else{
			return result(MsgKeyDict.deleteFailed);
		}
	}
	
	/**
	 * 设置置顶
	 * @param ids
	 * @param istop
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/istop/{istop:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tpiuser_settop(String[] ids, @PathVariable int istop) {
		if (so.setTop(ids, istop==0 ? false : true)){
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 设置放在首页
	 * @param ids
	 * @param isindex
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/isindex/{isindex:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tpiuser_setindex(String[] ids, @PathVariable int isindex) {
		if (so.setIndex(ids, isindex==0 ? false : true)){
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 更新状态
	 * @param ids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpiuser_status(String[] ids, @PathVariable int status) {
		if (so.setStatus(ids, EStatus.get(status))) {
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 审核信息
	 * @param ids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/audit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpiuser_audit(String[] ids, int result, String content) {
		if(so.audit(ids, result == 0 ? false : true, content)){
			if (result == 1) {
				TpiuserHP.sendAuditMsg2User(ids, true, content);
			}else {
				TpiuserHP.sendAuditMsg2User(ids, false, content);
			}
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 特批会员
	 * @param ids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/ajax/tpiuser/specialaudit/{paytype}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tpiuser_specialaudit(String[] ids, @PathVariable EPayType paytype) {
		if(so.specialaudit(ids, paytype)){
			String msg = "您的帐号已特批成功";
			MessageHP.SendSysMsg(ids, msg);
			return success();
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
}
