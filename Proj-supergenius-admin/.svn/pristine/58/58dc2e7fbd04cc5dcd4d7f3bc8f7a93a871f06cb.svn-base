package com.supergenius.web.admin.moral.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.CasesHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Case;
import com.supergenius.xo.moral.enums.ECase;
import com.supergenius.xo.moral.service.CaseSO;

/**
 * 案例管理
 * 
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CaseAdminer extends BaseController {

	@Autowired
	CaseSO so;

	/**
	 * 打开案例管理
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/cases", method = RequestMethod.GET)
	public String cases(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.cases.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.cases, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		Map<String, Object> typeMap = new HashMap<String, Object>();
		for (ECase item : ECase.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, typeMap);
		return "docase";
	}

	/**
	 * 查询数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/cases/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> cases_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CasesHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加案例
	 * 
	 * @param cases
	 * @param file
	 * @param url
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/cases/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cases_add(Case cases, @RequestParam MultipartFile file, String url) {
		if (cases.getType().equals(ECase.doc) && file != null && file.getSize() > 0) {
			cases.setHref(FileUtil.uploadFile(file, SysConf.MoralCasePath));
			cases.setCountdl(0);
			cases.setCreatetime(DateTime.now());
			return so.add(cases) ? success() : result(MsgKeyDict.addFailed);
		} else if (cases.getType().equals(ECase.link) && null != url && !("".equals(url.trim()))) {
			cases.setHref(url);
			cases.setCreatetime(DateTime.now());
			return so.add(cases) ? success() : result(MsgKeyDict.addFailed);
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 更新
	 * 
	 * @param newcase
	 * @param file
	 * @param url
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/cases/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cases_edit(Case newcase, @RequestParam MultipartFile file, String url) {
		Case cases = so.get(newcase.getUid());
		newcase.set(cases);
		if (newcase.getType().equals(ECase.doc) && file != null && file.getSize() > 0) {
			newcase.setHref(FileUtil.uploadFile(file, SysConf.MoralCasePath, cases.getName()));
			newcase.setUpdatetime(DateTime.now());
			return so.update(newcase) ? success() : result(MsgKeyDict.editFailed);
		} else if (cases.getType().equals(ECase.link) && null != url && !("".equals(url.trim()))) {
			newcase.setHref(url);
			newcase.setUpdatetime(DateTime.now());
			return so.update(newcase) ? success() : result(MsgKeyDict.editFailed);
		} else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * del
	 * 
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/cases/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> cases_delete(String[] ids) {
		if (so.delete(ids)) {
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
	@RequestMapping(value = { "/moral/ajax/cases/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cases_status(String[] ids, @PathVariable int status) {
		if (so.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

}
