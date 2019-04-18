package com.supergenius.web.admin.career.controller;

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
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.ContentHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Content;
import com.supergenius.xo.career.enums.EContent;
import com.supergenius.xo.career.service.ContentSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 内容管理controller
 * 
 * @author ChenQi
 * @date 2017年8月23日17:35:38
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/careercontent" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careercontent.name());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careercontent, Locale.CHINA));
		return "docareercontent";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/ajax/careercontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加内容
	 * 
	 * @author xuzhixiang
	 * @date 2017年11月15日09:46:05
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
	@RequestMapping(value = "/career/ajax/careercontent/add", method = RequestMethod.POST)
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
			if (StrUtil.isNotEmpty(imgdata)) {
				content.setContent(imgdata[2]);
			}
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				content.setAdminuid(AdminHP.getAdminUid());
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careercontent.toInt());
			adminLog.setDataid(content.getUid());
			adminLog.setDesc(EAdminLog.addCareerContent.getName());
			adminLog.setData(EAdminLog.addCareerContent.getName());
			adminLog.setOperation(EAdminLog.addCareerContent.getName());
			so.add(content, adminLog);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 编辑题目
	 * 
	 * @author xuzhixiang
	 * @date 2017年9月19日11:29:24
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/career/ajax/careercontent/edit", method = RequestMethod.POST)
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
				adminLog.setChannel(EChannel.careercontent.toInt());
				adminLog.setDataid(content.getUid());
				adminLog.setDesc(EAdminLog.updateCareerContent.getName());
				adminLog.setData(EAdminLog.updateCareerContent.getName());
				adminLog.setOperation(EAdminLog.updateCareerContent.getName());
				so.update(content, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author xuzhixiang
	 * @typename Map<String,Object>
	 * @date 2017年9月19日11:30:04
	 */
	@RequestMapping(value = "/career/ajax/careercontent/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			if (so.updateStatusByUid(uid)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);
	}
	
	/**
	 * 获取广告位中应聘指南的个数
	 * 
	 * @author yangguang
	 * @date 2017年11月14日12:38:14
	 */
	@RequestMapping(value = "/career/ajax/careercontent/guidecount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, ContentHP.getGuideCount());
		return json(model, Json.webStrategy);
	}	
}
