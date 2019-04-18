package com.supergenius.web.admin.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.constant.BaseMsgKeyDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.web.admin.admin.helper.CommandHP;

/**
 * 网站初始化
 * 
 * @author architect.bian
 * @createtime 2015-12-31 下午3:10:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class CommandAdminer extends BaseController {
	

	@RequestMapping(value = "/command", method = RequestMethod.GET)
	public String admindopwd(Map<String, Object> model, HttpServletRequest request) {
		return "docommand";
	}
	
	@RequestMapping(value = "/command", method = RequestMethod.POST)
	public String admindopwd(Map<String, Object> model, String action, String pwd, HttpServletRequest request) throws Exception {
		Map<String, String> errs = new HashMap<>();
		Map<String, String> msgs = new HashMap<>();
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_finance.name())) {
			CommandHP.initializeSearchIndex_finance();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_startup.name())) {
			CommandHP.initializeSearchIndex_startup();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_ai.name())) {
			CommandHP.initializeSearchIndex_ai();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_career.name())) {
			CommandHP.initializeSearchIndex_career();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_gupage.name())) {
			CommandHP.initializeSearchIndex_gupage();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_enterpriser.name())) {
			CommandHP.initializeSearchIndex_enterpriser();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_life.name())) {
			CommandHP.initializeSearchIndex_life();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_managernews.name())) {
			CommandHP.initializeSearchIndex_managernews();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_entrepreneur.name())) {
			CommandHP.initializeSearchIndex_entrepreneur();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		if (StrUtil.isNotEmpty(action) && action.equals(EAction.searchIndexInitialize_moralnews.name())) {
			CommandHP.initializeSearchIndex_moralnews();
			msgs.put(BaseMsgKeyDict.doSuccess, "");
		}
		
		model.put(BaseViewKeyDict.err, errs);
		model.put(BaseViewKeyDict.msg, msgs);
		return "docommand";
	}
	
	/**
	 * 内部操作枚举类，仅此类使用
	 * 
	 * @author architect.bian
	 * @createtime 2015-12-31 下午3:14:14
	 */
	protected enum EAction {
		searchIndexInitialize_finance,
		searchIndexInitialize_startup,
		searchIndexInitialize_career,
		searchIndexInitialize_ai,
		searchIndexInitialize_gupage,
		searchIndexInitialize_enterpriser,
		searchIndexInitialize_life,
		searchIndexInitialize_managernews,
		searchIndexInitialize_entrepreneur,
		searchIndexInitialize_moralnews;
	}
}
