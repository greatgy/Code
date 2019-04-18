package com.supergenius.web.admin.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.admin.helper.QuotesHP;
import com.supergenius.xo.common.entity.Quotes;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EQuotes;

/**
 * 名人名言后台控制器
 * 
 * @author LiJiacheng
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class QuotesAdminer extends BaseController {

	/**
	 * 进入名人名言(Quotes)管理界面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/quotes" }, method = RequestMethod.GET)
	public String quotes(Map<String, Object> model, HttpServletRequest request) {
		Map<String, Object> types = new HashMap<>();
		EQuotes[] quotess = EQuotes.values();
		for (EQuotes quotes : quotess) {
			types.put(quotes.name(), quotes.getName());
		}
		model.put(ViewKeyDict.types, types);
		model.put(ViewKeyDict.channel, EChannel.quotes.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.quotes, Locale.CHINA));
		return "doquotes";
	}

	/**
	 * 得到名人名言列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author LiJiacheng
	 * @throws IOException
	 */
	@RequestMapping(value = { "/ajax/quotes/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> quotes_list(Map<String, Object> model, HttpServletRequest request) throws IOException {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = QuotesHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 添加名人名言
	 * 
	 * @param quotes
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/ajax/quotes/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quotes_add(Quotes quotes) {
		String path = SysConf.SerialBasePath + SysConf.QuotesPath + SysConf.Separator_Directory + SysConf.Quotes;
		QuotesHP.add(path, quotes);
		return success();
	}

	/**
	 * 编辑名人名言
	 * 
	 * @param quotes
	 * @param site
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/ajax/quotes/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quotes_edit(Quotes quotes, int site) {
		String path = SysConf.SerialBasePath + SysConf.QuotesPath + SysConf.Separator_Directory + SysConf.Quotes;
		QuotesHP.edit(path, quotes, site);
		return success();
	}

	/**
	 * 删除名人名言
	 * 
	 * @param site
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = { "/ajax/quotes/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> quotes_delete(int ids) {
		String path = SysConf.SerialBasePath + SysConf.QuotesPath + SysConf.Separator_Directory + SysConf.Quotes;
		QuotesHP.delete(path, ids);
		return success();
	}

}
