package com.genius.server.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.constant.BaseViewKeyDict;
import com.genius.model.portal.enums.EContent;
import com.genius.server.base.controller.BaseController;
import com.genius.server.portal.helper.ContentHP;

/**
 * @author Architect.bian
 * 
 */
@Controller
public class ContentController extends BaseController {

//	@Autowired
//	private ContentSO so;
	
	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public String service(Map<String, Object> model, HttpServletRequest request) {
		return "service";
	}

	@RequestMapping(value = "/agreement", method = RequestMethod.GET)
	public String agreement(Map<String, Object> model, HttpServletRequest request) {
		return "agreement";
	}
	
	@RequestMapping(value = "/joinus", method = RequestMethod.GET)
	public String joinus(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.hr));
		return "joinus";
	}
	
	@RequestMapping(value = "/friendlink", method = RequestMethod.GET)
	public String friendlink(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.friendLink_txt));
		return "friendlink";
	}
	
	@RequestMapping(value = "/sitemap", method = RequestMethod.GET)
	public String sitemap(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.sitemap));
		return "sitemap";
	}

	
	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutus(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.aboutus));
		return "aboutus";
	}
	
	@RequestMapping(value = "/legal", method = RequestMethod.GET)
	public String legal(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.legal));
		return "legal";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String callcenter(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.contact));
		return "contact";
	}
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Map<String, Object> model, HttpServletRequest request) {
		model.put(BaseViewKeyDict.bean, ContentHP.getOne(EContent.help));
		return "help";
	}
	
}
