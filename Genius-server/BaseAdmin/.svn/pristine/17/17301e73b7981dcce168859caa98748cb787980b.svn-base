package com.genius.server.baseadmin.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseUriConf;
import com.genius.core.base.constant.BaseMsgKeyDict;
import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.model.base.entity.SEO;
import com.genius.model.baseadmin.enums.EChannel;
import com.genius.server.base.controller.BaseController;
import com.genius.server.base.helper.SEOHP;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = BaseUriConf.baseAdminPath)
public class SEOAdminer extends BaseController {

	@RequestMapping(value = "/seo{site:.*}", method = RequestMethod.GET)
	public String seo(Map<String, Object> model, HttpServletRequest request, @PathVariable String site) {
		model.put(BaseViewKeyDict.list, SEOHP.getList(site));
		model.put(BaseViewKeyDict.site, site);
		model.put(BaseViewKeyDict.channel, EChannel.seo.name());
		model.put(BaseViewKeyDict.channeloid, EChannel.seo);
		model.put(BaseViewKeyDict.channelname, EChannel.getName(EChannel.seo, Locale.CHINA));
		return "doseo";
	}

	@RequestMapping(value = "/ajax/seo{site:.*}/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> seo_list(Map<String, Object> model, HttpServletRequest request, @PathVariable String site) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseViewKeyDict.rows, SEOHP.getList(site));
		result.put(BaseViewKeyDict.total, SEOHP.getList(site).size());
		return json(result, Json.webStrategy);
	}
	
	/**
	 * 添加或编辑调用此方法
	 * @param seo
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-30 下午4:12:58
	 */
	@RequestMapping(value = "/api/seo{site:.*}/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> seo_add(SEO seo, @PathVariable String site) {
		if (SEOHP.add(seo, site)) {
			return success();
		} else {
			return result(BaseMsgKeyDict.addFailed);
		}
	}
	
	@RequestMapping(value = "/api/seo{site:.*}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> seo_delete(String[] ids, @PathVariable String site) {
		SEOHP.delete(ids, site);
		return success();
	}

}