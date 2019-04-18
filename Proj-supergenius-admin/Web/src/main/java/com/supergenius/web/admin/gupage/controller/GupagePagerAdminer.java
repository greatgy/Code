package com.supergenius.web.admin.gupage.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
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
import com.supergenius.web.admin.gupage.helper.GupagePagerHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.gupage.entity.Patent;
import com.supergenius.xo.gupage.enums.EPatent;
import com.supergenius.xo.gupage.service.PatentSO;

/**
 * 顾雏军专栏视频管理
 * 
 * @author YangGuang
 * @date 2018年1月10日13:45:49
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class GupagePagerAdminer extends BaseController {
	
	@Autowired
	private PatentSO so;

	@Autowired
	private AdminLogSO adminLogSO;
	/**
	 * 专利管理页面
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:46:28
	 * @return String
	 */
	@RequestMapping(value = "/gupage/pager/{channel:[a-z]+}", method = RequestMethod.GET)
	public String photo(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		EChannel eChannel = EChannel.get(channel);
		model.put(ViewKeyDict.channel, eChannel.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(eChannel, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.gupage.name());
		model.put(ViewKeyDict.photopath, SysConf.GupagePhotoPath);
		return "dogupagepager";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:52:43
	 * @return String
	 */
	@RequestMapping(value = "/gupage/ajax/{channel:[a-z]+}/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> photo_list(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = GupagePagerHP.query(model, channel);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加专利或论文
	 * 
	 * @param imgdata
	 * @param content
	 * @param title
	 * @param summary
	 * @return
	 * @author YangGuang
	 * @date 2018年1月10日14:11:23
	 */
	@RequestMapping(value = "/gupage/ajax/{channel:[a-z]+}/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String title, @RequestParam MultipartFile file, int type, int kind, String[] imgdata, String[] imgdata1) {
		Patent patent = new Patent();
		if (StrUtil.isNotEmpty(title)) {
			String adminUid = AdminHP.getAdminUid();
			patent.setTitle(title);
			patent.setKind(kind);
			if (StrUtil.isNotEmpty(imgdata)) {
				patent.setImgoriginal(imgdata[0]);
				patent.setImgbig(imgdata[1]);
				patent.setImgmedium(imgdata[2]);
				patent.setImglittle(imgdata[3]);
			} 
			if (kind == 0 && StrUtil.isNotEmpty(imgdata1)) {
				patent.setFilepath(imgdata1[0]);
			} else {
				if (file != null) {
					patent.setFilepath(FileUtil.uploadFile(file, SysConf.GupagePagerPath));
				}
			}
			patent.setType(EPatent.get(type));
			if (StrUtil.isNotEmpty(adminUid)) {
				patent.setAdminuid(adminUid);
				patent.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				patent.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.add(patent)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 冻结解冻
	 * 
	 * @param uid
	 * @return
	 * @author 杨光
	 * @data 2018年1月10日14:26:11
	 */
	@RequestMapping(value = "/gupage/ajax/pager/{channel:[a-z]+}/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable String channel, @PathVariable int status) {
		Patent patent = so.get(uid);
		EChannel eChannel = EChannel.get(channel);
		String adminuid = AdminHP.getAdminUid();
		if (patent != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(eChannel.toInt());
			adminLog.setOperation(EAdminLog.updateGupagePager.getName());
			adminLog.setData(EAdminLog.updateGupagePager.getName());
			adminLog.setDesc(EAdminLog.updateGupagePager.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			patent.setStatus(EStatus.get(status));
			patent.setAdminuid(adminuid);
			patent.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			if (so.update(patent)) {
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 删除视频
	 * 
	 * @param ids
	 * @return
	 * @author YangGuang
	 * @data 2018年1月10日14:11:51
	 */
	@RequestMapping(value = "/gupage/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids, @PathVariable String channel) {
		EChannel eChannel = EChannel.get(channel);
		for (String id : ids) {
			Patent patent = so.get(id);
			if (patent != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(eChannel.toInt());
				adminLog.setOperation(EAdminLog.deleteGupagePager.getName());
				adminLog.setData(EAdminLog.deleteGupagePager.getName());
				adminLog.setDesc(EAdminLog.deleteGupagePager.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				patent.setStatus(EStatus.deleted);
				if (so.update(patent)) {
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 专利编辑
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
	@RequestMapping(value = "/gupage/ajax/{channel:[a-z]+}/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String title, String uid, @PathVariable String channel, @RequestParam MultipartFile file, int type, String[] imgdata, String[] imgdata1) {
		Patent patent = so.get(uid);
		EChannel eChannel = EChannel.get(channel);
		if (patent != null && StrUtil.isNotEmpty(title)) {
			String adminUid = AdminHP.getAdminUid();
			patent.setTitle(title);
			if (StrUtil.isNotEmpty(imgdata)) {
				patent.setImgoriginal(imgdata[0]);
				patent.setImgbig(imgdata[1]);
				patent.setImgmedium(imgdata[2]);
				patent.setImglittle(imgdata[3]);
			} 
			if (patent.getKind() == 0 && StrUtil.isNotEmpty(imgdata1)) {
				patent.setFilepath(imgdata1[2]);
			} else {
				if (file != null) {
					patent.setFilepath(FileUtil.uploadFile(file, SysConf.GupagePagerPath));
				}
			}
			patent.setType(EPatent.get(type));
			if (StrUtil.isNotEmpty(adminUid)) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminUid);
				adminLog.setChannel(eChannel.toInt());
				adminLog.setOperation(EAdminLog.updateGupagePager.getName());
				adminLog.setData(EAdminLog.updateGupagePager.getName());
				adminLog.setDesc(EAdminLog.updateGupagePager.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				patent.setAdminuid(adminUid);
				patent.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				patent.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(patent)) {
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}
}
