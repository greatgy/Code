package com.supergenius.web.admin.finance.controller;

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
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.finance.helper.FinanceArticleHP;
import com.supergenius.web.admin.finance.helper.FinanceLabelHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.service.LabelSO;

@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class FinanceLabelAdminer extends BaseController{

	
	@Autowired
	LabelSO so;

	@Autowired
	AdminLogSO adminLogSO;

	/**
	 * 标签管理页面
	 * 
	 * @author loupengyu
	 * @date  2018年1月8日10:34:50
	 * @return String
	 */
	@RequestMapping(value = "/finance/financelabel", method = RequestMethod.GET)
	public String label(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.financelabel.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.financelabel, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.finance.name());
		return "dofinancelabel";
	}
	
	/**
	 * 显示列表
	 * 
	 * @author loupengyu
	 * @date  2018年1月8日10:35:01
	 * @return
	 */
	@RequestMapping(value = "/finance/ajax/financelabel/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> label_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = FinanceLabelHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 修改冻结状态
	 * 
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 */
	@RequestMapping(value = "/finance/ajax/financelabel/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> label_status(String uid, @PathVariable int status, String dopwd) {
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.label.toInt());
		adminLog.setOperation(EAdminLog.updateLabelStatus.getName());
		adminLog.setData(EAdminLog.updateLabelStatus.getName());
		adminLog.setDataid(uid);
		adminLog.setDesc("");
		adminLog.setAdminuid(AdminHP.getAdminUid());
		Label label = so.get(uid);
		if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
			label.setAdminuid(AdminHP.getAdminUid());
		}
		label.setStatus(EStatus.get(status));
		if (so.update(label, adminLog)) {
			if (EStatus.get(status) == EStatus.enable) {
				FinanceArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 添加标签
	 * 
	 * @author loupengyu
	 * @date   2018年1月8日10:35:14
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/finance/ajax/financelabel/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> label_add(Map<String, Object> model, String content, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(content)) {
			String[] labels = content.split("\\s+");
			for (String labeli : labels) {
				if (FinanceLabelHP.isExist(labeli) != null) {
					continue;
				}
				Label label = new Label();
				label.setContent(labeli);
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					label.setAdminuid(AdminHP.getAdminUid());
				}
				label.setCount(0);
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.label.toInt());
				adminLog.setDataid(label.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.addLabel.getName());
				adminLog.setOperation(EAdminLog.addLabel.getName());
				so.add(label, adminLog);
				FinanceArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
}
