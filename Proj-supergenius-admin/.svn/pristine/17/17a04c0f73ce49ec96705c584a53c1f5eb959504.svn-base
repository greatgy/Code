package com.supergenius.web.admin.life.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.supergenius.web.admin.life.helper.LifeBannerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.service.ContentSO;

/**
 * banner管理
 * 
 * @author ChenQi
 * @date 2018年5月11日10:32:48
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeBannerAdminer extends BaseController {

	@Autowired
	private ContentSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;
	/**
	 * 内容页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author ChenQi
	 * @createtime 2018年5月11日10:32:48
	 * @return String
	 */
	@RequestMapping(value = "/life/lifebanner", method = RequestMethod.GET)
	public String enterprisercontent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifebanner.name());
		model.put(ViewKeyDict.channelname, EChannel.lifebanner.getName());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.photopath, SysConf.LifePhotoPath);
		return "dolifebanner";
	}

	/**
	 * 查询数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author ChenQi
	 * @createtime 2018年5月11日10:32:48
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = { "/life/ajax/lifebanner/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeBannerHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 修改banner
	 * 
	 * @param content
	 * @param uid
	 * @return
	 * @author ChenQi
	 * @createtime 2018年5月11日10:32:48
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifebanner/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> photo_edit(String title, String url, String[] imgdata, String oid, String cid) {
		if (StrUtil.isEmpty(title)) {
			return result(MsgKeyDict.updateFailed);
		}
		String content = "";
		if (StrUtil.isNotEmpty(imgdata)) {
			content = imgdata[0];
		}
		Content entity = LifeBannerHP.update(title, content, url, oid, cid);
		if(so.update(entity)){
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}
	/**
	 * 冻结解冻
	 * 
	 * @param ids
	 * @return
	 * @author JIaShitao
	 * @data 2018年5月10日15:25:40
	 */
	@RequestMapping(value = "/life/ajax/lifebanner/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> banner_delete(String uid, @PathVariable int status) {
		Content content = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (content != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.lifecontent.toInt());
			adminLog.setOperation(EAdminLog.deleteLifeBanner.getName());
			adminLog.setData(EAdminLog.deleteLifeBanner.getName());
			adminLog.setDesc(EAdminLog.deleteLifeBanner.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			content.setStatus(EStatus.get(status));
			content.setAdminuid(adminuid);
			if (so.update(content)) {
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
}
