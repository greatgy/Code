package com.supergenius.web.admin.life.controller;

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
import com.supergenius.web.admin.life.helper.LifeTopicHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.service.TopicSO;

/**
 * 天才人生话题管理
 * 
 * @author JiaShitao
 * @date 2018年5月10日18:09:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeTopicAdminer extends BaseController {

	@Autowired
	private TopicSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 话题管理
	 * 
	 * @author JiaShitao
	 * @date 2018年5月10日17:24:12
	 * @return String
	 */
	@RequestMapping(value = "/life/lifetopic", method = RequestMethod.GET)
	public String topic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifetopic.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifetopic, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.catelogueList, LifeTopicHP.getCatalogueList());
		model.put(ViewKeyDict.photopath, SysConf.LifePhotoPath);
		return "dolifetopic";
	}

	/**
	 * 显示列表
	 * 
	 * @author JiaShitao
	 * @date 2018年5月10日18:55:26
	 * @return String
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> topic_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeTopicHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加列表
	 * 
	 * @author JiaShitao
	 * @date 2018年5月14日18:55:05
	 * @return String
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topic_add(String keywords, String title, String[] imgdata, String author, String cid, String origin, String type_radio, String summary, String content) {
		Topic topic = new Topic();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(type_radio)) {
			topic.setTitle(title);
			topic.setKind(Integer.parseInt(type_radio));
			topic.setContent(content);
		} else {
			return result(MsgKeyDict.addFailed);
		}
		topic.setSummary(summary);
		topic.setAuthor(author);
		topic.setOrigin(origin);
		topic.setCid(Long.parseLong(cid));
		topic.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		topic.setType(1);
		if (StrUtil.isNotEmpty(imgdata)) {
			topic.setImgoriginal(imgdata[0]);
			topic.setImgbig(imgdata[1]);
			topic.setImgmedium(imgdata[2]);
			topic.setImglittle(imgdata[3]);
		}
		if (StrUtil.isNotEmpty(adminUid)) {
			topic.setAdminuid(adminUid);
		}
		if (StrUtil.isNotEmpty(keywords)) {
			topic.setKeywords(keywords);
		}
		if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
			LifeTopicHP.deleteTopic();
			topic.setExamine(1);
		} else {
			topic.setExamine(0);
		}
		if (so.add(topic)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 删除话题
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月14日15:25:36
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_delete(String[] ids) {
		for (String id : ids) {
			Topic topic = so.get(id);
			if (topic != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.lifetopic.toInt());
				adminLog.setOperation(EAdminLog.deleteLifeTopic.getName());
				adminLog.setData(EAdminLog.deleteLifeTopic.getName());
				adminLog.setDesc(EAdminLog.deleteLifeTopic.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				topic.setStatus(EStatus.deleted);
				if (so.update(topic)) {
					if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
						LifeTopicHP.deleteTopic();
					}
					return success();
				}
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 冻结解冻
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月14日15:25:40
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topic_status(String uid, @PathVariable int status) {
		Topic topic = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (topic != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.lifetopic.toInt());
			adminLog.setOperation(EAdminLog.deleteLifeTopic.getName());
			adminLog.setData(EAdminLog.deleteLifeTopic.getName());
			adminLog.setDesc(EAdminLog.deleteLifeTopic.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			topic.setStatus(EStatus.get(status));
			topic.setAdminuid(adminuid);
			if (so.update(topic)) {
				if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
					LifeTopicHP.deleteTopic();
				}
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 审核通过
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年5月14日15:25:40
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/examine/{examine:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topic_examine(String uid, @PathVariable int examine) {
		Topic topic = so.get(uid);
		String adminuid = AdminHP.getAdminUid();
		if (topic != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.lifetopic.toInt());
			adminLog.setOperation(EAdminLog.updateLifeTopic.getName());
			adminLog.setData(EAdminLog.updateLifeTopic.getName());
			adminLog.setDesc(EAdminLog.updateLifeTopic.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			topic.setExamine(examine);
			topic.setAdminuid(adminuid);
			if (so.update(topic)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 话题编辑
	 * 
	 * @param uid
	 * @param title
	 * @param content
	 * @return
	 * @author Jiashitao
	 * @date 2018年5月14日18:56:15
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topic_edit(String[] imgdata,String uid, String title, String content) {
		Topic topic = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (topic != null) {
			topic.setTitle(title);
			topic.setContent(content);
			if (StrUtil.isNotEmpty(imgdata)) {
				topic.setImgoriginal(imgdata[0]);
				topic.setImgbig(imgdata[1]);
				topic.setImgmedium(imgdata[2]);
				topic.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminUid);
			adminLog.setChannel(EChannel.lifetopic.toInt());
			adminLog.setDataid(topic.getUid());
			adminLog.setDesc(EAdminLog.updateLifeTopic.getName());
			adminLog.setData(EAdminLog.updateLifeTopic.getName());
			adminLog.setOperation(EAdminLog.updateLifeTopic.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				topic.setAdminuid(adminUid);
			}
			if (so.update(topic)) {
				if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
					LifeTopicHP.deleteTopic();
				}
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
	 * @author JiaShitao
	 * @date 2018年5月14日15:25:49
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_top(String[] ids) {
		Topic topic;
		for (String uid : ids) {
			topic = so.get(uid);
			topic.setIstop(1);
			topic.setToptime(new DateTime(DateTimeZone.forOffsetHours(8)));
			if (!so.update(topic)) {
				return result(MsgKeyDict.updateFailed);
			}
		}
		return success();
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月14日15:25:53
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		Topic topic;
		for (String uid : ids) {
			topic = so.get(uid);
			topic.setIstop(0);
			if (!so.update(topic)) {
				return result(MsgKeyDict.updateFailed);
			}
		}
		return success();
	}

	/**
	 * 获取置顶的个数
	 * 
	 * @author JiaShitao
	 * @date 2018年5月14日15:25:56
	 */
	@RequestMapping(value = "/life/ajax/lifetopic/topcount/{cid:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> topic_topcount(Map<String, Object> model, @PathVariable Long cid, HttpServletRequest request) {
		model.put(MapperDict.count, LifeTopicHP.getTopTopicCount(cid));
		return json(model, Json.webStrategy);
	}
}
