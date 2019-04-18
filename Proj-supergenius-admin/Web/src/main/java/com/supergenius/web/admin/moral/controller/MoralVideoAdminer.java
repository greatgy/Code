package com.supergenius.web.admin.moral.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moral.helper.VideoHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Video;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.service.CommentSO;
import com.supergenius.xo.moral.service.VideoSO;

/**
 * 范例视频管理
 * @author liushaomin
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralVideoAdminer extends BaseController{

	@Autowired
	VideoSO so;
	
	@Autowired
	CommentSO commentSO;
	
	/**
	 * 打开视频管理
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/video", method = RequestMethod.GET)
	public String video(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.video.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.video, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		Map<String, String> typeMap = new HashMap<String, String>();
		for (EChapter item : EChapter.values()) {
			typeMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, typeMap);
		return "domoralvideo";
	}
	
	/**
	 * 查询组织数据
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/moral/ajax/video/list"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> video_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = VideoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加视频
	 * @param video
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/video/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_add(Video video, String file) {
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			video.setImgs(imgs);
		}
		if (so.add(video)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 编辑视频
	 * @param video
	 * @param file
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/video/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_edit(Video video, String file) {
		Video video2 = so.get(video.getUid());
		if (StringUtils.isNotEmpty(file)) {
			String[] imgs = file.split(BaseStrDict.comma);
			video2.setImgs(imgs);
		}
		video2.setName(video.getName());
		video2.setChapter(video.getChapter());
		video2.setCode(video.getCode());
		if (so.update(video2)) {
			return result(MsgKeyDict.editSuccess);
		}else {
			return result(MsgKeyDict.editFailed);
		}
	}
	
	/**
	 * 更新状态
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/video/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> video_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 删除视频
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/video/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> video_delete(String[] ids) {
		if (so.deleteByUids(ids)) {
			commentSO.deleteByFields(ids, EChannel.moralvideo);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
}
