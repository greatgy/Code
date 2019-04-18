package com.supergenius.web.admin.career.controller;

import java.util.List;
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
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.RulerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Ruler;
import com.supergenius.xo.career.service.RulerSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 规则管理controller
 * 
 * @author ChenQi
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerRulerAdminer extends BaseController {

	@Autowired
	private RulerSO so;

	/**
	 * 进入后台规则管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/careerruler" }, method = RequestMethod.GET)
	public String ruler(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerruler.name());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerruler, Locale.CHINA));
		return "docareerruler";
	}

	/**
	 * 得到rulerlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/ajax/careerruler/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ruler_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = RulerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 显示规则表格
	 * 
	 * @author ChenQi
	 * @date 2017-11-15 15:14:11
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careerruler/rulertable", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> rulerlist(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		List<Ruler> list = so.getList();
		model.put(ViewKeyDict.list, list);
		return json(model, Json.webStrategy);
	}

	/**
	 * 编辑规则
	 * 
	 * @param rulerListnow
	 * @return
	 * @author ChenQi 2017年11月16日18:23:50
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/career/ajax/careerruler/editruler", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ruler_edit(String rulerListnow) {
		List<Ruler> list = JsonUtil.fromJson(rulerListnow, List.class, Json.cacheStrategy);
		String adminUid = null;
		Ruler newRuler = null;
		if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
			adminUid = AdminHP.getAdminUid();
		}
		for (Ruler ruler : list) {
			ruler.setAdminuid(adminUid);
			newRuler = so.get(ruler.getUid());
			newRuler.set(ruler);
			so.update(newRuler);
		}
		return success();
	}
	
	/**
	 * 进入后台规则内容管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/careerrulercontent" }, method = RequestMethod.GET)
	public String rulerContent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerrulercontent.name());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerrulercontent, Locale.CHINA));
		return "docareerrulercontent";
	}
	
	/**
	 * 得到rulerlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/ajax/careerrulercontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> rulercontent_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = RulerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 编辑规则内容
	 * 
	 * @param content
	 * @return
	 * @author ChenQi 2017年12月21日16:05:21
	 */
	@RequestMapping(value = "/career/ajax/careerrulercontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rulercontent_edit(Map<String, Object> model, String uid, String name, String content, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(content)) {
			Ruler ruler = so.get(uid);
			if (ruler != null) {
				ruler.setName(name);
				ruler.setContent(content);
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					ruler.setAdminuid(AdminHP.getAdminUid());
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.careerrulercontent.toInt());
				adminLog.setDataid(ruler.getUid());
				adminLog.setDesc(EAdminLog.updateCareerContent.getName());
				adminLog.setData(EAdminLog.updateCareerContent.getName());
				adminLog.setOperation(EAdminLog.updateCareerContent.getName());
				so.update(ruler, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}
}
