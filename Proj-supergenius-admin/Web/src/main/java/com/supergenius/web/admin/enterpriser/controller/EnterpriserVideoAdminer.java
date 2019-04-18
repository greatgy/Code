package com.supergenius.web.admin.enterpriser.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserVideoHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.enterpriser.entity.Video;
import com.supergenius.xo.enterpriser.service.VideoSO;

/**
 * 引资购商视频管理
 * 
 * @author YangGuang
 * @date 2018年1月10日13:45:49
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserVideoAdminer extends BaseController {

	@Autowired
	private VideoSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 视频管理页面
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:46:28
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/enterpriservideo", method = RequestMethod.GET)
	public String photo(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterpriservideo.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.enterpriservideo, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.enterpriser.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.EnterpriserPhotoPath);
		return "doenterpriservideo";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:52:43
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> photo_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = EnterpriserVideoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加视频
	 * 
	 * @param imgdata
	 * @param content
	 * @param title
	 * @param summary
	 * @return
	 * @author YangGuang
	 * @date 2018年1月10日14:11:23
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String content, String title, String summary, String[] imgdata, String keywords) {
		if (StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(imgdata)) {
			Video video = new Video();
			String adminUid = AdminHP.getAdminUid();
			if (StrUtil.isNotEmpty(summary)) {
				video.setSummary(summary);
			}
			if (StrUtil.isNotEmpty(keywords)) {
				video.setKeywords(keywords);
			}
			video.setContent(content);
			video.setTitle(title);
			if (StrUtil.isNotEmpty(imgdata)) {
				video.setImgoriginal(imgdata[0]);
				video.setImgbig(imgdata[1]);
				video.setImgmedium(imgdata[2]);
				video.setImglittle(imgdata[3]);
			}
			video.setCid(1);
			if (StrUtil.isNotEmpty(adminUid)) {
				video.setAdminuid(adminUid);
			}
			if (so.add(video)) {
				EnterpriserVideoHP.handleCache();
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 删除视频
	 * 
	 * @param ids
	 * @return
	 * @author YangGuang
	 * @data 2018年1月10日14:11:51
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids) {
		for (String id : ids) {
			Video video = so.get(id);
			if (video != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.enterpriservideo.toInt());
				adminLog.setOperation(EAdminLog.deleteEnterpriserVideo.getName());
				adminLog.setData(EAdminLog.deleteEnterpriserVideo.getName());
				adminLog.setDesc(EAdminLog.deleteEnterpriserVideo.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				video.setStatus(EStatus.deleted);
				if (so.update(video)) {
					EnterpriserVideoHP.handleCache();
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 冻结解冻
	 * 
	 * @param uid
	 * @return
	 * @author 杨光
	 * @data 2018年1月10日14:26:11
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
		Video video = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (video != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.enterpriservideo.toInt());
			adminLog.setOperation(EAdminLog.updateEnterpriserVideo.getName());
			adminLog.setData(EAdminLog.updateEnterpriserVideo.getName());
			adminLog.setDesc(EAdminLog.updateEnterpriserVideo.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			video.setStatus(EStatus.get(status));
			video.setAdminuid(adminuid);
			if (so.update(video)) {
				EnterpriserVideoHP.handleCache();
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 视频编辑
	 * 
	 * @param uid
	 * @param title
	 * @param imgdata
	 * @param content
	 * @param summary
	 * @return
	 * @author 杨光
	 * @date 2018年1月10日14:32:09
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String content, String title, String uid, String[] imgdata, String summary, String keywords) {
		Video video = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isEmpty(title) || StrUtil.isEmpty(content)) {
			return result(MsgKeyDict.updateFailed);
		}
		if (video != null) {
			if (StrUtil.isNotEmpty(summary)) {
				video.setSummary(summary);
			}
			if (StrUtil.isNotEmpty(keywords)) {
				video.setKeywords(keywords);
			}
			video.setContent(content);
			video.setTitle(title);
			if (StrUtil.isNotEmpty(imgdata)) {
				video.setImgoriginal(imgdata[0]);
				video.setImgbig(imgdata[1]);
				video.setImgmedium(imgdata[2]);
				video.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.enterpriservideo.toInt());
			adminLog.setDataid(video.getUid());
			adminLog.setDesc(EAdminLog.updateEnterpriserVideo.getName());
			adminLog.setData(EAdminLog.updateEnterpriserVideo.getName());
			adminLog.setOperation(EAdminLog.updateEnterpriserVideo.getName());
			adminLogSO.add(adminLog);
			if (StrUtil.isNotEmpty(adminUid)) {
				video.setAdminuid(adminUid);
			}
			if (so.update(video)) {
				EnterpriserVideoHP.handleCache();
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 设置置顶
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2018年1月10日14:37:46
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String[] ids) {
		if (so.setRecommend(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2018年1月10日14:37:55
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		if (so.setRecommend(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 获取置顶的个数
	 * 
	 * @author yangguang
	 * @date 2018年1月10日14:38:41
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriservideo/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, EnterpriserVideoHP.getTopVideoCount());
		return json(model, Json.webStrategy);
	}
}
