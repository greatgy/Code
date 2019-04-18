package com.supergenius.web.admin.official.controller;

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
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.official.helper.RecruitHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.official.entity.Recruit;
import com.supergenius.xo.official.enums.ERecruit;
import com.supergenius.xo.official.service.RecruitSO;

/**
 * 招聘信息控制器
 * 
 * @author LiJiacheng
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class RecruitAdminer extends BaseController {

	@Autowired
	RecruitSO so;

	/**
	 * 进入招聘管理
	 * 
	 * @param model
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/recruit", method = RequestMethod.GET)
	public String recruit(Map<String, Object> model) {
		model.put(ViewKeyDict.channel, EChannel.recruit.name());
		model.put(ViewKeyDict.site, EChannel.official.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.recruit, Locale.CHINA));
		Map<String, Object> channelMap = new HashMap<String, Object>();
		for (ERecruit item : ERecruit.values()) {
			channelMap.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, channelMap);
		return "dorecruit";
	}

	/**
	 * 进行查询，之后返回Json数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/ajax/recruit/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> recruit_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = RecruitHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加招聘信息
	 * 
	 * @param recruit
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/ajax/recruit/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recruit_add(Recruit recruit) {
		if (null == recruit.getType()) {
			return result(MsgKeyDict.addFailed);
		} else if (so.add(recruit)) {
			return success();
		} else {
			return result(MsgKeyDict.addFailed);
		}
	}

	/**
	 * 保存编辑的招聘信息
	 * 
	 * @param recruit
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/ajax/recruit/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recruit_edit(Recruit recruit) {
		if (null == recruit.getType()) {
			return result(MsgKeyDict.editFailed);
		} else if (so.update(recruit)) {
			return success();
		} else {
			return result(MsgKeyDict.editFailed);
		}
	}

	/**
	 * 删除招聘信息
	 * 
	 * @param recruit
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/ajax/recruit/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> recruit_delete(String[] ids) {
		if (so.delete(ids)) {
			return success();
		} else {
			return result(MsgKeyDict.deleteFailed);
		}
	}

	/**
	 * 设置是否置顶
	 * 
	 * @param uids
	 * @param istop
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/official/ajax/recruit/istop/{istop:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> recruit_settop(String[] ids, @PathVariable int istop) {
		if (so.setTop(ids, 0 == istop ? false : true)) {
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 
	 * 冻结或解冻信息
	 * 
	 * @param uids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	@RequestMapping(value = "/official/ajax/recruit/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recruit_status(String[] ids, @PathVariable int status) {
		if (so.status(ids, EStatus.get(status))) {
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

}
