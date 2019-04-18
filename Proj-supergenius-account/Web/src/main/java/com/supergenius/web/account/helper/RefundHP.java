package com.supergenius.web.account.helper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;
import com.github.wxpay.sdk.WXPay;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.third.alipay.config.AlipayConfig;
import com.supergenius.third.wxpay.qrcode.config.WXRefundConfig;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.entity.Refund;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.account.service.AccountSO;
import com.supergenius.xo.account.service.RefundSO;

/**
 * 退款
 * 
 * @author YangGuang
 */
public class RefundHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(RefundHP.class);

	private static AccountSO so;

	private static RefundSO refundSO;

	private static AccountSO getSO() {
		if (so == null) {
			so = (AccountSO) spring.getBean(AccountSO.class);
		}
		return so;
	}

	private static RefundSO getRefundSO() {
		if (refundSO == null) {
			refundSO = (RefundSO) spring.getBean(RefundSO.class);
		}
		return refundSO;
	}

	/**
	 * 退款
	 * 
	 * @param account
	 * @param money 退款金额（必须小于account的可退款金额）
	 * @return
	 * @author YangGuang
	 * @throws AlipayApiException
	 */
	public static String refund(Account account, Refund refund, Double money, Map<String, Object> result) throws AlipayApiException {
		String refund_money;
		if (StrUtil.isNotEmpty(money)) {
			if (money > account.getMoney()) {
				log.info("money is false");
				result.put(ViewKeyDict.result, 3);
				return JsonUtil.toJson(result);
			}
			refund_money = String.valueOf(money);
		} else {
			refund_money = String.valueOf(account.getAvailable());
		}
		log.info("fund money is -----------------------------:" + refund_money);
		if (account.getBank() == EBank.alipay) {
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.refundUrl, SysConf.AlipayAppid, SysConf.AlipayPrivateKey, "json", "UTF-8", SysConf.AlipayPublicKey,
					AlipayConfig.refund_sign_type);
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();
			model.setOutTradeNo(account.getAccountsn());
			model.setRefundAmount(refund_money);
			model.setOutRequestNo(account.getUid());// 部分退款此参数必传
			model.setRefundReason("正常退款");
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizModel(model);
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			log.info("fund change -----------------------------:" + response.getFundChange());
			log.info("refund result -----------------------------:" + response.getMsg());
			log.info("refund code -----------------------------:" + response.getCode());
			log.info("refund SubCode -----------------------------:" + response.getSubCode());
			log.info("refund SubMsg -----------------------------:" + response.getSubMsg());
			if (response.isSuccess()) {
				if (response.getMsg().equals("Success")) {
					refund.setState(ERefundState.success);
					refund.setOut_refund_no(response.getOutTradeNo());
					refund.setRefundmoney(Double.parseDouble(response.getRefundFee()));
					refund.setTransaction_id(response.getTradeNo());
					if (response.getFundChange().equalsIgnoreCase("Y")) {
						account.setAvailable(account.getMoney() - Double.parseDouble(refund_money));
						getSO().update(account);
						AccountHP.sendRefundEmail(refund, SysConf.ManagerEmailsSuccess);
					}
					getRefundSO().update(refund);
					log.info("refund is success");
					result.put(ViewKeyDict.result, 1);
					return JsonUtil.toJson(result);
				}
			} else {
				refund.setState(ERefundState.failed);
				getRefundSO().update(refund);
			}
		}
		if (account.getBank() == EBank.wxpay) {
			log.info("微信退款开始");
			try {
	        	WXRefundConfig config = new WXRefundConfig();
	        	WXPay wxpay = new WXPay(config);
	        	Map<String, String> data = new HashMap<String, String>();
	        	data.put("out_trade_no", account.getAccountsn());//商户订单号
	        	data.put("out_refund_no", refund.getUid());//退款单号
	        	data.put("total_fee", String.valueOf((int) (account.getMoney()*100)));//订单金额
	        	data.put("refund_fee", String.valueOf((int) (Double.parseDouble(refund_money)*100)));//订单金额
	        	Map<String, String> response = new HashMap<String, String>();
	        	response = wxpay.refund(data);
	        	log.info("result_code -----------------------------:" + response.get("result_code"));
				log.info("refund err_code -----------------------------:" + response.get("err_code"));
				log.info("refund code -----------------------------:" + response.get("err_code_des"));
				log.info("refund return_msg -----------------------------:" + response.get("return_msg"));
				log.info("refund transaction_id -----------------------------:" + response.get("transaction_id"));
				if (response.get("result_code").equalsIgnoreCase("success")) {
					refund.setState(ERefundState.success);
					refund.setOut_refund_no(response.get("out_trade_no"));
					refund.setRefundmoney(Double.parseDouble(response.get("refund_fee"))/100);
					refund.setTransaction_id(response.get("transaction_id"));
					getRefundSO().update(refund);
					AccountHP.sendRefundEmail(refund, SysConf.ManagerEmailsSuccess);
					log.info("refund is success");
					result.put(ViewKeyDict.result, 1);
					return JsonUtil.toJson(result);
				} else {
					refund.setState(ERefundState.failed);
					getRefundSO().update(refund);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("refund is fail");
		result.put(ViewKeyDict.result, 3);
		return JsonUtil.toJson(result);
	}

}
