package com.supergenius.web.account.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.account.helper.DemoHP;

/**
 * 测试支付
 * @author liushaomin
 */
@Controller
public class DemoController extends BaseController{

	public static String APIUID = "uid11111111111111111111111111111";
	
	/**
	 * demo 充值
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/", "/index", "/demopay" }, method = RequestMethod.GET)
	public String pay(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.apiuid, APIUID);
		model.put(ViewKeyDict.payuid, GlobalUtil.getUUID());
		return "demopay";
	}
	
	/**
	 * 退款
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = {"/refund" }, method = RequestMethod.GET)
	public String refund(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.list, DemoHP.getAccountList());
		model.put(ViewKeyDict.list2, DemoHP.getRefundList());
		return "demorefund";
	}
	
	/**
	 * notifyurl 逻辑
	 * @param model
	 * @param pwdmd5
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/demopay/recharge/notify" }, method = RequestMethod.GET)
	public @ResponseBody String notifyurl(Map<String, Object> model, String pwdmd5, String money, String banktype, String payuid, String useruid, HttpServletRequest request) {
		StringBuffer strb = new StringBuffer();
		strb.append(APIUID).append(payuid).append(banktype).append(money).append(useruid);
		String pwdString = DigestUtils.md5Hex(strb.toString());
		if (pwdmd5.equals(pwdString)) {
			//处理用户信息
			return "0";	
		}
		return "-1";
	}
	
	/**
	 * 充值结果页
	 * @param model
	 * @param result
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = {"/demopay/recharge/result{result:\\d+}" }, method = RequestMethod.GET)
	public String result(Map<String, Object> model, @PathVariable String result, String useruid, HttpServletRequest request) {
		model.put(ViewKeyDict.result, result);
		model.put(ViewKeyDict.apiuid, SysConf.DemoPayApiuid);
		return "demoresult";
	}
	
}
