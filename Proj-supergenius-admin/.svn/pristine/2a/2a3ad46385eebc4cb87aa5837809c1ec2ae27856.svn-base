package com.supergenius.web.admin.solr.controller;

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
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.solr.helper.ContentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.solr.entity.Content;
import com.supergenius.xo.solr.enums.EContent;
import com.supergenius.xo.solr.service.ContentSO;

/**
 * 搜索推荐管理controller
 * 
 * @author ChenQi
 * @date 2017年11月3日12:27:37
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class SolrContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/solr/solrcontent" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.solrcontent.name());
		model.put(ViewKeyDict.site, ESite.solr.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.solrcontent, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.SolrPhotoPath);
		return "dosolrcontent";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/solr/ajax/solrcontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加内容
	 * 
	 * @author ChenQi
	 * @date 2017年11月3日12:27:48
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/solr/ajax/solrcontent/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow,
			String type, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl) && StrUtil.isNotEmpty(isshow)
				&& StrUtil.isNotEmpty(type)) {
			Content content = new Content();
			content.setName(name);
			content.setOriginurl(originurl);
			content.setStatus(EStatus.get(isshow));
			content.setType(EContent.get(type));
			content.setContent(imgdata[2]);
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.solrcontent.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc("");
			adminLog.setData(EAdminLog.addSolrContent.getName());
			adminLog.setOperation(EAdminLog.addSolrContent.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑题目
	 * 
	 * @author ChenQi
	 * @date 2017年11月3日12:27:52
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/solr/ajax/solrcontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, String uid, String name, String originurl,
			String isshow, String type, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl)
				&& StrUtil.isNotEmpty(isshow) && StrUtil.isNotEmpty(type)) {
			Content content = so.get(uid);
			if (content != null) {
				content.setName(name);
				content.setOriginurl(originurl);
				content.setStatus(EStatus.get(isshow));
				content.setType(EContent.get(type));
				if (StrUtil.isEmpty(imgdata) || imgdata == null) {
					content.setContent(content.getContent());
				} else {
					content.setContent(imgdata[2]);
				}
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					content.setAdminuid(AdminHP.getAdminUid());
				}
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.solrcontent.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc("");
				adminLog.setData(EAdminLog.addSolrContent.getName());
				adminLog.setOperation(EAdminLog.addSolrContent.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author ChenQi
	 * @typename Map<String,Object>
	 * @data 2017年11月3日12:27:56
	 */
	@RequestMapping(value = "/solr/ajax/solrcontent/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			if (so.updateStatusByUid(uid)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);

	}
}
