package com.supergenius.web.admin.gupage.controller;

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
import com.supergenius.web.admin.gupage.helper.GupageCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.gupage.entity.Catalogue;
import com.supergenius.xo.gupage.entity.Comments;
import com.supergenius.xo.gupage.service.CatalogueSO;
import com.supergenius.xo.gupage.service.CommentsSO;
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
public class GupageCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;

	@Autowired
	private CommentsSO commentSO;

	/**
	 * 模块管理页面
	 * 
	 * @author ChenQi
	 * @date 2017年11月15日15:13:51
	 * @return String
	 */
	@RequestMapping(value = "/gupage/gupagecatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.gupagecatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.gupagecatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.gupage.name());
		model.put(MapperDict.count, so.getCount());
		return "dogupagecatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017年11月15日15:13:56
	 * @return String
	 */
	@RequestMapping(value = "/gupage/ajax/gupagecatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = GupageCatalogueHP.query(model);
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
	@RequestMapping(value = "/gupage/ajax/gupagecatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname, String data) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		List<Comments> list = commentSO.getListByField(MapperDict.cid, Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			if (StrUtil.isNotEmpty(data)) {
				catalogue.setData(data);
			}
			catalogue.setName(newname);
			for (Comments comments : list) {
				comments.setCataloguename(newname);
				commentSO.update(comments);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.gupagecatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateGupageCatalogue.getName());
			adminLog.setData(EAdminLog.updateGupageCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateGupageCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(catalogue)) {
				String path = SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.Separator_Directory + catalogue.getCid();
				File tempfile = new File(path);
				if (tempfile.exists() && tempfile.isFile()) {
					tempfile.delete();
				}
				try {
					tempfile.createNewFile();
					SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
				} catch (IOException e) {
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
	@RequestMapping(value = "/gupage/ajax/gupagecatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(String cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.get(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		// 删除二级目录对应的序列化文件
		String path = SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.GupageCatalogueOrder;
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
		path = SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.Separator_Directory + cid;
		file = new File(path);
		GupageCatalogueHP.deleteFile(file);
		if (EStatus.get(status) == EStatus.enable) {
			try {
				file.createNewFile();
				SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.gupagecatalogue.toInt());
		adminLog.setOperation(EAdminLog.updateGupageCatalogue.getName());
		adminLog.setData(EAdminLog.updateGupageCatalogue.getName());
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
	@RequestMapping(value = "/gupage/ajax/gupagecatalogue/setorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_order(String informationListnow) {
		Map<String, String> map = new HashMap<>();
		String cidList = "0," + informationListnow;
		map.put(MapperDict.informationList, cidList);
		SerialUtil.serializeToJson(map, SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.GupageCatalogueOrder);
		return success();
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2017-11-15 15:14:11
	 * @return String
	 */
	@RequestMapping(value = "/gupage/ajax/gupagecatalogue/orderlist", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResponseEntity<Map<String, Object>> catalogue_orderlist(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		List<Catalogue> list = new LinkedList<>();
		Catalogue catalogue = null;
		// 从序列化文件中取得网站导航的目录(排序)
		String path = SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.GupageCatalogueOrder;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
			for (String cid : index.get(MapperDict.informationList).split(MapperDict.comma)) {
				path = SysConf.SerialBasePath + SysConf.SerialGupageCataloguePath + SysConf.Separator_Directory + cid;
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
