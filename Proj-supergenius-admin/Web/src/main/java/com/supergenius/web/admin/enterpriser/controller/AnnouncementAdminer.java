package com.supergenius.web.admin.enterpriser.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.enterpriser.hellper.AnnouncementHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Announcement;
import com.supergenius.xo.enterpriser.service.AnnouncementSO;

/**
 * 公告管理
 * 
 * @author LiJiacheng
 */
@Controller(value="enterpriserAnnouncementController")
@RequestMapping(value = UriConf.baseAdminPath)
public class AnnouncementAdminer extends BaseController {

	@Autowired
	AnnouncementSO announcementSO;

	/**
	 * 打开页面
	 * 
	 * @param map
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/announcement" }, method = RequestMethod.GET)
	public String announcement(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.announcement.name());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.announcement, Locale.CHINA));
		return "doenannouncement";
	}

	/**
	 * 查询时组织的数据
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> announcement_list(Map<String, Object> model,HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AnnouncementHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加社区公告
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> announcement_add(Announcement announcement, String expiretime) {
		int sn = AnnouncementHP.getAnnouncementSn();
		if (-1 == sn) {
			return result(MsgKeyDict.addFailed);
		} else if (null != announcement.getContent() && !"".equals(announcement.getContent())) {
			announcement.setEndtime(DateTime.parse(expiretime));
			announcement.setSn(sn);
			announcement.setAddtime(DateTime.now());
			if (announcementSO.add(announcement)) {
				return success();
			} else {
				return result(MsgKeyDict.addFailed);
			}
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 编辑社区公告
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> announcement_edit(String uid, int cid,String title, String content, String endtimeStr) {
		Announcement oldAnnouncement = announcementSO.get(uid);
		oldAnnouncement.setTitle(title);
		oldAnnouncement.setCid(cid);
		oldAnnouncement.setContent(content);
		oldAnnouncement.setEndtime(DateTime.parse(endtimeStr.replaceAll(MapperDict.starttimeformat, "")));
		if (null != content && !"".equals(content) && announcementSO.update(oldAnnouncement)) {
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 删除社区公告
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> announcement_delete(String ids) {
		if (announcementSO.delete(ids)) {
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 社区公告冻结、解冻
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/status/{status:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> announcement_status(String ids, @PathVariable int status) {
		if (announcementSO.update(EStatus.get(status), ids)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 社区公告置顶
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/enterpriser/ajax/announcement/istop/{istop:\\d+}" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> announcement_istop(String ids, @PathVariable int istop) {
		if (0 == istop && announcementSO.setTop(ids, istop == 0 ? false : true)) {
			return success();
		} else if (announcementSO.checkIsTop() && announcementSO.setTop(ids, istop == 0 ? false : true)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}

}
