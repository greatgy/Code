package com.supergenius.web.admin.startup.controller;

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
import com.supergenius.web.admin.startup.helper.StartupCatalogueHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.entity.Catalogue;
import com.supergenius.xo.startup.service.CatalogueSO;

/**
 * 模块管理首页
 * 
 * @author 许志翔
 * @date 2017年8月25日09:34:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class StartupCatalogueAdminer extends BaseController {

	@Autowired
	private CatalogueSO so;

	/**
	 * 模块管理页面
	 * 
	 * @author 许志翔
	 * @date 2017年8月25日10:55:50
	 * @return String
	 */
	@RequestMapping(value = "/startup/startupcatalogue", method = RequestMethod.GET)
	public String catalogue(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.startupcatalogue.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.startupcatalogue, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.startup.name());
		Map<String, Object> map = new HashMap<>();
		model.put(MapperDict.count, so.getCount(map));
		return "dostartupcatalogue";
	}

	/**
	 * 显示列表
	 * 
	 * @author 许志翔
	 * @date 2017年8月24日09:34:20
	 * @return String
	 */
	@RequestMapping(value = "/startup/ajax/startupcatalogue/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> catalogue_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StartupCatalogueHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 编辑模块
	 * @param cid
	 * @param newname
	 * @return
	 * @author 许志翔
	 * @date 2017年8月25日11:09:45
	 */
	@RequestMapping(value = "/startup/ajax/startupcatalogue/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_edit(String cid, String newname, String data) {
		Catalogue catalogue = so.getOneByCid(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		if (catalogue != null) {
			if (Integer.valueOf(cid) == 3) {
				catalogue.setData(data);
			} else {
				catalogue.setName(newname);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.startupcatalogue.toInt());
			adminLog.setDataid(catalogue.getUid());
			adminLog.setDesc(EAdminLog.updateStartupCatalogue.getName());
			adminLog.setData(EAdminLog.updateStartupCatalogue.getName());
			adminLog.setOperation(EAdminLog.updateStartupCatalogue.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				catalogue.setAdminuid(adminUid);
				catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(catalogue)) {
				if (catalogue.getPcid() != 0 && catalogue.getCid() != SysConf.flashCid) {
					String path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + catalogue.getPcid()
							+ SysConf.Separator_Directory + catalogue.getCid();
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
				}
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/startup/ajax/startupcatalogue/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_status(String cid, @PathVariable int status, HttpServletRequest request) {
		Catalogue catalogue = so.getOneByCid(Integer.valueOf(cid));
		String adminUid = AdminHP.getAdminUid();
		int pcid = catalogue.getPcid();
		if (pcid == 0) { //表示是一级模块，需要把二级模块也进行相应的处理
			StartupCatalogueHP.setSecondCatalogueStatus(catalogue.getCid(), EStatus.get(status));
			String path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + cid;
			File file = new File(path);
			StartupCatalogueHP.deleteFile(file);
			if (EStatus.get(status) == EStatus.enable) {
				file.mkdirs();
				List<Catalogue> secondCatalogueList = so.getListByPcid(Integer.parseInt(cid));
				for (Catalogue catalogue2 : secondCatalogueList) {
					if (catalogue2.getCid() == SysConf.flashCid) {
						continue;
					}
					path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + cid
							+ SysConf.Separator_Directory + catalogue2.getCid();
					File tempfile = new File(path);
					try {
						tempfile.createNewFile();
						SerialUtil.serializeToJson(catalogue2, path, Json.cacheStrategy);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {//删除二级目录对应的序列化文件
			String path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.startupCatalogueOrder;
			File file = new File(path);
			String mapper = "";
			if (pcid == 1) {
				mapper = MapperDict.informationList;
			}
			if (pcid == 2) {
				mapper = MapperDict.tidbitsList;
			}
			if (file.exists() && file.isFile() && StrUtil.isNotEmpty(mapper)) {
				Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
				String cids = "";
				if (EStatus.get(status) == EStatus.disable && catalogue.getCid() != SysConf.flashCid) {
					cids = index.get(mapper).replaceAll("," + cid + "|" + cid + ",", "");
				} else {
					cids = index.get(mapper) + "," + cid;
				}
				index.put(mapper, cids);
				SerialUtil.serializeToJson(index, path);
			}
			path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + pcid + SysConf.Separator_Directory + cid;
			file = new File(path);
			StartupCatalogueHP.deleteFile(file);
			if (EStatus.get(status) == EStatus.enable && catalogue.getCid() != SysConf.flashCid) {
				try {
					file.createNewFile();
					SerialUtil.serializeToJson(catalogue, path, Json.cacheStrategy);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		AdminLog adminLog = new AdminLog();
		adminLog.setChannel(EChannel.topic.toInt());
		adminLog.setOperation(EAdminLog.updateStartupCatalogue.getName());
		adminLog.setData(EAdminLog.updateStartupCatalogue.getName());
		if (StrUtil.isNotEmpty(adminUid)) {
			catalogue.setAdminuid(adminUid);
			catalogue.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		}
		if (so.update(cid, status)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	  * 更新模块的显示以及顺序
	  * @param informationListnow
	  * @param tidbitsListnow
	  * @return
	  * @author ChenQi
	  * 2017年9月15日17:47:03
	  */
	@RequestMapping(value = "/startup/ajax/startupcatalogue/setorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> catalogue_order(String tidbitsListnow, String informationListnow) {
		Map<String, String> map = new HashMap<>();
		map.put(MapperDict.informationList, informationListnow);
		map.put(MapperDict.tidbitsList, tidbitsListnow);
		SerialUtil.serializeToJson(map, SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.startupCatalogueOrder);
		return success();
	}
	
	/**
	 * 显示列表
	 * 
	 * @author 许志翔
	 * @date 2017年8月24日09:34:20
	 * @return String
	 */
	@RequestMapping(value = "/startup/ajax/startupcatalogue/orderlist", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResponseEntity<Map<String, Object>> catalogue_orderlist(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		List<Catalogue> informationList = new LinkedList<>();//创业资讯
		List<Catalogue> tidbitsList = new LinkedList<>();//创业花絮
		Catalogue catalogue = null;
		//从序列化文件中取得网站导航的目录(排序)
		String path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.startupCatalogueOrder;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			Map<String, String> index = SerialUtil.deserializeFromJson(path, Map.class);
			for(String cid: index.get(MapperDict.informationList).split(MapperDict.comma)) {
				path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + "1" + SysConf.Separator_Directory + cid;
				file = new File(path);
				if (file.exists() && file.isFile()) {
					catalogue = SerialUtil.deserializeFromJson(path, Catalogue.class, Json.cacheStrategy);
					informationList.add(catalogue);
				}
			}
			for(String cid: index.get(MapperDict.tidbitsList).split(MapperDict.comma)) {
				path = SysConf.SerialBasePath + SysConf.SerialCataloguePath + SysConf.Separator_Directory + "2" + SysConf.Separator_Directory + cid;
				file = new File(path);
				if (file.exists() && file.isFile()) {
					catalogue = SerialUtil.deserializeFromJson(path, Catalogue.class, Json.cacheStrategy);
					tidbitsList.add(catalogue);
				}
			}
		} else {
			informationList = so.getListByPcid(1);
			List<Catalogue> cataloguelist = so.getListByPcid(2);
			for(Catalogue catalogue2 : cataloguelist){
				if (catalogue2.getCid() != SysConf.flashCid) {
					tidbitsList.add(catalogue2);
				}
			}
		}
		model.put(MapperDict.informationList, informationList);
		model.put(MapperDict.tidbitsList, tidbitsList);
		return json(model, Json.webStrategy);
	}

}
