package com.supergenius.web.front.life.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.genius.server.base.controller.BaseController;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.life.service.NewsSO;
import com.supergenius.xo.user.entity.User;

/**
 * 消息控制器
 *
 * @author 雍雪振
 * @date 2018年4月8日10:23:45
 */
@Controller
public class MessageController extends BaseController{

	@Autowired
	private NewsSO so;
	
	/**
	 * 返回ajax跨域jsonp格式:评论消息数,系统通知,系统消息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author 雍雪振
	 */
	@RequestMapping(value = "/ajax/msg/msg_jsonp", method = RequestMethod.GET)
	@ResponseBody
	public String msg_jsonp(Map<String, Object> model, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		int countcomment = so.getCountByUnRead(user.getUid());
		Map<String, Object> map = new HashMap<>();
		map.put(ViewKeyDict.countcomment, countcomment);
		return jsonp(map, request);
	}
}
