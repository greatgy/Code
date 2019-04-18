package com.supergenius.web.front.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.user.entity.Order;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.OrderSO;

/**
 * 订单管理
 * @author diaobisong
 */
@Controller
public class MyOrderController extends BaseController {

    @Autowired
    private OrderSO orderSO;

    /**
     * 初始化订单列表
     * @param model
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping("/my/order")
    public String getMyOrder(Map<String,Object> model, Integer pageNum, HttpServletRequest request, String num) {
    	User user = BaseUserHP.getCurrUser(request);
    	String useruid = user.getUid();
    	Pager orderPager = Pager.getNewInstance(num, WebConf.OrderPageSize);
    	orderPager.setTotalCount(orderSO.getCount(useruid));
    	List<Order> orderList = orderSO.getList(useruid, orderPager);
    	if(orderList.size() == 0){
    		model.put(MsgKeyDict.not_exist, true);
    	} else {
    		model.put(ViewKeyDict.pager, orderPager);
    		model.put(ViewKeyDict.list, orderList);
    	}
    	return "order";
    }
    

    
}
