package com.supergenius.web.admin.user.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.user.helper.TradeHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.enums.ETrade;
import com.supergenius.xo.user.service.TradeDetailSO;

/**
 * 管理交易明细Controller
 * 
 * @author chenminchang
 * @date 2016-5-24 下午6:45:26
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class TradeAdminer extends BaseController {

	@Autowired
	TradeDetailSO so;

	/**
	 * 跳转交易明细列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/trade", method = RequestMethod.GET)
	public String trade(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.trade.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.trade, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.user.name());
		Map<String, String> map = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();
		for (ETrade item : ETrade.values()) {
			map.put(item.toString(), item.getName());
		}
		for (ESite site : ESite.values()) {
			map2.put(site.toString(), site.getName());
		}
		model.put(ViewKeyDict.map, map);
		model.put(ViewKeyDict.map2, map2);
		model.put(ViewKeyDict.summoney, so.getSumMoney());
		return "dotrade";
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/user/ajax/trade/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> trade_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = TradeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 以excel格式导出选中的数据
	 * 
	 * @param model
	 * @param sns
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/user/trade/exportexcel" }, method = RequestMethod.POST)
	public String trade_exportexcel(Map<String, Object> model, String sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] sn = sns.split(",");
		TradeHP.exportexcel(sn, request, response);
		return null;
	}
}
