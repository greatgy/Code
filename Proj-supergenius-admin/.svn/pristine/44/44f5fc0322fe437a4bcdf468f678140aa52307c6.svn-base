package com.supergenius.web.admin.manger.controller;

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
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.manager.helper.VideoHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.Video;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.manager.service.VideoSO;

/**
 * 视频管理
 * @author XieMing
 * @date 2016-10-20 上午11:02:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ManagerVideoAdminer extends BaseController {

	@Autowired
	VideoSO so;
	@Autowired
	PkScheduleSO pkScheduleSO;
	@Autowired
	AppReplySO appReplySO;
	
	/**
	 * 跳转到视频管理页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-20 上午11:08:37
	 */
	@RequestMapping(value = "/manager/video", method = RequestMethod.GET)
	public String video(Map<String, Object> model, HttpServletRequest request) {
		model.putAll(VideoHP.loadMsg());
		model.put(ViewKeyDict.pk, "0");
		model.put(ViewKeyDict.reply, "0");
		return "domanagervideo";
	}
	
	/**
	 * 加载数据
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-20 下午6:43:08
	 */
	@RequestMapping(value = { "/manager/ajax/managervideo/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> video_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = VideoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 视频的冻结 解冻
	 * @param ids
	 * @param status
	 * @param dopwd
	 * @param desc
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午2:15:53
	 */
	@RequestMapping(value = { "/manager/ajax/managervideo/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> member_status(String ids, @PathVariable int status, String dopwd, String desc) {
		Video video = so.get(ids);
		if (AdminHP.isDopwd(dopwd) && video != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.managervideo.toInt());
			adminLog.setDesc(desc);
			adminLog.setDataid(video.getUid());
			switch (status) {
			case 0:
				video.setStatus(EStatus.disable);
				break;
			case 1:
				video.setStatus(EStatus.enable);
				break;
			}
			adminLog.setData(EAdminLog.updateManagerVideoStatus.getName());
			adminLog.setOperation(EAdminLog.updateManagerVideoStatus.getName());
			if (so.updateStatus(video.getUid(), video.getStatus(), adminLog)) {
				return success();
			} else {
				return result(MsgKeyDict.updateFailed);
			}
		} else {
			return result(MsgKeyDict.dopwdIsWrong);
		}
	}
	
	/**
	 * 视频的设置 取消推荐
	 * @param ids
	 * @param isrecommend
	 * @param dopwd
	 * @param desc
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午2:44:33
	 */
	@RequestMapping(value = { "/manager/ajax/managervideo/recommend/{isrecommend:\\d+}" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> member_recommend(String ids, @PathVariable int isrecommend) {
		Video video = so.get(ids);
		AdminLog adminLog = new AdminLog();
		adminLog.setAdminuid(AdminHP.getAdminUid());
		adminLog.setChannel(EChannel.managervideo.toInt());
		adminLog.setDesc("");
		adminLog.setDataid(video.getUid());
		switch (isrecommend) {
		case 0:
			video.setIsrecommend(false);
			break;
		case 1:
			video.setIsrecommend(true);
			break;
		}
		adminLog.setData(EAdminLog.updateManagerVideoRecommend.getName());
		adminLog.setOperation(EAdminLog.updateManagerVideoRecommend.getName());
		if (so.updateStatus(video.getUid(), video.isIsrecommend(), adminLog)) {
			return success();
		} else {
			return result(MsgKeyDict.updateFailed);
		}
	}

	/**
	 * 编辑视频信息
	 * @param video
	 * @param name
	 * @param sn
	 * @param channelfrom
	 * @param major
	 * @param price
	 * @param code
	 * @param summary
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-31 下午4:18:45
	 */
	@RequestMapping(value = "/manager/ajax/managervideo/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managervideo_edit(Video video, String uid, HttpServletRequest request) {
		Video video1 = so.get(uid);
		video1.setTitle(video.getTitle());
		video1.setSn(video.getSn());
		video1.setCode(video.getCode());
		video1.setChannelfrom(video.getChannelfrom());
		video1.setMajor(video.getMajor());
		video1.setDesc(video.getDesc());
		if(video.getOriginal() != null) {
			video1.setImgs((video.getOriginal().split(ViewKeyDict.comma)));
		}
		video1.setPrice(video.getPrice());
		video1.setSummary(video.getSummary());
		so.update(video1);
		return success();
	}
	
	/**
	 * 添加挑战视频
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-11-1 上午10:03:56
	 */
	@RequestMapping(value = "/manager/video/pk/{uid:\\d+}", method = RequestMethod.GET)
	public String pk_addvideo(Map<String, Object> model, HttpServletRequest request) {
		model.putAll(VideoHP.loadMsg());
		model.put(ViewKeyDict.pk, true);
		model.put(ViewKeyDict.reply, false);
		return "domanagervideoadd";
	}
	
	/**
	 * 添加答辩视频
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-11-1 上午10:05:08
	 */
	@RequestMapping(value = "/manager/video/reply/{uid:\\d+}", method = RequestMethod.GET)
	public String reply_addvideo(Map<String, Object> model, HttpServletRequest request) {
		model.putAll(VideoHP.loadMsg());
		model.put(ViewKeyDict.pk, false);
		model.put(ViewKeyDict.reply, true);
		return "domanagervideoadd";
	}
	
	/**
	 * 添加视频
	 * @param video
	 * @param file
	 * @return
	 * @author XieMing
	 * 2016-11-1 上午10:49:07
	 */
	@RequestMapping(value = { "/manager/ajax/managervideo/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managervideo_add(Video video, String file) {
		if(file != null) {
			video.setImgs(file.split(ViewKeyDict.comma));
		}
		video.setVideouid("");
		video.setSn(AutoIncrHP.getVideosn());
		video.setName(video.getTitle());
		so.add(video);
		return result(MsgKeyDict.addSuccess);
	}
	
	/**
	 * 跳转到添加挑战视频页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-20 上午11:08:37
	 */
	@RequestMapping(value = "/manager/video/pk/add/{uid:.{32}}", method = RequestMethod.GET)
	public String add_pk(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request) {
		PKSchedule pkSchedule = pkScheduleSO.get(uid);
		model.putAll(VideoHP.loadMsg());
		model.put(ViewKeyDict.pk, "1");
		model.put(ViewKeyDict.reply, "0");
		model.put(ViewKeyDict.keyword, pkSchedule.getMajor().toString());
		model.put(ViewKeyDict.refuid, uid);
		model.put(ViewKeyDict.videoname, pkSchedule.getName());
		return "domanagervideo";
	}
	
	/**
	 * 跳转到添加答辩视频页面
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing
	 * 2016-10-20 上午11:08:37
	 */
	@RequestMapping(value = "/manager/video/reply/add/{uid:.{32}}", method = RequestMethod.GET)
	public String add_reply(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request) {
		AppReply appReply = appReplySO.get(uid);
		model.putAll(VideoHP.loadMsg());
		model.put(ViewKeyDict.pk, "0");
		model.put(ViewKeyDict.reply, "1");
		model.put(ViewKeyDict.keyword, appReply.getMajor().toString());
		model.put(ViewKeyDict.refuid, uid);
		model.put(ViewKeyDict.videoname, appReply.getName());
		return "domanagervideo";
	}

}
