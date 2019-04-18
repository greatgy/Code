package com.supergenius.web.finance.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.CollectHP;
import com.supergenius.xo.finance.service.CollectSO;
import com.supergenius.xo.user.entity.User;

/**
 * 收藏的控制器
 * 
 * @author yangguang
 */
@Controller
public class CollectController extends BaseController {

	@Autowired
	CollectSO so;

	/**
	 * 收藏
	 * @param model
	 * @param type
	 * @param finance
	 * @param id
	 * @param request
	 * @return
	 * @author yangguang
	 * @date 2017年8月29日17:01:45
	 */
	@RequestMapping(value = "/my/ajax/collect/{id:\\w{32}}", method = RequestMethod.GET)
	public @ResponseBody boolean addCollect(Map<String, Object> model, @PathVariable String id, HttpServletRequest request) {
		User user = BaseUserHP.getCurrUser(request);
		boolean bool = false;
		if (CollectHP.isCollect(user.getUid(), id)) {
			CollectHP.cancleCollect(user.getUid(), id);
			ArticleRedisUtil.decr(id, ViewKeyDict.collectcount);
		} else {
			if (CollectHP.add(user.getUid(), id)) {
				ArticleRedisUtil.incr(id, ViewKeyDict.collectcount);
				bool = true;
			}
		}
		return bool;
	}
}
