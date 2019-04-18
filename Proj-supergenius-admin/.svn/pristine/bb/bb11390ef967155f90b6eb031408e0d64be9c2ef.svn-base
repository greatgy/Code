package com.supergenius.web.admin.tpi.controller;

import java.util.Locale;
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
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.tpi.helper.MessageHP;
import com.supergenius.web.admin.tpi.helper.TeamHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.EType;
import com.supergenius.xo.tpi.service.TeamSO;
import com.supergenius.xo.tpi.service.TypeSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 团队控制器
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class TeamAdminer extends BaseController{
	
	@Autowired
	private TeamSO so;
	
	@Autowired
	private TypeSO typeSO;
	
	@Autowired
	private WishSO wishSO;
	
	/**
	 * 进入团队管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/team"}, method = RequestMethod.GET)
	public String team(Map<String, Object> model) {
		model.put(ViewKeyDict.type, typeSO.getListByType(EType.team));
		model.put(ViewKeyDict.channel, EChannel.team.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.team, Locale.CHINA));
		return "doteam";
	}
	
	/**
	 * 得到team列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"ajax/team/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> team_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = TeamHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除团队
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/team/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> team_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			MessageHP.SendSysMsg(ids, "您创建的团队已经删除");
			wishSO.deleteByTouid(ids); 
			wishSO.deleteByFromuid(ids); 
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 修改团队状态
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 */
	@RequestMapping(value = "/ajax/team/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> team_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置置顶
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/team/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> team_top(String[] ids) {
		if (so.setTop(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消置顶
	 * @param ids
	 * @return 
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/team/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> team_untop(String[] ids) {
		if (so.setTop(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 设置为推荐
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/team/isrecommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> team_isrecommend(String[] ids) {
		if (so.setRecommend(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 取消推荐
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/ajax/team/unrecommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> team_unrecommend(String[] ids) {
		if (so.setRecommend(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
}
