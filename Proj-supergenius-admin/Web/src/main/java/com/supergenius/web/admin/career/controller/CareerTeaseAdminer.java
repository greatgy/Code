package com.supergenius.web.admin.career.controller;

import java.util.HashMap;
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
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.core.rule.CareerTeaseSecondCommentCountRule;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.TeaseHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Tease;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.career.service.TeaseSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 吐槽专区或鬼话管理controller
 * 
 * @author ChenQi
 * @date 2017年11月14日18:18:59
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CareerTeaseAdminer extends BaseController {

	@Autowired
	private TeaseSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/{channel:[a-z]+}" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		EChannel eChannel = EChannel.get(channel);
		model.put(ViewKeyDict.channel, eChannel.name());
		model.put(ViewKeyDict.site, ESite.career.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(eChannel, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.allCount, String.valueOf(so.getCountByfromuseruid(eChannel)));
		return "docareertease";
	}
	
	/**
	 * 编辑职场吐槽-管理专区
	 * 
	 * @author loupengyu
	 * @param Map<String,Object>
	 * @data 
	 */
	@RequestMapping(value = "/career/ajax/careercomplainarea/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tease1_edit(Map<String, Object> model, String uid, String createtimeStr, String fromusername, String content, String status, HttpServletRequest request) {
			Tease tease = so.get(uid);
			if (tease != null) {
				tease.setFromusername(fromusername);
				tease.setContent(content);
				tease.setStatus(EStatus.get(Integer.parseInt(status)));
			}
			tease.setCreatetime(DateUtil.parse(createtimeStr));
		    if(so.update(tease)){
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.careercomplainarea.toInt());
				adminLog.setDataid(tease.getUid());
				adminLog.setDesc(EAdminLog.updateCareercomplainarea.getName());
				adminLog.setData(EAdminLog.updateCareercomplainarea.getName());
				adminLog.setOperation(EAdminLog.updateCareercomplainarea.getName());
				return success();
			}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 编辑职场鬼话
	 * 
	 * @author loupengyu
	 * @param Map<String,Object>
	 * @data
	 */
	@RequestMapping(value = "/career/ajax/careertease/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tease_edit(Map<String, Object> model, String uid, String createtimeStr, String fromusername, String content, String status, HttpServletRequest request) {
			Tease tease = so.get(uid);
			if (tease != null) {
				tease.setFromusername(fromusername);
				tease.setContent(content);
				tease.setStatus(EStatus.get(Integer.parseInt(status)));
			}
			tease.setCreatetime(DateUtil.parse(createtimeStr));
		    if(so.update(tease)){
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careertease.toInt());
			adminLog.setDataid(tease.getUid());
			adminLog.setDesc(EAdminLog.updateCareertease.getName());
			adminLog.setData(EAdminLog.updateCareertease.getName());
			adminLog.setOperation(EAdminLog.updateCareertease.getName());
			return success();
			}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/career/ajax/{channel:[a-z]+}/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tease_list(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		EChannel eChannel = EChannel.get(channel);
		Map<String, Object> searchMap = TeaseHP.query(model, eChannel);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/career/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tease_delete(String[] ids, @PathVariable String channel) {
		Tease tease = so.get(ids[0]);
		EChannel eChannel = EChannel.get(channel);
		if (tease != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(eChannel.toInt());
			adminLog.setOperation(EAdminLog.deleteCareerTease.getName());
			adminLog.setData(EAdminLog.deleteCareerTease.getName());
			adminLog.setDesc(EAdminLog.deleteCareerTease.getName());
			adminLog.setDataid(ids[0]);
			tease.setStatus(EStatus.deleted);
			so.update(tease);
			if (StrUtil.isNotEmpty(tease.getTouid())) {
				Rule rule = new CareerTeaseSecondCommentCountRule(tease.getData());
				RedisUtil.decr(rule);
			}
			adminLogSO.add(adminLog);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

	/**
	 * 冻结或解冻
	 * 
	 * @author ChenQi
	 * @param Map<String,Object>
	 * @data 2017年11月14日18:19:12
	 */
	@RequestMapping(value = "/career/ajax/{channel:[a-z]+}/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			Tease tease = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isNotEmpty(tease.getTouid())) {
				Tease firstTease = so.get(tease.getTouid());
				if (firstTease != null) {
					if (firstTease.getStatus() == EStatus.enable) {
						Rule rule = new CareerTeaseSecondCommentCountRule(tease.getData());
						if (tease.getStatus() == EStatus.enable) {
							RedisUtil.decr(rule);
						} else {
							RedisUtil.incr(rule);
						}
					}
				}
			}
			if (tease.getStatus() == EStatus.enable) {
				tease.setStatus(EStatus.disable);
			} else {
				tease.setStatus(EStatus.enable);
			}
			tease.setAdminuid(adminUid);
			if (so.update(tease)) {
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);

	}
	
	/**
	 * 判断吐槽或鬼话是否已经有所属评论
	 * 
	 * @author YangGuang
	 * @date 2018年6月6日17:54:02
	 */
	@RequestMapping(value = "/career/ajax/{channel:[a-z]+}/teasecount/{uid:.{32}}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, @PathVariable String uid, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		map.put(MapperDict.data, uid);
		map.put(MapperDict.type, EComment.comment);
		model.put(MapperDict.count, so.getCount(map));
		return json(model, Json.webStrategy);
	}
}