package com.supergenius.web.admin.managernews.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.managernews.helper.ManagerNewsAdHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.managernews.entity.Content;
import com.supergenius.xo.managernews.enums.EContent;
import com.supergenius.xo.managernews.service.ContentSO;

/**
 * 广告位管理页面
 * 
 * @author YangGuang
 * @date 2018年5月9日18:13:19
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerNewsAdAdminer extends BaseController {


	@Autowired
	AdminLogSO adminLogSO;

	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/managernews/managernewsad" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.managernewsad.name());
		model.put(ViewKeyDict.site, ESite.managernews.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.managernewsad, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.ManagernewsPhotoPath);
		model.put(ViewKeyDict.cataloguelist, ManagerNewsAdHP.getCatalogue());
		return "domanagernewsad";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/managernews/ajax/managernewsad/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ManagerNewsAdHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加内容
	 * 
	 * @param model
	 * @param name
	 * @param originurl
	 * @param isshow
	 * @param type
	 * @param imgdata
	 * @param request
	 * @author JiaShitao
	 * @date 2018年5月9日18:20:06
	 * @return
	 */
	@RequestMapping(value = "/managernews/ajax/managernewsad/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow,
	 Long cid, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl) && StrUtil.isNotEmpty(isshow)) {
			Content content = new Content();
			content.setName(name);
			content.setOriginurl(originurl);
			content.setStatus(EStatus.get(isshow));
			content.setType(EContent.ad);
			content.setCid(cid);
			if (StrUtil.isNotEmpty(imgdata)) {
				content.setContent(imgdata[2]);
			}
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.managernewsad.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addManagerNewsAd.getName());
			adminLog.setData(EAdminLog.addManagerNewsAd.getName());
			adminLog.setOperation(EAdminLog.addManagerNewsAd.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 删除广告
	 * @author:JiaShitao
	 * @param ids
	 * @return
	 * @date:2018年7月20日
	 */
	@RequestMapping(value = "/managernews/ajax/managernewsad/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> contents_delete(String[] ids) {
		Content content = so.get(ids[0]);
		if (content != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.managernewsad.toInt());
			adminLog.setOperation(EAdminLog.deleteStatistics.getName());
			adminLog.setData(EAdminLog.deleteStatistics.getName());
			adminLog.setDesc(EAdminLog.deleteStatistics.getName());
			adminLog.setDataid(ids[0]);
			content.setStatus(EStatus.deleted);
			if (so.update(content)) {
				adminLogSO.add(adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 编辑广告位
	 * 
	 * @author YangGuang
	 * @date 2018年5月9日18:19:35
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/managernews/ajax/managernewsad/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, Long cid, String uid, String name, String originurl,
			String isshow, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl)
				&& StrUtil.isNotEmpty(isshow) && StrUtil.isNotEmpty(cid)) {
			Content content = so.get(uid);
			if (content != null) {
				content.setName(name);
				content.setOriginurl(originurl);
				content.setStatus(EStatus.get(isshow));
				content.setCid(cid);
				if (StrUtil.isNotEmpty(imgdata)) {
					content.setContent(imgdata[2]);
				}
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					content.setAdminuid(AdminHP.getAdminUid());
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.managernewsad.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.updateManagerNewsAd.getName());
				adminLog.setData(EAdminLog.updateManagerNewsAd.getName());
				adminLog.setOperation(EAdminLog.updateManagerNewsAd.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @typename Map<String,Object>
	 * @date 2018年5月9日18:19:22
	 */
	@RequestMapping(value = "/managernews/ajax/managernewsad/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			Content content = so.get(uid);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			if (content.getStatus() == EStatus.disable) {
				content.setStatus(EStatus.enable);
			} else {
				content.setStatus(EStatus.disable);
			}
			so.update(content);
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

}
