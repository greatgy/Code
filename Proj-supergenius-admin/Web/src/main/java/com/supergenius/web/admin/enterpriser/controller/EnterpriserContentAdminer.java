package com.supergenius.web.admin.enterpriser.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.enterpriser.hellper.ContentHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.service.ContentSO;

/**
 * 内容管理
 * 
 * @author liubin
 * @date 2016-10-28 下午12:33:57
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EnterpriserContentAdminer extends BaseController {

	@Autowired
	private ContentSO so;

	/**
	 * 内容页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午2:39:23
	 * @return String
	 */
	@RequestMapping(value = "/enterpriser/enterpriserhtmlcontent", method = RequestMethod.GET)
	public String enterprisercontent(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.enterpriserhtmlcontent.name());
		model.put(ViewKeyDict.channelname, EChannel.enterpriserhtmlcontent.getName());
		model.put(ViewKeyDict.site, EChannel.enterpriser.name());
		model.put(ViewKeyDict.total, so.getCount());
		model.put(ViewKeyDict.count, so.getCount(EContent.lecture));
		model.put(ViewKeyDict.count1, so.getCount(EContent.train));
		model.put(ViewKeyDict.count2, so.getCount(EContent.cooperation));
		return "doehtmlcontent";
	}

	/**
	 * 查询数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午5:00:55
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = { "/enterpriser/ajax/enterpriserhtmlcontent/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> content_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ContentHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加页面内容
	 * 
	 * @param name
	 * @param type
	 * @param summary
	 * @param content
	 * @param data
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午6:40:39
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserhtmlcontent/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> htmlcontent_add(String name, String type, String summary, String content, String data) {
		Content content2 = new Content();
		if (StrUtil.isNotEmpty(type)) {
			content2.setType(EContent.get(type));
		} else {
			return result(MsgKeyDict.addFailed);
		}
		if (StrUtil.isNotEmpty(summary)) {
			content2.setSummary(summary);
		}
		if (StrUtil.isNotEmpty(content)) {
			content2.setContent(content);
		}
		if (StrUtil.isNotEmpty(data)) {
			content2.setData(JsonUtil.toJson(data));
		}
		if (so.add(content2)) {
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	/**
	 * 修改页面内容
	 * 
	 * @param name
	 * @param type
	 * @param summary
	 * @param content
	 * @param data
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午6:41:40
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserhtmlcontent/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> htmlcontent_edit(String uid, String name, String type, String summary, String content) {
		Content item = so.get(uid);
		if (StrUtil.isEmpty(item)) {
			return result(MsgKeyDict.editFailed);
		}
		item.setName(name);
		item.setType(EContent.get(type));
		item.setSummary(summary);
		item.setContent(content);
		so.update(item);
		return success();
	}

	/**
	 * 删除内容
	 * 
	 * @param ids
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午6:59:59
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/enterpriser/ajax/enterpriserhtmlcontent/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> content_delete(String[] ids) {
		if (so.delete(ids[0]))
			return success();
		return result(MsgKeyDict.deleteFailed);
	}

}
