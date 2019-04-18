package com.supergenius.web.admin.enterpriser.controller;

import java.io.File;
import java.io.IOException;
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
import com.genius.core.base.utils.StrUtil;
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Catalogue;
import com.supergenius.xo.enterpriser.service.CatalogueSO;

/**
 * 模块管理首页
 * 
 * @author loupengyu
 * @date  2018年1月31日12:28:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;
	@Autowired
	private AdminSO adminSO;
	/**
	 * 模块管理页面
	 * 
	 * @author loupengyu
	 * @date 2018年1月31日12:28:38
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/enterprisercatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterprisercatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.enterprisercatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.enterpriser.name());
		model.put(MapperDict.count, so.getCount());
		return "doenterprisercatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author loupengyu
	 * @date 2018年1月31日12:28:38
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EnterpriserCatalogueHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 编辑模块
	 * @param cid
	 * @param newname
	 * @return
	 * @author loupengyu
	 * @date 2018年1月31日12:28:38
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			catalogue.setName(newname);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.enterprisercatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateEnterpriserCatalogue.getName());
			adminLog.setData(EAdminLog.updateEnterpriserCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateEnterpriserCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				Admin admin = adminSO.get(adminUid);
				catalogue.setAdminname(admin.getName());
			}
			if (so.update(catalogue)) {
					String path = SysConf.SerialBasePath + SysConf.SerialEnterpriserCataloguePath + SysConf.Separator_Directory + catalogue.getCid();
					File tempfile = new File(path);
					if (tempfile.exists() && tempfile.isFile()) {
						tempfile.delete();
					}
					if (catalogue.getPcid() == 0) {
						try {
							tempfile.createNewFile();
							SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 冻结与解冻模块
	 * @author loupengyu
	 * @date 2018年1月31日12:28:38
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(int cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.get(cid);
		String adminUid = AdminHP.getAdminUid();
		String	path = SysConf.SerialBasePath + SysConf.SerialEnterpriserCataloguePath + SysConf.Separator_Directory + cid;
		  File	file = new File(path);
			EnterpriserCatalogueHP.deleteFile(file);
			if (EStatus.get(status) == EStatus.enable && catalogue.getPcid() == 0) {
				try {
					file.createNewFile();
					SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.enterprisercatalogue.toInt());
		adminLog.setOperation(EAdminLog.updateEnterpriserCatalogue.getName());
		adminLog.setData(EAdminLog.updateEnterpriserCatalogue.getName());
		if (StrUtil.isNotEmpty(adminUid)) {
			catalogue.setAdminuid(adminUid);
			Admin admin = adminSO.get(adminUid);
			catalogue.setAdminname(admin.getName());
		}
		catalogue.setStatus(EStatus.get(status));
		if (so.update(catalogue)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
}
