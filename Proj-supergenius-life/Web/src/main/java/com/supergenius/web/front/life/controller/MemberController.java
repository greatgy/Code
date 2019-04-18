package com.supergenius.web.front.life.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.front.life.helper.MemberHP;

/**
 * 会员通道controller
 * 
 * @author: ChenQi 
 * @date 2017年9月7日17:07:55
 */
@Controller
public class MemberController extends BaseController {

	/**
	 * 跳转到会员通道并展示数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author: ChenQi 2017年9月1日18:46:37
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String member(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.cid, 512); 
		String member = MemberHP.getContent(request, model,  (long)512); 
		model.put(ViewKeyDict.member, member);
		return "member";
	}

}
