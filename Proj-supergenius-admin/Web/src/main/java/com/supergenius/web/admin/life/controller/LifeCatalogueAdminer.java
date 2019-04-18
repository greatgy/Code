package com.supergenius.web.admin.life.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.service.CatalogueSO;

/**
 * 模块管理首页
 * 
 * @author ChenQi
 * @date 2018年5月15日11:10:19
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;

	/**
	 * 模块管理页面
	 * 
	 * @author ChenQi
	 * @date 2018年5月15日11:10:19
	 * @return String
	 */
	@RequestMapping(value = "/life/lifecatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifecatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifecatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.life.name());
		Map<String, Object> map = new HashMap<>();
		model.put(MapperDict.count, so.getCount(map));
		return "dolifecatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2018年5月15日11:10:19
	 * @return String
	 */
	@RequestMapping(value = "/life/ajax/lifecatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeCatalogueHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 编辑模块
	 * @param cid
	 * @param newname
	 * @return
	 * @author ChenQi
	 * @date 2018年5月15日11:10:19
	 */
	@RequestMapping(value = "/life/ajax/lifecatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname, String data) {
		Catalogue catalogue = so.getOneByCid(Long.parseLong(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			if (Long.parseLong(cid) == 8) {
				catalogue.setData(data);
			} else {
				catalogue.setName(newname);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.lifecatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateLifeCatalogue.getName());
			adminLog.setData(EAdminLog.updateLifeCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateLifeCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(catalogue)) {
				String path = SysConf.SerialBasePath + SysConf.SerialLifeCataloguePath;
				FileUtil.delete(path);
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 冻结与解冻模块
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/life/ajax/lifecatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(String cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.getOneByCid(Long.parseLong(cid));
		String adminUid = AdminHP.getAdminUid();
		String path = SysConf.SerialBasePath + SysConf.SerialLifeCataloguePath;
		FileUtil.delete(path);
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.topic.toInt());
		adminLog.setOperation(EAdminLog.updateLifeCatalogue.getName());
		adminLog.setData(EAdminLog.updateLifeCatalogue.getName());
		if (StrUtil.isNotEmpty(adminUid)) {
			catalogue.setAdminuid(adminUid);
			catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		}
		if (so.update(cid, status)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
}