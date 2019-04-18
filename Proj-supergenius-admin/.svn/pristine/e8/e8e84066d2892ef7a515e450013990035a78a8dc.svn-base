package com.supergenius.web.admin.startup.controller;

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
import com.genius.core.base.utils.StrUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.RulerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.enums.ERuler;
import com.supergenius.xo.startup.service.RulerSO;
import com.supergenius.xo.startup.entity.Ruler;

/**
 * 规则管理controller
 * 
 * @author ChenQi
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class RulerAdminer extends BaseController {

	@Autowired
	private RulerSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台规则管理页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/startup/ruler" }, method = RequestMethod.GET)
	public String ruler(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.ruler.name());
		model.put(ViewKeyDict.site, ESite.startup.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.ruler, Locale.CHINA));
		return "doruler";
	}

	/**
	 * 得到rulerlist
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/startup/ajax/ruler/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ruler_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = RulerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加ruler
	 * @param typeValue
	 * @param newruler
	 * @return
	 */
	@RequestMapping(value = "/startup/ajax/ruler/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ruler_add(String typeValue, Ruler newruler, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(newruler.getName()) && StrUtil.isNotEmpty(newruler.getContent()) && StrUtil.isNotEmpty(newruler.getRejectmincount()) && StrUtil.isNotEmpty(newruler.getRejectmaxcount()) && StrUtil.isNotEmpty(newruler.getMinscore()) && StrUtil.isNotEmpty(newruler.getMaxscore())) {newruler.setType(ERuler.get(typeValue));
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				newruler.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.ruler.toInt());
			adminLog.setDataid(newruler.getUid());
			adminLog.setDesc("");
			adminLog.setData(EAdminLog.addRuler.getName());
			adminLog.setOperation(EAdminLog.addRuler.getName());
			if (so.add(newruler, adminLog)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 编辑ruler
	 * @param typeValue
	 * @param newruler
	 * @return
	 */
	@RequestMapping(value = "/startup/ajax/ruler/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ruler_edit(String typeValue, Ruler newruler, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(newruler.getName()) && StrUtil.isNotEmpty(newruler.getContent()) && StrUtil.isNotEmpty(newruler.getRejectmincount()) && StrUtil.isNotEmpty(newruler.getRejectmaxcount()) && StrUtil.isNotEmpty(newruler.getMinscore()) && StrUtil.isNotEmpty(newruler.getMaxscore())) {
			newruler.setType(ERuler.get(typeValue));
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				newruler.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.ruler.toInt());
			adminLog.setDataid(newruler.getUid());
			adminLog.setDesc("");
			adminLog.setData(EAdminLog.updateRuler.getName());
			adminLog.setOperation(EAdminLog.updateRuler.getName());
			Ruler ruler = so.get(newruler.getUid());
			ruler.set(newruler);
			if (so.update(ruler)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 删除测试数据
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/startup/ajax/ruler/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ruler_delete(String[] ids) {
		Ruler ruler = so.get(ids[0]);
		if (ruler != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.ruler.toInt());
			adminLog.setOperation(EAdminLog.deleteRuler.getName());
			adminLog.setData(EAdminLog.deleteRuler.getName());
			adminLog.setDesc(EAdminLog.deleteRuler.getName());
			adminLog.setDataid(ids[0]);
			so.delete(ruler.getUid());
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
}
