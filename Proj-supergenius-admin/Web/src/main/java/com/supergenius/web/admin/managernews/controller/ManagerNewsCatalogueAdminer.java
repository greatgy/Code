package com.supergenius.web.admin.managernews.controller;

import java.io.File;
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
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.managernews.helper.ManagerNewsCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.managernews.entity.Catalogue;
import com.supergenius.xo.managernews.service.CatalogueSO;

/**
 * 模块管理首页
 * 
 * @author Jiashitao
 * @date 2018年7月5日14:21:13
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerNewsCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;

	/**
	 * 模块管理页面
	 * 
	 * @author Jiashitao
	 * @date 2018年7月5日14:21:13
	 * @return String
	 */
	@RequestMapping(value = "/managernews/managernewscatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.managernewscatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.managernewscatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.managernews.name());
		model.put(MapperDict.count, so.getCount());
		return "domanagernewscatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author JiaShitao
	 * @date 2018年7月5日14:21:13
	 * @return String
	 */
	@RequestMapping(value = "/managernews/ajax/managernewscatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ManagerNewsCatalogueHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 编辑模块
	 * @param cid
	 * @param newname
	 * @return
	 * @author Jiashitao
	 * @date 2018年7月5日14:21:13
	 */
	@RequestMapping(value = "/managernews/ajax/managernewscatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname, String data) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			if (Integer.valueOf(cid) == 128) {
				catalogue.setData(data);
			} else {
				catalogue.setName(newname);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.managernewscatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateManagerNewsCatalogue.getName());
			adminLog.setData(EAdminLog.updateManagerNewsCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateManagerNewsCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(catalogue)) {
				String path = SysConf.SerialBasePath + SysConf.SerialManagerNewsCataloguePath + SysConf.Separator_Directory + catalogue.getCid();
				FileUtil.delete(path);
				SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 冻结与解冻模块
	 * 
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/managernews/ajax/managernewscatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(String cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		String path = SysConf.SerialBasePath + SysConf.SerialManagerNewsCataloguePath + SysConf.Separator_Directory + catalogue.getCid();
		File file = new File(path);
		ManagerNewsCatalogueHP.deleteFile(file);
		if (EStatus.get(status) == EStatus.enable) {
			ManagerNewsCatalogueHP.NewMultiFile(file);
			SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.managernewscatalogue.toInt());
		adminLog.setOperation(EAdminLog.updateManagerNewsCatalogue.getName());
		adminLog.setData(EAdminLog.updateManagerNewsCatalogue.getName());
		if (StrUtil.isNotEmpty(adminUid)) {
			catalogue.setAdminuid(adminUid);
			catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		}
		if (so.update(Integer.valueOf(cid), EStatus.get(status))) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

}
