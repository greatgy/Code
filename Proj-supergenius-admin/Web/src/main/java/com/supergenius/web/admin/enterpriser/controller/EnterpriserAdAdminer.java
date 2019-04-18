package com.supergenius.web.admin.enterpriser.controller;

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
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserContentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.service.ContentSO;

/**
 * 广告位管理页面
 * 
 * @author loupengyu
 * @date 2018年1月30日17:21:01
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserAdAdminer extends BaseController {


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
	@RequestMapping(value = { "/enterpriser/enterprisercontent" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterprisercontent.name());
		model.put(ViewKeyDict.site, ESite.enterpriser.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.enterprisercontent, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.EnterpriserPhotoPath);
		model.put(ViewKeyDict.cataloguelist, EnterpriserContentHP.getCatalogue());
		return "doenterprisercontent";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/enterpriser/ajax/enterprisercontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EnterpriserContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加内容
	 * 
	 * @author loupengyu
	 * @date 2018年1月30日17:21:56
	 * @return Map<String,Object>
	 */
	/**
	 * @param model
	 * @param name
	 * @param originurl
	 * @param isshow
	 * @param type
	 * @param imgdata
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercontent/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_add(Map<String, Object> model, String name, String originurl, String isshow,
			int type, int cid, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl) && StrUtil.isNotEmpty(isshow)
				&& StrUtil.isNotEmpty(type)) {
			Content content = new Content();
			content.setName(name);
			content.setOriginurl(originurl);
			content.setStatus(EStatus.get(isshow));
			content.setType(EContent.get(type));
			content.setCid(cid);
			if (StrUtil.isNotEmpty(imgdata)) {
				content.setContent(imgdata[2]);
			}
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.enterprisercontent.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addEnterpriserContent.getName());
			adminLog.setData(EAdminLog.addEnterpriserContent.getName());
			adminLog.setOperation(EAdminLog.addEnterpriserContent.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑题目
	 * 
	 * @author loupengyu
	 * @date 2018年1月30日17:22:24
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, int cid, String uid, String name, String originurl,
			String isshow, String type, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name) && StrUtil.isNotEmpty(originurl)
				&& StrUtil.isNotEmpty(isshow) && StrUtil.isNotEmpty(cid)) {
			Content content = so.get(uid);
			if (content != null) {
				content.setName(name);
				content.setOriginurl(originurl);
				content.setStatus(EStatus.get(isshow));
				content.setCid(cid);
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
				adminLog.setChannel(EChannel.enterprisercontent.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.updateEnterpriserContent.getName());
				adminLog.setData(EAdminLog.updateEnterpriserContent.getName());
				adminLog.setOperation(EAdminLog.updateEnterpriserContent.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author loupengyu
	 * @typename Map<String,Object>
	 * @date 2018年1月30日17:23:11
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterprisercontent/status", method = RequestMethod.POST)
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
