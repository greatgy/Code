package com.supergenius.web.admin.startup.controller;

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
import com.supergenius.web.admin.startup.helper.LabelHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.entity.Label;
import com.supergenius.xo.startup.service.LabelSO;

@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LabelAdminer extends BaseController {

	@Autowired
	LabelSO so;

	@Autowired
	AdminLogSO adminLogSO;

	/**
	 * 标签管理页面
	 * 
	 * @author yangguang
	 * @date 2017年8月23日15:57:25
	 * @return String
	 */
	@RequestMapping(value = "/startup/label", method = RequestMethod.GET)
	public String label(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.label.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.label, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.startup.name());
		return "dolabel";
	}

	/**
	 * 显示列表
	 * 
	 * @author yangguang
	 * @date 2017年8月23日16:01:24
	 * @return
	 */
	@RequestMapping(value = "/startup/ajax/label/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> label_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LabelHP.query(model);
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
	@RequestMapping(value = "/startup/ajax/label/status/{status:\\d+}", method = RequestMethod.POST)
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
				StartupArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 添加标签
	 * 
	 * @author yangguang
	 * @date 2017年8月24日09:45:30
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/startup/ajax/label/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> label_add(Map<String, Object> model, String content, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(content)) {
			String[] labels = content.split("\\s+");
			for (String labeli : labels) {
				if (LabelHP.isExist(labeli) != null) {
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
				StartupArticleHP.addTags(label);
			}
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

}
