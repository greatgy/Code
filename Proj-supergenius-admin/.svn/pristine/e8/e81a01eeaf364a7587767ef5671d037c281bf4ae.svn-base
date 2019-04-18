package com.supergenius.web.admin.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.model.base.entity.SEO;
import com.supergenius.global.conf.UriConf;

/**
 * @author Architect.bian
 * 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class SEOAdminer extends com.genius.server.baseadmin.controller.SEOAdminer {

	@Override
	@RequestMapping(value = "/ajax/seo{site:.*}/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> seo_list(Map<String, Object> model, HttpServletRequest request, @PathVariable String site) {
		// TODO Auto-generated method stub
		return super.seo_list(model, request, site);
	}
	
	@RequestMapping(value = "/ajax/seo{site:.*}/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> seo_add(SEO seo, Integer ismobile, @PathVariable String site) {
		if (ismobile != null && ismobile == 1) {
			seo.setUri("(/m){0,1}" + seo.getUri());
		}
		return seo_add(seo, site);
	}

	@Override
	public Map<String, Object> seo_add(SEO seo, @PathVariable String site) {
		// TODO Auto-generated method stub
		return super.seo_add(seo, site);
	}

	@Override
	@RequestMapping(value = "/ajax/seo{site:.*}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> seo_delete(String[] ids, @PathVariable String site) {
		// TODO Auto-generated method stub
		return super.seo_delete(ids, site);
	}

}