package com.supergenius.third.wxpay.qrcode.config;

import com.supergenius.global.conf.SysConf;

public class WXConfig {

	public static final String UrlUnifiedOrder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String UrlOrderQuery = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";
	public static final String MsgOk = "OK";
	public static final String MsgFail = "Fail";
	/**
	 * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 */
	public static String AppId = SysConf.BankWXPayAppId;
	public static String MchId = SysConf.BankWXPayMchId;
	public static String BillCreateIp = SysConf.BankWXPayBillCreateIp;
	public static String ApiKey = SysConf.BankWXPayApiKey;
	public static String certPath = SysConf.BankWXPayBasePath + SysConf.BankWXPayp12FilePath;

}
