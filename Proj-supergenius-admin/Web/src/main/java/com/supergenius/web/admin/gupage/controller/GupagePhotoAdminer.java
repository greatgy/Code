package com.supergenius.web.admin.gupage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.supergenius.web.admin.gupage.helper.GupagePhotoHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.gupage.entity.Photo;
import com.supergenius.xo.gupage.service.PhotoSO;

/**
 * 顾雏军专栏图片管理
 * 
 * @author YangGuang
 * @date 2018年1月10日13:45:49
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class GupagePhotoAdminer extends BaseController {

	@Autowired
	private PhotoSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 图片管理页面
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:46:28
	 * @return String
	 */
	@RequestMapping(value = "/gupage/gupagephoto", method = RequestMethod.GET)
	public String photo(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.gupagephoto.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.gupagephoto, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.gupage.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.GupagePhotoPath);
		return "dogupagephoto";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2018年1月10日13:52:43
	 * @return String
	 */
	@RequestMapping(value = "/gupage/ajax/gupagephoto/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> photo_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = GupagePhotoHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加图片
	 * 
	 * @param imgdata
	 * @param summary
	 * @return
	 * @author YangGuang
	 * @date 2018年1月10日14:11:23
	 */
	@RequestMapping(value = "/gupage/ajax/gupagephoto/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String summary, String[] imgdata) {
		if (StrUtil.isNotEmpty(summary) && StrUtil.isNotEmpty(imgdata)) {
			Photo photo = new Photo();
			String adminUid = AdminHP.getAdminUid();
			photo.setSummary(summary);
			photo.setImgoriginal(imgdata[0]);
			photo.setImgbig(imgdata[1]);
			photo.setImgmedium(imgdata[2]);
			photo.setImglittle(imgdata[3]);
			if (StrUtil.isNotEmpty(adminUid)) {
				photo.setAdminuid(adminUid);
				photo.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				photo.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.add(photo)) {
				GupagePhotoHP.handleCache();
				return success();
			}
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 删除图片
	 * 
	 * @param ids
	 * @return
	 * @author YangGuang
	 * @data 2018年1月10日14:11:51
	 */
	@RequestMapping(value = "/gupage/ajax/gupagephoto/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids) {
		for (String id : ids) {
			Photo photo = so.get(id);
			if (photo != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.gupagephoto.toInt());
				adminLog.setOperation(EAdminLog.deleteGupagePhoto.getName());
				adminLog.setData(EAdminLog.deleteGupagePhoto.getName());
				adminLog.setDesc(EAdminLog.deleteGupagePhoto.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				photo.setStatus(EStatus.deleted);
				if (so.update(photo)) {
					GupagePhotoHP.handleCache();
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
	@RequestMapping(value = "/gupage/ajax/gupagephoto/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
		Photo photo = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (photo != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.gupagephoto.toInt());
			adminLog.setOperation(EAdminLog.updateGupagePhoto.getName());
			adminLog.setData(EAdminLog.updateGupagePhoto.getName());
			adminLog.setDesc(EAdminLog.updateGupagePhoto.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			photo.setStatus(EStatus.get(status));
			photo.setAdminuid(adminuid);
			photo.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			if (so.update(photo)) {
				GupagePhotoHP.handleCache();
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 图片编辑
	 * 
	 * @param uid
	 * @param imgdata
	 * @param summary
	 * @return
	 * @author 杨光
	 * @date 2018年1月10日14:32:09
	 */
	@RequestMapping(value = "/gupage/ajax/gupagephoto/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String[] imgdata, String summary) {
		Photo photo = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (photo != null) {
			photo.setSummary(summary);
			if (StrUtil.isNotEmpty(imgdata)) {
				photo.setImgoriginal(imgdata[0]);
				photo.setImgbig(imgdata[1]);
				photo.setImgmedium(imgdata[2]);
				photo.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.gupagephoto.toInt());
			adminLog.setDataid(photo.getUid());
			adminLog.setDesc(EAdminLog.updateGupagePhoto.getName());
			adminLog.setData(EAdminLog.updateGupagePhoto.getName());
			adminLog.setOperation(EAdminLog.updateGupagePhoto.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				photo.setAdminuid(adminUid);
				photo.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(photo)) {
				GupagePhotoHP.handleCache();
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
	@RequestMapping(value = "/gupage/ajax/gupagephoto/enable", method = RequestMethod.GET)
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
	@RequestMapping(value = "/gupage/ajax/gupagephoto/disable", method = RequestMethod.GET)
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
	@RequestMapping(value = "/gupage/ajax/gupagephoto/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, GupagePhotoHP.getTopPhotoCount());
		return json(model, Json.webStrategy);
	}
}
