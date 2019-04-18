package com.supergenius.web.front.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.HttpUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.global.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.user.helper.AccountHP;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.ETrade;
import com.supergenius.xo.user.service.TradeDetailSO;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 收支管理controller
 * @author chenminchang
 * @date 2016-4-1 上午11:30:55 
 */
@Controller
public class MyAccountController extends BaseController {

	@Autowired
	TradeDetailSO tradedetailSO;
	@Autowired
	AccountSO accountSO;
	@Autowired
	UserSO userSO;
	
	/**
	 * 收支管理-账户基本信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/account/info", "/my/account" }, method = RequestMethod.GET)
	public String info(Map<String, Object> model, HttpServletRequest request) {
		return "accountinfo";
	}
	
	/**
	 * 收支管理-账户充值
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/account/recharge" }, method = RequestMethod.GET)
	public String recharge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.apiuid, SysConf.UserPayApiuid);
		model.put(ViewKeyDict.payuid, GlobalUtil.getUUID());
		model.put(ViewKeyDict.site, ESite.user);//TODO 升级正式服务器才是正确的 
		model.put(ViewKeyDict.type, ECharge.recharge);
		model.put(ViewKeyDict.userip, NetUtil.getIPAddr(request));
		return "recharge";
	}
	
	/**
	 * account项目通知
	 * @param model
	 * @param pwdmd5
	 * @param money
	 * @param banktype
	 * @param payuid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/api/account/recharge/notify" }, method = RequestMethod.GET)
	@ResponseBody
	public String notifyurl(Map<String, Object> model, String pwdmd5, String money, String banktype, String payuid, String useruid, HttpServletRequest request) {
		User user = userSO.get(useruid);
		StringBuffer strb = new StringBuffer();
		strb.append(SysConf.UserPayApiuid).append(payuid).append(banktype).append(money).append(useruid);
		String pwdString = DigestUtils.md5Hex(strb.toString());
		if (pwdmd5.equals(pwdString)) {
			user.setAccount(user.getAccount() + Double.valueOf(money));
			TradeDetail tradeDetail = new TradeDetail(useruid, null, Double.parseDouble(money), user.getAccount(), AutoIncrHP.getTradeDetailsn(), null, ETrade.recharge, ETrade.recharge.getName(), ESite.user);
			userSO.update(user, tradeDetail);
			BaseUserHP.freshSessUser(request, user);
			return "0";
		}
		return "-1";
	}
	
	/**
	 * 页面查询->访问account的query
	 * @param model
	 * @param apiuid
	 * @param payuid
	 * @param site
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/api/{apiuid:.{32}}/recharge/query/{payuid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody String query(Map<String, Object> model, @PathVariable String apiuid, @PathVariable String payuid, String site, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put(ViewKeyDict.site, site);
		String url = AccountHP.urlFormat(SysConf.AccountQueryUrl, null, apiuid, payuid);
		String result = "";
		result =  HttpUtil.get(url, map);
		if (StrUtil.isEmpty(result)) {
			Map<String, Object> errmap = new HashMap<>();
			errmap.put(MsgKeyDict.errs, true);
			result = JsonUtil.toJson(errmap);
		}
		return result;
	}
	
	/**
	 * recharge结果页
	 * @param model
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/account/recharge/result{result:\\d+}" }, method = RequestMethod.GET)
	public String result(Map<String, Object> model, @PathVariable String result, String money, HttpServletRequest request) {
		model.put(ViewKeyDict.result, result);
		model.put(ViewKeyDict.money, money);
		return "rechargeresult";
	}
	
	/**
	 * 收支管理-充值记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/account/rechargedetail" }, method = RequestMethod.GET)
	public String rechargedetail(Map<String, Object> model, String num, HttpServletRequest request) {
		String useruid = BaseUserHP.getCurrUser(request).getUid();
		int count = accountSO.getCount(useruid);
		if (count == 0) {
			model.put(MsgKeyDict.not_exist, true);
			return "rechargedetail";
		}
		Pager pager = Pager.getNewInstance(num,WebConf.RechargePageSize);
		List<Account> list = accountSO.getList(useruid, pager);
		pager.setTotalCount(count);
		model.put(ViewKeyDict.pager, pager);
		model.put(ViewKeyDict.list, list);
		return "rechargedetail";
	}
	
	/**
	 * 收支管理-收支记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/my/account/tradedetail" }, method = RequestMethod.GET)
	public String tradedetail(Map<String, Object> model, String num, HttpServletRequest request) {
		String useruid = BaseUserHP.getCurrUser(request).getUid();
		int count = tradedetailSO.getCountByUseruid(useruid);
		if (count == 0) {
			model.put(MsgKeyDict.not_exist, true);
			return "tradedetail";
		}
		Pager pager = Pager.getNewInstance(num,WebConf.TradePageSize);
		List<TradeDetail> list = tradedetailSO.getList(useruid, pager);
		pager.setTotalCount(count);
		model.put(ViewKeyDict.pager, pager);
		model.put(ViewKeyDict.list, list);
		return "tradedetail";
	}
	
}
