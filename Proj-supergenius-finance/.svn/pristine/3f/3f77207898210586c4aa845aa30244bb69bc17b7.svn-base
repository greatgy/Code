package com.supergenius.web.finance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.MycenterHP;
import com.supergenius.web.finance.helper.NewsHP;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.enums.EFollow;
import com.supergenius.xo.finance.service.SubscribeSO;
import com.supergenius.xo.user.entity.User;

/**
 * 首页controller
 * 
 * @author: ChenQi
 * @date 2017年11月14日10:24:34
 */
@Controller
public class SubscribeController extends BaseController {

	@Autowired
	private SubscribeSO so;
	
	/**
	 * 添加订阅
	 * 
	 * @param fromuid
	 * @param request
	 * @param response
	 * @author ChenQi
	 * @date 2018年1月6日13:44:31
	 */
	@ResponseBody
	@RequestMapping(value = { "/ajax/subscribe/{refuseruid:.{32}}" }, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> subscribe_add(@PathVariable String refuseruid, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Subscribe subscribe = new Subscribe();
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			subscribe.setUseruid(user.getUid());
			subscribe.setRefuseruid(refuseruid);
			Subscribe tempsub = MycenterHP.getSubscribe(refuseruid, user.getUid());//查询是否被对方订阅过
			if (tempsub != null) {
				subscribe.setFollow(EFollow.draft);
				tempsub.setFollow(EFollow.draft);
				so.update(tempsub);
			} else {
				subscribe.setFollow(EFollow.published);
			}
			if (so.add(subscribe)) {
				NewsHP.sendMsg(subscribe, EFinanceMsg.subscribe);
				result.put(ViewKeyDict.result, true);
			} else {
				result.put(ViewKeyDict.result, false);
			}
		} else {
			result.put(ViewKeyDict.result, false);
		}
		return json(result, Json.webStrategy);
	}

	/**
	 * 取消订阅
	 * 
	 * @param fromuid
	 * @param request
	 * @param response
	 * @author ChenQi
	 * @date 2018年1月6日13:44:31
	 */
	@ResponseBody
	@RequestMapping(value = { "/ajax/cancelsubscribe/{refuseruid:.{32}}" }, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> subscribe_cancel(@PathVariable String refuseruid, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Subscribe subscribe = new Subscribe();
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			subscribe = MycenterHP.getSubscribe(user.getUid(), refuseruid);
			if (subscribe.getFollow() == EFollow.draft) {
				subscribe.setFollow(EFollow.published);
				Subscribe tempsub = MycenterHP.getSubscribe(refuseruid, user.getUid());
				tempsub.setFollow(EFollow.published);
				so.update(tempsub);
			}
			subscribe.setStatus(EStatus.deleted);
			if (so.update(subscribe)) {
				result.put(ViewKeyDict.result, true);
			} else {
				result.put(ViewKeyDict.result, false);
			}
		} else {
			result.put(ViewKeyDict.result, false);
		}
		return json(result, Json.webStrategy);
	}
}
