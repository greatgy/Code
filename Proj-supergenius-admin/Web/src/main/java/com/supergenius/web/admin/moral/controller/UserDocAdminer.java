package com.supergenius.web.admin.moral.controller;

import java.util.List;
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
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.MessageHP;
import com.supergenius.web.admin.moral.helper.UserdocHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.moral.entity.Case;
import com.supergenius.xo.moral.entity.Userdoc;
import com.supergenius.xo.moral.enums.ECase;
import com.supergenius.xo.moral.service.CaseSO;
import com.supergenius.xo.moral.service.UserdocSO;

/**
 * 学员分享
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class UserDocAdminer extends BaseController{
	
	@Autowired
	UserdocSO so;
	
	@Autowired
	CaseSO caseSO;

	/**
	 * 进入学员分享管理
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/userdoc" }, method = RequestMethod.GET)
	public String userdoc(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.userdoc.name());
		model.put(ViewKeyDict.site, EChannel.moral.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.userdoc, Locale.CHINA));
		return "douserdoc";
	}
	
	/**
	 * 查询组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/userdoc/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userdoc_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = UserdocHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 加入到案例
	 * @param userdoc
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/userdoc/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userdoc_edit(Userdoc userdoc) {
		String uid = userdoc.getUid();
		Userdoc userdoc2 = so.get(uid);
		Case ncase = new Case();
		ncase.setName(userdoc2.getName());
		ncase.setType(ECase.doc);
		ncase.setHref(userdoc2.getFile());
		if (caseSO.add(ncase)) {
			MessageHP.sendNoticeMsg(userdoc2, EMsg.userdocrmcase);
			UserdocHP.modifyScore(userdoc2.getUseruid());
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/userdoc/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userdoc_delete(String[] ids) {
		List<Userdoc> list = UserdocHP.get(ids);
		if (so.delete(ids)) {
			for (Userdoc userdoc : list) {
				MessageHP.sendNoticeMsg(userdoc, EMsg.deluserdoc);
			}
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 修改状态
	 * 
	 * @param ids
	 * @param status
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/userdoc/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userdoc_status(String[] ids, @PathVariable int status) {
		if (so.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
}
