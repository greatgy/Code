package com.supergenius.web.admin.manger.controller;

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
import com.supergenius.web.admin.manager.helper.AppStudentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.service.AppStudentSO;

/** 
 * 申请学员管理
 * @author chenminchang
 * @date 2016-10-20 下午2:08:58 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AppStudentAdminer extends BaseController {

	@Autowired
	AppStudentSO so;
	
	/**
	 * 跳转申请学员管理页
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-10-20下午2:53:53
	 */
	@RequestMapping(value = "/manager/appstudent", method = RequestMethod.GET)
	public String appstudent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.appstudent.name());
		model.put(ViewKeyDict.channelname, EChannel.appstudent.getName());
		model.put(ViewKeyDict.apptotal, so.getCount());
		model.put(ViewKeyDict.passtotal, so.getCount(EStatus.enable));
		model.put(ViewKeyDict.nopasstotal, so.getCount(EStatus.disable));
		model.put(ViewKeyDict.majormap, EMajor.getChinaNames());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		return "doappstudent";
	}
	
	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/manager/ajax/appstudent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> appstudent_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AppStudentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 同意或拒绝
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-10-21下午3:03:33
	 */
	@RequestMapping(value = "/manager/ajax/appstudent/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> manager_status(HttpServletRequest request, String[] ids, @PathVariable int status,String adminuid, String dopwd, String desc, String year, String month) {
		if (AdminHP.isDopwd(dopwd)) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.appstudent.toInt());
			adminLog.setOperation(EAdminLog.updateAppStudentStatus.getName());
			adminLog.setData(EAdminLog.updateAppStudentStatus.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			if (AppStudentHP.updateStatus(ids[0], EStatus.get(status), year, month, adminLog, desc))
				return success();
			return result(MsgKeyDict.doFailed);
		} 
		return result(MsgKeyDict.dopwdIsWrong);
	}
	
}
