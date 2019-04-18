package com.supergenius.web.admin.enterpriser.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.enterpriser.hellper.AppCooperationHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.AppCooperation;
import com.supergenius.xo.enterpriser.service.AppCooperationSO;

/**
 * 互助合作平台报名控制器
 * @author XieMing
 * @date 2016年12月5日 下午5:05:37
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AppCooperationAdminer extends BaseController {

	@Autowired
	private AppCooperationSO so;
	
	@Autowired
	AdminLogSO adminLogSO;
	
	/**
	 * 互助合作平台报名后台管理首页
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016年12月5日 下午4:24:43
	 */
	@RequestMapping(value = "/enterpriser/appcooperation", method = RequestMethod.GET)
	public String appcooperation(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.appcooperation.name());
		model.put(ViewKeyDict.channelname, EChannel.appcooperation.getName());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.apptotal, so.getCount());
		return "doappcooperation";
	}
	
	/**
	 * 获取查询数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016年12月5日 下午4:24:27
	 */
	@RequestMapping(value = "/enterpriser/ajax/appcooperation/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> appcooperation_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AppCooperationHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 编辑互助合作平台报名人信息，邮寄地址 公司 职位
	 * @param uid
	 * @param address
	 * @return
	 * @author XieMing
	 * 2016年12月5日 下午4:15:54
	 */
	@RequestMapping(value = "/enterpriser/ajax/appcooperation/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appcooperation_edit(String uid, String address, String company, String job) {
		AppCooperation appCooperation = so.get(uid);
		if(appCooperation != null) {
			appCooperation.setAddress(address);
			appCooperation.setCompany(company);
			appCooperation.setJob(job);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.appcooperation.toInt());
			adminLog.setDataid(appCooperation.getUid());
			adminLog.setData(EAdminLog.updateAppCooperation.getName());
			adminLog.setOperation(EAdminLog.updateAppCooperation.getName());
			adminLog.setDesc(EAdminLog.updateAppCooperation.getName());
			so.update(appCooperation);
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}
	
}
