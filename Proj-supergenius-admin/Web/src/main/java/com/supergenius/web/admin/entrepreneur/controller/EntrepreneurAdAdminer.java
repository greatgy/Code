package com.supergenius.web.admin.entrepreneur.controller;

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
import com.supergenius.web.admin.entrepreneur.hellper.EntrepreneurAdHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.entrepreneur.entity.Content;
import com.supergenius.xo.entrepreneur.enums.EContent;
import com.supergenius.xo.entrepreneur.service.ContentSO;

/**
 * 广告位管理页面
 * 
 * @author tf
 * @date 2018年7月5日
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EntrepreneurAdAdminer extends BaseController {


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
	@RequestMapping(value = { "/entrepreneur/entrepreneurad" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.entrepreneurad.name());
		model.put(ViewKeyDict.site, ESite.entrepreneur.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.entrepreneurad, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.EntrepreneurPhotoPath);
		model.put(ViewKeyDict.cataloguelist, EntrepreneurAdHP.getCatalogue());
		return "doentrepreneurad";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/entrepreneur/ajax/entrepreneurad/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EntrepreneurAdHP.query(model);
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
	 * @date 2018年7月5日
	 * @return
	 */
	@RequestMapping(value = "/entrepreneur/ajax/entrepreneurad/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow,String type,
	 int cid, String[] imgdata, HttpServletRequest request) {
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
			adminLog.setChannel(EChannel.entrepreneurad.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addEntrepreneurAd.getName());
			adminLog.setData(EAdminLog.addEntrepreneurAd.getName());
			adminLog.setOperation(EAdminLog.addEntrepreneurAd.getName());
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
	@RequestMapping(value = "/entrepreneur/ajax/entrepreneurad/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> contents_delete(String[] ids) {
		Content content = so.get(ids[0]);
		if (content != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.entrepreneurad.toInt());
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
	 * @author tf
	 * @date 2018年7月5日
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/entrepreneur/ajax/entrepreneurad/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, int cid, String uid, String name, String originurl,
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
				adminLog.setChannel(EChannel.entrepreneurad.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.updateEntrepreneurAd.getName());
				adminLog.setData(EAdminLog.updateEntrepreneurAd.getName());
				adminLog.setOperation(EAdminLog.updateEntrepreneurAd.getName());
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
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "/entrepreneur/ajax/entrepreneurad/status", method = RequestMethod.POST)
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
		}
		return result(MsgKeyDict.editFailed);
	}

}
