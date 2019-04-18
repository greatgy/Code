package com.supergenius.web.admin.moralnews.controller;

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
import com.supergenius.web.admin.moralnews.helper.MoralNewsAdHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.moralnews.entity.Content;
import com.supergenius.xo.moralnews.enums.EContent;
import com.supergenius.xo.moralnews.service.ContentSO;

/**
 * 职业道德广告位管理
 * 
 * @author tf
 * @date 2018年9月18日
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralNewsAdAdminer extends BaseController {
	
	@Autowired
	private AdminLogSO adminLogSO;
	
	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/moralnews/moralnewsad" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.moralnewsad.name());
		model.put(ViewKeyDict.site, ESite.moralnews.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.moralnewsad, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.MoralNewsPhotoPath);
		model.put(ViewKeyDict.cataloguelist, MoralNewsAdHP.getCatalogue());
		return "domoralnewsad";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/moralnews/ajax/moralnewsad/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = MoralNewsAdHP.query(model);
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
	 * @author tf
	 * @date 2018年9月19日
	 * @return
	 */
	@RequestMapping(value = "/moralnews/ajax/moralnewsad/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow, int cid, String[] imgdata, HttpServletRequest request) {
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
			adminLog.setChannel(EChannel.moralnewsad.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addMoralnewsAd.getName());
			adminLog.setData(EAdminLog.addMoralnewsAd.getName());
			adminLog.setOperation(EAdminLog.addMoralnewsAd.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 删除广告
	 * 
	 * @author:tf
	 * @param ids
	 * @return
	 * @date:2018年9月19日
	 */
	@RequestMapping(value = "/moralnews/ajax/moralnewsad/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> contents_delete(String[] ids) {
		Content content = so.get(ids[0]);
		if (content != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.moralnewsad.toInt());
			adminLog.setOperation(EAdminLog.deleteMoralnewsAd.getName());
			adminLog.setData(EAdminLog.deleteMoralnewsAd.getName());
			adminLog.setDesc(EAdminLog.deleteMoralnewsAd.getName());
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
	 * @author tf
	 * @date 2018年9月19日
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/moralnews/ajax/moralnewsad/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, int cid, String uid, String name, String originurl, String isshow, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl) && StrUtil.isNotEmpty(isshow) && StrUtil.isNotEmpty(cid)) {
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
				adminLog.setChannel(EChannel.moralnewsad.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.updateMoralnewsAd.getName());
				adminLog.setData(EAdminLog.updateMoralnewsAd.getName());
				adminLog.setOperation(EAdminLog.updateMoralnewsAd.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author tf
	 * @typename Map<String,Object>
	 * @date 2018年9月19日
	 */
	@RequestMapping(value = "/moralnews/ajax/moralnewsad/status", method = RequestMethod.POST)
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
