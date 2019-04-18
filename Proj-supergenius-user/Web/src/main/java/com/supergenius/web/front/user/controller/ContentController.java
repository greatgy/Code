package com.supergenius.web.front.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.user.entity.Content;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.ContentSO;

/** 
 * 用户协议等Controller
 * @author guanshiqian
 * @date 2016-4-25 下午1:55:44 
 */
@Controller
public class ContentController extends BaseController {

	@Autowired
    private ContentSO contentSO;
	
	/**
	 * 获取用户协议
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userrule", method = RequestMethod.GET)
    public String userrule(Map<String,Object> model, HttpServletRequest request) {
		Content content = contentSO.getOneByType(EContent.userrule);
		model.put(ViewKeyDict.userrule, content);
    	return "userrule";
    }
	
	/**
	 * 获取法律声明
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/legal", method = RequestMethod.GET)
    public String legal(Map<String,Object> model, HttpServletRequest request) {
		Content content = contentSO.getOneByType(EContent.legal);
		model.put(ViewKeyDict.legal, content);
    	return "legal";
    }
	
	/**
	 * 获取超天才网社交网路平台管理规定
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/managerule", method = RequestMethod.GET)
    public String managerule(Map<String,Object> model, HttpServletRequest request) {
		Content content = contentSO.getOneByType(EContent.managerule);
		model.put(ViewKeyDict.managerule, content);
    	return "managerule";
    }
	
}
