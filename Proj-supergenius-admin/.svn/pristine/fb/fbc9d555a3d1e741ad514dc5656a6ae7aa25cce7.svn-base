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
import com.supergenius.web.admin.tpi.helper.NoticeHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.service.NoticeSO;

/**
 * 招聘信息管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class NoticeAdminer extends BaseController{
	
	
	@Autowired
	private NoticeSO so;
	
	/**
	 * 进入招聘信息管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/notice"}, method = RequestMethod.GET)
	public String notice(Map<String, Object> model) {
		model.put(ViewKeyDict.enumstype, NoticeHP.getENoticeTypeMap());
		model.put(ViewKeyDict.channel, EChannel.notice.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.notice, Locale.CHINA));
		return "donotice";
	}
	
	/**
	 * 查询、获取列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"ajax/notice/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> notice_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = NoticeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/ajax/notice/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> notice_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
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
	@RequestMapping(value = "/ajax/notice/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> notice_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

}
