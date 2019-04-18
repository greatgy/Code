package com.supergenius.web.admin.life.controller;

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
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeContentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.service.ContentSO;

/**
 * 内容管理controller
 * 
 * @author ChenQi
 * @date 2018年5月11日16:03:51
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 进入后台content管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/lifecontent" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifecontent.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifecontent, Locale.CHINA));
		return "dolifecontent";
	}

	/**
	 * 得到contentlist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/ajax/lifecontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}


	/**
	 * 编辑内容
	 * 
	 * @author ChenQi
	 * @date 2018年5月11日17:28:48
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifecontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, String uid, String name, String summary, String content, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid) && StrUtil.isNotEmpty(name)) {
			Content entity = so.get(uid);
			entity.setSummary(summary);
			entity.setContent(content);
			if (entity != null) {
				entity.setName(name);
				if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
					entity.setAdminuid(AdminHP.getAdminUid());
				}
				so.update(entity);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 删除内容
	 * 
	 * @param ids
	 * @return
	 * @author ChenQi
	 * @createtime 2018年5月11日17:31:21
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifecontent/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> content_delete(String[] ids) {
		if (so.delete(ids[0]))
			return success();
		return result(MsgKeyDict.deleteFailed);
	}
}
