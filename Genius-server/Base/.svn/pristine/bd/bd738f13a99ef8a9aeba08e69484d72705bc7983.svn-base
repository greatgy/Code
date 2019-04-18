package com.genius.server.base.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面控制器
 * 
 * @author architect.bian
 * @createtime 2014-7-24 下午3:24:55
 */
@Controller
public class ErrorController extends BaseController {

	@RequestMapping(value = "/404")
	public String error_404(HttpServletResponse response) {
		return response404(response);
	}
}
