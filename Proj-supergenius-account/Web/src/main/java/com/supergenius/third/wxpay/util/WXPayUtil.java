package com.supergenius.third.wxpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.BaseUtil;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.HttpUtil;
import com.supergenius.third.wxpay.qrcode.config.PayData;
import com.supergenius.third.wxpay.qrcode.config.WXConfig;

public class WXPayUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(WXPayUtil.class);
	
	/**
	 * 生成直接支付url，支付url有效期为2小时,模式二
	 * @param payData
	 * @return
	 */
	public static String GetPayUrl(PayData payData) {
		log.info("begin to invoke GetPayUrl(...)");
		PayData data = unifiedOrder(payData);
		if (isSuccess(data)) {
			return data.getCodeUrl();
		}
		return null;
	}

	/**
	 * 统一下单
	 * @param data
	 * @return
	 */
	public static PayData unifiedOrder(PayData data) {
		log.info("begin to invoke unifiedOrder(...)");
		configPayData(data);
		String xml = data.toXml();
		log.info("UnifiedOrder request xml:" + xml);
		String result = HttpUtil.postXml(WXConfig.UrlUnifiedOrder, xml);
		log.info("HttpUtil.postXml result: " + result);
		return (new PayData()).fromXml(result);
	}

	/**
	 * 通过微信支付订单号查询支付结果
	 * @param transactionId
	 * @return
	 */
	public static boolean queryOrder(String transactionId) {
		log.info(String.format("begin to invoke queryOrder(%s)", transactionId));
		PayData data = new PayData();
		data.setTransactionId(transactionId);
		configPayData(data);
		String xml = data.toXml();
		log.info("queryOrder request xml:" + xml);
		String result = HttpUtil.postXml(WXConfig.UrlOrderQuery, xml);
		log.info("HttpUtil.postXml result: " + result);
		PayData res = (new PayData()).fromXml(result);
		return isSuccess(res);
	}

	/**
	 * 配置paydata，将需要的参数加上
	 * @param data
	 */
	private static void configPayData(PayData data) {
		data.setAppId(WXConfig.AppId); //公众账号ID
		data.setMchId(WXConfig.MchId); //商户号
		data.setBillCreateIp(WXConfig.BillCreateIp); //终端ip
		data.setNonceStr(GlobalUtil.getUUID()); //随机字符串
	}
	
	/**
	 * 是否成功
	 * @param data
	 * @return
	 */
	private static boolean isSuccess(PayData data) {
		log.info(String.format("data.getReturnCode: %s, data.getResultCode: %s", data.getReturnCode(), data.getResultCode()));
		return WXConfig.SUCCESS.equals(data.getReturnCode()) && WXConfig.SUCCESS.equals(data.getResultCode());
	}

}
