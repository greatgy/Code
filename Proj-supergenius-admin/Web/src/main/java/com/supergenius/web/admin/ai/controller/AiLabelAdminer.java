package com.supergenius.web.admin.ai.controller;

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
import com.supergenius.web.admin.ai.helper.AiArticleHP;
import com.supergenius.web.admin.ai.helper.AiLabelHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.ai.entity.Label;
import com.supergenius.xo.ai.service.LabelSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 标签管理页面
 * 
 * @author ChenQi
 * @date 2017年9月26日17:47:32
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AiLabelAdminer extends BaseController {

	@Autowired
	LabelSO so;

	@Autowired
	AdminLogSO adminLogSO;

	/**
	 * 标签管理页面
	 * 
	 * @author ChenQi
	 * @date 2017年9月19日11:56:18
	 * @return String
	 */
	@RequestMapping(value = "/ai/ailabel", method = RequestMethod.GET)
	public String label(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.ailabel.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.ailabel, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.ai.name());
		return "doailabel";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017年9月19日12:11:14
	 * @return
	 */
	@RequestMapping(value = "/ai/ajax/ailabel/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> label_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AiLabelHP.query(model);
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
	@RequestMapping(value = "/ai/ajax/ailabel/status/{status:\\d+}", method = RequestMethod.POST)
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
				AiArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 添加标签
	 * 
	 * @author ChenQi
	 * @date 2017年9月19日14:30:15
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/ai/ajax/ailabel/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> label_add(Map<String, Object> model, String content, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(content)) {
			String[] labels = content.split("\\s+");
			for (String labeli : labels) {
				if (AiLabelHP.isExist(labeli) != null) {
					continue;
				}
				Label label = new Label();
				label.setContent(labeli);
				label.setIsrecommend(0);
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					label.setAdminuid(AdminHP.getAdminUid());
				}
				label.setCount(0L);
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.label.toInt());
				adminLog.setDataid(label.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.addLabel.getName());
				adminLog.setOperation(EAdminLog.addLabel.getName());
				so.add(label, adminLog);
				AiArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

}
