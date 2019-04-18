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
import com.supergenius.web.admin.user.helper.OrderGoodsHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.service.OrderGoodsSO;

/**
 * 会员中心订单管理controller
 * 
 * @author XieMing
 * @date 2016-5-11 上午10:11:00
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class OrderAdminer extends BaseController {

	@Autowired
	OrderGoodsSO so;

	/**
	 * 跳转到列表页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-11 上午10:24:41
	 */
	@RequestMapping(value = "/user/order", method = RequestMethod.GET)
	public String order(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.order.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.order, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.user.name());
		Map<String, Object> map = new HashMap<>();
		for (EGoods item : EGoods.values()) {
			map.put(item.toString(), item.getName());
		}
		model.put(ViewKeyDict.map, map);
		model.put(ViewKeyDict.totalorder, so.getCount());
		map.clear();
		map.put(MapperDict.type, EGoods.video.getValue());
		model.put(ViewKeyDict.videoorder, so.getCount(map));
		map.clear();
		map.put(MapperDict.type, EGoods.ticket.getValue());
		model.put(ViewKeyDict.ticketorder, so.getCount(map));
		return "doorder";
	}

	/**
	 * 加载数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author XieMing 2016-5-11 下午1:40:05
	 */
	@RequestMapping(value = { "/user/ajax/order/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> order_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = OrderGoodsHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 导出选中数据到表格文件中
	 * 
	 * @param model
	 * @param sns
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author XieMing 2016-5-12 下午12:53:05
	 */
	@RequestMapping(value = { "/user/order/exportexcel" }, method = RequestMethod.POST)
	public String order_exportexcel(Map<String, Object> model, String sns, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] sn = sns.split(",");
		OrderGoodsHP.exportExcel(sn, request, response);
		return null;
	}
}
