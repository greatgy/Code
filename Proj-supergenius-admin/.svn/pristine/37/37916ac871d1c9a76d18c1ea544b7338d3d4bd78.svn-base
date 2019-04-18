package com.supergenius.web.admin.user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.genius.core.serial.utils.SerialUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.UserSO;

/**
 * 用户列表controller
 * 
 * @author ChenQi
 * @date 2017年10月20日10:28:52
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class VisitorAdminer extends BaseController {

	@Autowired
	UserSO so;

	/**
	 * 跳转到列表页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author ChenQi 2017年10月20日10:28:52
	 */
	@RequestMapping(value = "/user/visitor", method = RequestMethod.GET)
	public String visitor(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.visitor.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.visitor, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.user.name());
		model.put(ViewKeyDict.photopath, SysConf.VisitorAvatarPath);
		return "dovisitor";
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author ChenQi 2017年10月20日10:28:52
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/user/ajax/visitor/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> visitor_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		List<Visitor> list = new ArrayList<Visitor>();
		List<Visitor> reusultlist = new ArrayList<Visitor>();
		String path = SysConf.SerialBasePath + SysConf.SerialUserVisitorPath;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
		} else {
			String imgPath = SysConf.ImgSiteBasePath + SysConf.VisitorAvatarPath;
			Visitor visitor = new Visitor();
			visitor.setNickname("游客");
			File dir = new File(imgPath);
			if (dir.exists() && dir.isDirectory()) {
				visitor.setAvatar(SysConf.VisitorAvatarPath + SysConf.Separator_Directory + SysConf.DefaultVisitorAvatar);
			} else {
				dir.mkdir();
			}
			list.add(visitor);  
			SerialUtil.serializeToJson(list, path, Json.cacheStrategy);
		}
		if (list.size() >= pager.getStartIndex()+pager.getPageSize()) {
			reusultlist = list.subList(pager.getStartIndex(), pager.getStartIndex()+pager.getPageSize());
		} else {
			reusultlist = list.subList(pager.getStartIndex(), list.size());
		}
		searchMap.put(ViewKeyDict.rows, reusultlist);
		searchMap.put(ViewKeyDict.total, list.size());
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加昵称
	 * 
	 * @author ChenQi
	 * @date 2017年10月20日10:28:41
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/ajax/visitor/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> visitor_add(Map<String, Object> model, String nickname, String[] imgdata, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(nickname)) {
			Visitor visitor = new Visitor();
			visitor.setNickname(nickname);
			visitor.setAvatar(imgdata[2]);
			String path = SysConf.SerialBasePath + SysConf.SerialUserVisitorPath;
			List<Visitor> list = new ArrayList<Visitor>();
			list = SerialUtil.deserializeFromJson(path, list.getClass(), Json.cacheStrategy);
			list.add(visitor);  
			SerialUtil.serializeToJson(list, path, Json.cacheStrategy);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

}
