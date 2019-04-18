package com.supergenius.web.admin.career.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.CareerCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Catalogue;
import com.supergenius.xo.career.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 模块管理首页
 * 
 * @author ChenQi
 * @date 2017年11月15日15:13:45
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;

	/**
	 * 模块管理页面
	 * 
	 * @author ChenQi
	 * @date 2017年11月15日15:13:51
	 * @return String
	 */
	@RequestMapping(value = "/career/careercatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careercatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careercatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(MapperDict.count, so.getCount());
		return "docareercatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017年11月15日15:13:56
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careercatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CareerCatalogueHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 编辑模块
	 * 
	 * @param cid
	 * @param newname
	 * @return
	 * @author ChenQi
	 * @date 2017-11-15 15:14:00
	 */
	@RequestMapping(value = "/career/ajax/careercatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname, String data) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			if (StrUtil.isNotEmpty(data)) {
				catalogue.setData(data);
			} 
			catalogue.setName(newname);
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careercatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateCareerCatalogue.getName());
			adminLog.setData(EAdminLog.updateCareerCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateCareerCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(catalogue)) {
				String path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.Separator_Directory
						+ catalogue.getCid();
				File tempfile = new File(path);
				if (tempfile.exists() && tempfile.isFile()) {
					tempfile.delete();
				}
				try {
					tempfile.createNewFile();
					SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/career/ajax/careercatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(String cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		// 删除二级目录对应的序列化文件
		String path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.CareerCatalogueOrder;
		File file = new File(path);
		String mapper = MapperDict.informationList;
		if (file.exists() && file.isFile() && StrUtil.isNotEmpty(mapper)) {
			Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
			String cids = "";
			if (EStatus.get(status) == EStatus.disable) {
				cids = index.get(mapper).replaceAll("," + cid + "|" + cid + ",", "");
			} else {
				cids = index.get(mapper) + "," + cid;
			}
			index.put(mapper, cids);
			SerialUtil.serializeToJson(index, path);
		}
		path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.Separator_Directory + cid;
		file = new File(path);
		CareerCatalogueHP.deleteFile(file);
		if (EStatus.get(status) == EStatus.enable) {
			try {
				file.createNewFile();
				SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.careercatalogue.toInt());
		adminLog.setOperation(EAdminLog.updateCareerCatalogue.getName());
		adminLog.setData(EAdminLog.updateCareerCatalogue.getName());
		if (StrUtil.isNotEmpty(adminUid)) {
			catalogue.setAdminuid(adminUid);
			catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		}
		if (so.update(Integer.valueOf(cid), EStatus.get(status))) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 更新模块的显示以及顺序
	 * 
	 * @param informationListnow
	 * @return
	 * @author ChenQi 2017-11-15 15:14:07
	 */
	@RequestMapping(value = "/career/ajax/careercatalogue/setorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_order(String informationListnow) {
		Map<String, String> map = new HashMap<>();
		String cidList = "0," + informationListnow;
		map.put(MapperDict.informationList, cidList);
		SerialUtil.serializeToJson(map,
				SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.CareerCatalogueOrder);
		return success();
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017-11-15 15:14:11
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/careercatalogue/orderlist", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResponseEntity<Map<String, Object>> catalogue_orderlist(Map<String, Object> model,
			HttpServletRequest request) {
		cloneParamsToModel(model, request);
		List<Catalogue> list = new LinkedList<>();
		Catalogue catalogue = null;
		// 从序列化文件中取得网站导航的目录(排序)
		String path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.CareerCatalogueOrder;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
			for (String cid : index.get(MapperDict.informationList).split(MapperDict.comma)) {
				path = SysConf.SerialBasePath + SysConf.SerialCareerCataloguePath + SysConf.Separator_Directory + cid;
				file = new File(path);
				if (file.exists() && file.isFile()) {
					catalogue = SerialUtil.deserializeFromJson(path, Catalogue.class, Json.cacheStrategy);
					list.add(catalogue);
				} else {
					catalogue = so.get(cid);
					list.add(catalogue);
				}
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put(MapperDict.status, EStatus.enable);
			map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.createtime + MapperDict.desc);
			list = so.getList(map);
		}
		model.put(MapperDict.informationList, list);
		return json(model, Json.webStrategy);
	}

}
