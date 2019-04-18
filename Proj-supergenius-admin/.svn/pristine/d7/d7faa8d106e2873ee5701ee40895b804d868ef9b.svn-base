package com.supergenius.web.admin.official.controller;

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
import com.supergenius.web.admin.official.helper.DiscussHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.official.entity.Discuss;
import com.supergenius.xo.official.service.DiscussSO;

/**
 * 评论互动
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class DiscussAdminer extends BaseController{
	
	@Autowired
	DiscussSO so;

	/**
	 * 进入评论互动
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/discuss/{type:.{1}}", method = RequestMethod.GET)
	public String discuss(Map<String, Object> model, @PathVariable String type) {
		model.put(ViewKeyDict.type, type);
		model.put(ViewKeyDict.channel, EChannel.discuss.name());
		model.put(ViewKeyDict.site, EChannel.official.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.discuss, Locale.CHINA));
		return "dodiscuss";
	}
	
	/**
	 * 查询时组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/official/ajax/discuss/list/{type:.{1}}"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> discuss_list(Map<String, Object> model, @PathVariable String type, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		model.put(ViewKeyDict.type, type);
		Map<String, Object> searchMap = DiscussHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 回复评论
	 * @param article
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/discuss/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> discuss_edit(String uid, String replycontent) {
		Discuss discuss = so.get(uid);
		if (discuss != null) {
			discuss.setReply(DiscussHP.getReplys(replycontent));
			if (so.update(discuss)) {
				return result(MsgKeyDict.editSuccess);
			}
		}
			return result(MsgKeyDict.editFailed);
	}
	
	
	/**
	 * 修改状态
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/discuss/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> discuss_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 删除评论
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/official/ajax/discuss/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> discuss_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
}
