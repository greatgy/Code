package com.supergenius.web.admin.life.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.admin.life.helper.LifeVideoHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Comments;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.service.CommentsSO;
import com.supergenius.xo.life.service.VideoSO;
import com.supergenius.xo.user.entity.User;

/**
 * 视频页面
 * 
 * @author YangGuang
 * @date 2018年5月11日16:32:51
 * @return String
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeVideoAdminer extends BaseController {

	@Autowired
	AdminLogSO adminLogSO;

	@Autowired
	private VideoSO so;

	@Autowired
	private CommentsSO commentsSO;
	/**
	 * 进入后台视频管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/lifevideo" }, method = RequestMethod.GET)
	public String content(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifevideo.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifevideo, Locale.CHINA));
		model.put(ViewKeyDict.photopath, SysConf.LifePhotoPath);
		return "dolifevideo";
	}

	/**
	 * 得到list
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/ajax/lifevideo/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeVideoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author YangGuang
	 * @typename Map<String,Object>
	 * @date 2018年5月11日16:36:03
	 */
	@RequestMapping(value = "/life/ajax/lifevideo/status", method = RequestMethod.GET)
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
	 * 点评/回复
	 * 
	 * @author YangGuang
	 * @date 2018年5月11日18:15:41
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/life/ajax/lifevideo/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> content_edit(Map<String, Object> model, String uid, String infodata, String notreply, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid)) {
			Video video = so.get(uid);
			if (StrUtil.isNotEmpty(infodata)) {
				Comments comments = new Comments();
				comments.setFromuid(video.getUid());
				comments.setFromuseruid(ViewKeyDict.defaultuid);
				comments.setFromuseroid(ViewKeyDict.defaultoid);
				comments.setFromusername(ViewKeyDict.system);
				User toUser = BaseUserHP.get(video.getUseruid());
				comments.setTousername(toUser.getUsername());
				comments.setTouseroid(toUser.getOid());
				comments.setTouseruid(toUser.getUid());
				comments.setType(EComment.major);
				comments.setChannel(ELifeChannel.problemcomments);
				comments.setContent(WebUtil.clearXSS(infodata));
				if (video.getState() == EState.waitReply) {
					comments.setTouid(ViewKeyDict.defaultuid);
				}
				commentsSO.add(comments);
				video.setState(EState.over);
			} else if (StrUtil.isNotEmpty(notreply) && notreply.equals("on")) {
				video.setState(EState.over);
			} 
			if (so.update(video)) {
				LifeVideoHP.sendMsg(video);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 删除视频
	 * 
	 * @param ids
	 * @return
	 * @author YangGuang
	 * @data 2018年5月14日11:05:09
	 */
	@RequestMapping(value = "/life/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> life_delete(String[] ids) {
		for (String id : ids) {
			Video video = so.get(id);
			if (video != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.lifevideo.toInt());
				adminLog.setOperation(EAdminLog.deleteLifeVideo.getName());
				adminLog.setData(EAdminLog.deleteLifeVideo.getName());
				adminLog.setDesc(EAdminLog.deleteLifeVideo.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				video.setStatus(EStatus.deleted);
				if (so.update(video)) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 获取点评信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/life/ajax/lifevideo/comments", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_comments(String uid, HttpServletResponse response) {
		Video video = so.get(uid);
		List<Comments> list = LifeVideoHP.getSecondMajor(video);
		return JsonUtil.toJson(list, Json.webStrategy);
	}
}
