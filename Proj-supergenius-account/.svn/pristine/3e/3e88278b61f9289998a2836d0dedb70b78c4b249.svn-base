package com.supergenius.web.account.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.session.constant.SessDict;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.account.helper.AccountHP;
import com.supergenius.web.account.helper.RechargeHP;
import com.supergenius.web.account.helper.RefundHP;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.account.service.RefundSO;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;

/**
 * 充值接口
 * 
 * @author liushaomin
 */
@Controller
public class RechargeInterfacer extends BaseController {

	private static Logger log = LoggerFactory.getLogger(RechargeInterfacer.class);

	@Autowired
	private AccountSO so;
	@Autowired
	private RefundSO refundSO;

	/**
	 * 充值接口 描述：调用此接口，请求充值
	 * 
	 * @param model
	 * @param apiuid
	 * @param payuid
	 * @param money
	 * @param banktype
	 * @param notifyurl
	 * @param resulturl
	 * @param userip
	 * @param site
	 * @param useruid
	 * @param username
	 * @param useremail
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/api/{apiuid:.{32}}/recharge/{payuid:.{32}}", method = RequestMethod.POST)
	public String recharge(Map<String, Object> model, @PathVariable String apiuid, @PathVariable String payuid, Double money, String banktype, String notifyurl, String resulturl, String userip,
			String site, String type, String useruid, String username, String useremail, HttpServletRequest request) {
		log.info(
				String.format("begin to invoke recharge (apiuid:%s, payuid:%s, money:%s, banktype:%s, notifyurl:%s, resulturl:%s, userip:%s, site:%s, type:%s,  useruid:%s, username:%s, useremail:%s)",
						apiuid, payuid, money, banktype, notifyurl, resulturl, userip, site, type, useruid, username, useremail));
		boolean flag = false;
		if (StrUtil.isEmpty(apiuid)) {
			log.info("apiuid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(payuid)) {
			log.info("payuid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(money)) {
			log.info("money is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(banktype)) {
			log.info("banktype is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(notifyurl)) {
			log.info("notifyurl is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(resulturl)) {
			log.info("resulturl is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(userip)) {
			log.info("userip is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(site)) {
			log.info("site is Empty");
			flag = true;
		}
		if (!StrUtil.isNumeric(site)) {
			log.info("site is not a number");// TODO
												// 增加对site类型的判断，如果是String类型将之转换
			flag = true;
		}
		if (StrUtil.isEmpty(type)) {
			log.info("type is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(useruid)) {
			log.info("useruid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(username)) {
			log.info("username is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(useremail)) {
			log.info("useremail is Empty");
			flag = true;
		}
		if (flag) {
			model.put(ViewKeyDict.empty, true);
			return "error";
		}
		// TODO 增加sign参数，并校验，使用与第三方项目约定好的salt与其他参数生成sign并验证是否一致
		if (!RechargeHP.CheckApiuid(site, apiuid)) {
			log.info("Apiuid Validation failed");
			model.put(ViewKeyDict.validationfailed, 0);
			return "error";
		}
		if ((request.getParameter(ViewKeyDict.ismobile) != null && Boolean.valueOf(request.getParameter(ViewKeyDict.ismobile))) || WebUtil.isMobileRequest(request)) {
			log.info("request is from mobile");
			model.put(ViewKeyDict.ismobile, true); // 是否移动端，若移动端支付宝会更换接口
		} else {
			log.info("request is not from mobile");
			model.put(ViewKeyDict.ismobile, false);
		}
		Account account = so.getOneByPayuid(payuid);

		if (account == null) {
			account = new Account();
			account.setAccountsn(AccountHP.getAccountsn(site));
			account.setPayuid(payuid);
			account.setUseruid(useruid);
			account.setUsername(username);
			account.setUseremail(useremail);
			account.setUserip(userip);
			account.setMoney(Double.valueOf(money));
			account.setAvailable(Double.valueOf(money));
			account.setSite(ESite.get(Integer.parseInt(site)));
			account.setResulturl(resulturl);
			account.setNotifyurl(notifyurl);
			account.setBank(EBank.get(banktype));
			account.setType(ECharge.get(type));
			account.setState(EState.init);
			if (so.add(account)) {
				String resultAccountUrl = String.format(SysConf.BankRechargeResultUrl, banktype, account.getUid());
				log.info("All Client Cookie:" + CookieUtil.getAllCookie(request));
				log.info("ResulAccountUrl in bank_recharge(...):" + resultAccountUrl);
				log.info("money in bank_recharge(...):" + account.getMoney());
				if (EBank.wxpay.toString().equals(banktype)) { // 微信支付需要返回额外参数
					model.put(ViewKeyDict.apiuid, apiuid);
					model.put(ViewKeyDict.payuid, payuid);
					model.put(ViewKeyDict.site, site);
					model.put(ViewKeyDict.resulturl, RechargeHP.getResultUrl(account, 1));
				}
				if (EBank.payeezy.toString().equals(banktype)) { // payeezy支付需要返回额外参数
					model.put(ViewKeyDict.merchant_cookie_1, account.getUid());
				}
				System.err.println("马上进入相对应的银行，进行调用接口操作----------------------------------------------");
				System.err.println("EBank.get(banktype)====="+ banktype);
				System.err.println("如果banktype等于6的话，选择的就是相对应的银联支付");
				return RechargeHP.getPostData(model, EBank.get(banktype), account, resultAccountUrl, NetUtil.getIPAddr(request));
			}
		}
		log.info("repeatSubmit");
		model.put(ViewKeyDict.repeatSubmit, true);
		return "error";
	}

	/**
	 * 充值结果，页面请求，跳转到结果页，结果也自动跳转到站点的结果页
	 * 
	 * @param model
	 * @param banktype
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/recharge/frombank{banktype:\\d+}/{uid:.{32}}")
	public String bank_rechargeReceive(Map<String, Object> model, @PathVariable String banktype, @PathVariable String uid, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info(String.format("begin to invoke bank_rechargeReceive(banktype:%s, uid:%s)", banktype, uid));
		Map<String, String> errs = new HashMap<String, String>();
		Account account = so.get(uid);
		if (account != null) {
			if (EBank.get(banktype) == EBank.paypal) {
				model.put(ViewKeyDict.time, 0);
				String resulturl = RechargeHP.getResultUrl(account, 2); // paypal初始返回页面
				log.info(String.format("ResultUrl: %s", resulturl));
				model.put(ViewKeyDict.resulturl, resulturl);
				log.info("request ResultUrl is paypal");
				return "rechargeresult";
			} else {
				if (RechargeHP.receivePostData(model, account, errs, request, response)) {
					AccountHP.updateAccountState(account, EState.pay);
						AccountHP.updateAccountState(account, EState.success);
						String resulturl = RechargeHP.getResultUrl(account, 1); // 支付成功
						log.info(String.format("siteresulturl (siteresulturl:%s)", resulturl));
						model.put(ViewKeyDict.resulturl, resulturl);
						return "rechargeresult";
				} else {
					AccountHP.updateAccountState(account, EState.failed);
					log.info("bank result is Empty");
					errs.put(ViewKeyDict.noexist, "");
				}
			}
		}

		String resulturl = RechargeHP.getResultUrl(account, 0); // 出现异常，已支付，操作未完成
		log.info(String.format("ResultUrl: %s", resulturl));
		model.put(ViewKeyDict.resulturl, resulturl);
		model.put(ViewKeyDict.err, errs);
		return "rechargeresult";
	}

	/**
	 * payeezy充值结果，页面请求，跳转到结果页，结果也自动跳转到站点的结果页
	 * 
	 * @param model
	 * @param banktype
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/recharge/payeezy")
	public String payeezy_rechargeReceive(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uid = request.getParameter("merchant_cookie_1");
		int state = Integer.valueOf(request.getParameter("x_response_code"));
		log.info(String.format("begin to invoke bankpayeezy_rechargeReceive(uid:%s)", uid));
		log.info(String.format("begin to invoke bankpayeezy_rechargeReceive(state:%s)", state));
		Map<String, String> errs = new HashMap<String, String>();
		Account account = so.get(uid);
		if (account != null) {
			if (state == 1) {
				model.put(ViewKeyDict.time, 0);
				AccountHP.updateAccountState(account, EState.success);
				String resulturl = RechargeHP.getResultUrl(account, 1); // paypal初始返回页面
				log.info(String.format("ResultUrl: %s", resulturl));
				model.put(ViewKeyDict.resulturl, resulturl);
				log.info("request ResultUrl is payeezy");
				return "payeezyrechargeresult";
			} else {
				AccountHP.updateAccountState(account, EState.failed);
				log.info("bank result is Empty");
				errs.put(ViewKeyDict.noexist, "");
			}
		}

		String resulturl = RechargeHP.getResultUrl(account, 0); // 出现异常，已支付，操作未完成
		log.info(String.format("ResultUrl: %s", resulturl));
		model.put(ViewKeyDict.resulturl, resulturl);
		model.put(ViewKeyDict.err, errs);
		return "payeezyrechargeresult";
	}

	/**
	 * 给银行后台调用的通知接口
	 * 
	 * @param model
	 * @param banktype
	 * @param uid
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author liushaomin
	 */
	@RequestMapping(value = "/api/frombank{banktype:\\d+}/{uid:.{32}}")
	public @ResponseBody String bank_backReceive(Map<String, Object> model, @PathVariable String banktype, @PathVariable String uid, HttpServletRequest request, HttpServletResponse response,
			@RequestBody(required = false) String data) throws IOException {
		log.info(String.format("+++++++++++++++++++++++++++++++++++++++++++++++begin to invoke bank_backReceive(banktype:%s, uid:%s)", banktype, uid));
		
		System.err.println("123465");
		
		Account account = so.get(uid);
		if (account != null && !account.getState().equals(EState.pay) && !account.getState().equals(EState.success)) {
			if (StrUtil.isNotEmpty(data)) {
				log.info(String.format("request data: %s", data));
				model.put(ViewKeyDict.data, data);
			}
			String flag = "";
			if (RechargeHP.backReceiveHandler(model, account, request, response)) {
				log.info("recharge success");
				AccountHP.updateAccountState(account, EState.pay);
				if (RechargeHP.getSiteNotifyUrl(account)) {
					AccountHP.updateAccountState(account, EState.success);
				} // TODO 未请求成功
				flag = "true";
			} else {
				flag = "false";
			}
			log.info("All Client Cookie:" + CookieUtil.getAllCookie(request));
			if (EBank.get(banktype) == EBank.cmbchina && CookieUtil.getCookie(request, SessDict.SESSION_ID) != null) {// 招行返回商户页的地址与银行后台通知地址同一个地址，所以返回商户时做一个判断
				log.info("banktype:" + banktype);
				String resulturl = String.format(SysConf.BankRechargeResultUrl, banktype, account.getUid());// 默认是充值
				resulturl += "?" + request.getQueryString();
				response.sendRedirect(resulturl);
				log.info(String.format("redirecting from bank%s to %s", banktype, resulturl));
			}
			if (EBank.get(banktype) == EBank.alipay) { // 支付宝返回结果要求不同
				log.info("banktype:" + banktype);
				if (Boolean.valueOf(flag)) {
					return "success";
				} else {
					return "fail";
				}
			} else if (EBank.get(banktype) == EBank.wxpay) { // 微信支付返回结果要求不同
				log.info("banktype:" + banktype);
				if (model.get(ViewKeyDict.response) != null) {
					return model.get(ViewKeyDict.response).toString();
				} else {
					return response404(response);
				}
			}
			log.info("return flag=" + flag);
			return flag;
		} else {
			return response404(response);
		}
	}

	/**
	 * 站点主动查询充值结果的接口 操作：在页面点击“支付完成”或“支付未完成”，页面自动请求
	 * 
	 * @param model
	 * @param apiuid
	 * @param payuid
	 * @param site
	 * @param request
	 * @param response
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/api/{apiuid:.{32}}/recharge/query/{payuid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody String query(Map<String, Object> model, @PathVariable String apiuid, @PathVariable String payuid, String site, HttpServletRequest request, HttpServletResponse response) {
		log.info(String.format("begin to invoke query(apiuid:%s, payuid:%s, site:%s, siteName:%s)", apiuid, payuid, site, ESite.getName(ESite.get(Integer.parseInt(site)), Locale.CHINA)));
		Map<String, Object> resultmap = new HashMap<String, Object>();
		boolean flag = false;
		if (StrUtil.isEmpty(apiuid)) {
			log.info("apiuid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(payuid)) {
			log.info("payuid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(site)) {
			log.info("site is Empty");
			flag = true;
		}
		if (flag) {
			log.info("result 3");
			resultmap.put(ViewKeyDict.result, 3);
			return JsonUtil.toJson(resultmap);
		}
		if (!RechargeHP.CheckApiuid(site, apiuid)) {
			log.info("Apiuid Validation failed, result 4");
			resultmap.put(ViewKeyDict.result, 4);
			return JsonUtil.toJson(resultmap);
		}
		Account account = so.getOneByPayuid(payuid);
		if (account == null) {
			log.info("payuid Validation failed, result 5 or Send a payment request fails");
			resultmap.put(ViewKeyDict.result, 5);
			return JsonUtil.toJson(resultmap);
		}
		if (account.getState().equals(EState.init)) {
			log.info("result 0");
			resultmap.put(ViewKeyDict.result, 0);
		} else if (account.getState().equals(EState.pay)) {
			log.info("result 2");
			resultmap.put(ViewKeyDict.result, 2);
		} else if (account.getState().equals(EState.success)) {
			log.info("result 1");
			resultmap.put(ViewKeyDict.result, 1);
		} else {
			log.info("result -1");
			resultmap.put(ViewKeyDict.result, -1);
		}
		return JsonUtil.toJson(resultmap);
	}
	
	/**
	 * 申请退款
	 * 
	 * @param model
	 * @param apiuid
	 * @param payuid
	 * @param site
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 * @throws AlipayApiException 
	 */
	@RequestMapping(value = "/api/{apiuid:.{32}}/refund/{payuid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody String applyrefund(Map<String, Object> model, @PathVariable String apiuid, @PathVariable String payuid, String site, HttpServletRequest request, HttpServletResponse response) {
		log.info("测试退款接口：");
		log.info("------------------------------------------------------------");
		log.info("用户点击申请退款，开始验证参数以及account");
		Map<String, Object> resultmap = new HashMap<String, Object>();
		boolean flag = false;
		if (StrUtil.isEmpty(payuid)) {
			log.info("payuid is Empty");
			flag = true;
		}
		if (StrUtil.isEmpty(site)) {
			log.info("site is Empty");
			flag = true;
		}
		if (flag) {
			log.info("result 3");
			resultmap.put(ViewKeyDict.result, 3);
			return JsonUtil.toJson(resultmap);
		}
		if (!RechargeHP.CheckApiuid(site, apiuid)) {
			log.info("Apiuid Validation failed");
			resultmap.put(ViewKeyDict.result, 4);
			return JsonUtil.toJson(resultmap);
		}
		Account account = so.getOneByPayuid(payuid);
		if (account == null) {
			log.info("account Validation failed, account is empty");
			resultmap.put(ViewKeyDict.result, 5);
			return JsonUtil.toJson(resultmap);
		}
		if (!account.getState().equals(EState.success)) {
			log.info("state Validation failed, state is not success");
			resultmap.put(ViewKeyDict.result, 5);
			return JsonUtil.toJson(resultmap);
		}
		log.info("------------------------------------------------------------");
		log.info("所有验证通过，开始创建退款记录");
		Refund refund = new Refund();
		refund.setAccountuid(account.getUid());
		refund.setUseruid(account.getUseruid());
		refund.setUseremail(account.getUseremail());
		refund.setUsername(account.getUsername());
		refund.setSite(ESite.get(Integer.parseInt(site)));
		refund.setMoney(account.getAvailable());
		refund.setState(ERefundState.init);
		refund.setBank(account.getBank());
		if (refundSO.add(refund)) {
			log.info("------------------------------------------------------------");
			log.info("创建退款记录成功，等待管理员同意退款");
			resultmap.put(ViewKeyDict.result, 1);
			return JsonUtil.toJson(resultmap);
		} else {
			log.info("创建退款记录失败");
			resultmap.put(ViewKeyDict.result, 6);
			return JsonUtil.toJson(resultmap);
		}
	}
	
	/**
	 * 管理员同意退款(该controller应该放在管理后台实现，目前为了方便测试，先放在这里，完成测试后移到管理后台)
	 * 
	 * @param model
	 * @param refunduid
	 * @param payuid
	 * @param request
	 * @param response
	 * @return
	 * @author ChenQi
	 * @throws AlipayApiException 
	 */
	@RequestMapping(value = "/agreerefund/{refunduid:.{32}}", method = RequestMethod.GET)
	public @ResponseBody String agreerefund(Map<String, Object> model, @PathVariable String refunduid, HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
		log.info("------------------------------------------------------------");
		log.info("管理员点击同意退款，开始验证参数");
		Map<String, Object> resultmap = new HashMap<String, Object>();
		boolean flag = false;
		if (StrUtil.isEmpty(refunduid)) {
			log.info("refunduid is Empty");
			flag = true;
		}
		if (flag) {
			log.info("result 3");
			resultmap.put(ViewKeyDict.result, 3);
			return JsonUtil.toJson(resultmap);
		}
		Refund refund = refundSO.get(refunduid);
		if (refund == null) {
			log.info("refund Validation failed, refund is empty");
			resultmap.put(ViewKeyDict.result, 5);
			return JsonUtil.toJson(resultmap);
		}
		Account account = so.get(refund.getAccountuid());
		if (account == null || !account.getState().equals(EState.success)) {
			log.info("account Validation failed, account is false");
			resultmap.put(ViewKeyDict.result, "5");
			return JsonUtil.toJson(resultmap);
		}
		log.info("------------------------------------------------------------");
		log.info("所有验证通过，开始改变退款记录的状态为agree");
		/*if (StrUtil.isNotEmpty(AdminHP.getAdminUid())) {//后台使用的部分代码
			refund.setAdminUid(AdminHP.getAdminUid());
		}*/
		refund.setState(ERefundState.agree);
		if (refundSO.update(refund)) {
			//跳转到各自的接口，以方便封装参数及调用
			if (EBank.wxpay == refund.getBank()) { // 微信退款接口
				return RefundHP.refund(account, refund, null, resultmap);
			}
			if (EBank.alipay == refund.getBank()) { // 支付宝退款接口
				return RefundHP.refund(account, refund, null, resultmap);
			}
			log.info("未找到相应退款接口");
			resultmap.put(ViewKeyDict.result, "6");
			return JsonUtil.toJson(resultmap);
		}
		log.info("更改退款记录失败");
		resultmap.put(ViewKeyDict.result, "5");
		return JsonUtil.toJson(resultmap);
	}
	
}
