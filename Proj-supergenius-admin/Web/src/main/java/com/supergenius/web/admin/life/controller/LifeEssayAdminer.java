package com.supergenius.web.admin.life.controller;

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
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.life.helper.LifeEssayHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.rule.LifeEssaySecondCommentCountRule;
import com.supergenius.xo.life.service.EssaySO;

/**
 * 动态管理controller
 * 
 * @author ChenQi
 * @date 2017年11月14日18:18:59
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class LifeEssayAdminer extends BaseController {

	@Autowired
	private EssaySO so;
	
	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 进入后台评论管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/lifeessay" }, method = RequestMethod.GET)
	public String statistic(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.lifeessay.name());
		model.put(ViewKeyDict.site, ESite.life.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.lifeessay, Locale.CHINA));
		model.put(ViewKeyDict.commentsCount, so.getCountByType(EComment.comment));
		model.put(ViewKeyDict.praiseCount, so.getCountByType(EComment.praise));
		model.put(ViewKeyDict.allCount, LifeEssayHP.getUserCount());
		return "dolifeessay";
	}
	
	/**
	 * 得到commentslist
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/life/ajax/lifeessay/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> essay_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = LifeEssayHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除评论
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/life/ajax/lifeessay/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> essay_delete(String[] ids) {
		Essay essay = so.get(ids[0]);
		if (essay != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setOperation(EAdminLog.deleteLifeEssay.getName());
			adminLog.setData(EAdminLog.deleteLifeEssay.getName());
			adminLog.setDesc(EAdminLog.deleteLifeEssay.getName());
			adminLog.setDataid(ids[0]);
			essay.setStatus(EStatus.deleted);
			so.update(essay);
			if (StrUtil.isNotEmpty(essay.getTouid())) {
				Rule rule = new LifeEssaySecondCommentCountRule(essay.getData());
				RedisUtil.decr(rule);
			}
			FileUtil.delete(SysConf.SerialBasePath + "/life/"+ essay.getCid() + SysConf.SerialEssayPath);
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
	@RequestMapping(value = "/life/ajax/lifeessay/status", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStatus(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			Essay essay = so.get(uid);
			String adminUid = null;
			if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {
				adminUid = AdminHP.getAdminUid();
			}
			if (StrUtil.isNotEmpty(essay.getTouid())) {
				Essay firstEssay = so.get(essay.getTouid());
				if (firstEssay != null) {
					if (firstEssay.getStatus() == EStatus.enable) {
						Rule rule = new LifeEssaySecondCommentCountRule(essay.getData());
						if (essay.getStatus() == EStatus.enable) {
							RedisUtil.decr(rule);
						} else {
							RedisUtil.incr(rule);
						}
					}
				}
			}
			if (essay.getStatus() == EStatus.enable) {
				essay.setStatus(EStatus.disable);
			} else {
				essay.setStatus(EStatus.enable);
			}
			essay.setAdminuid(adminUid);
			if (so.update(essay)) {
				FileUtil.delete(SysConf.SerialBasePath + "/life/"+ essay.getCid() + SysConf.SerialEssayPath);
				return success();
			}
		}
		return result(MsgKeyDict.editFailed);

	}
}