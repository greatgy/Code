package com.supergenius.third.wxpay.qrcode.config;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.XmlUtil;

public class PayData {

	private SortedMap<String, Object> data = new TreeMap<String, Object>();
	private static final String NATIVE = "NATIVE";
	
	public PayData() { }
	
	/**
	 * 
	 * @param body
	 * @param tradeNo
	 * @param totalFee 单位是分
	 */
	public PayData(String body, String tradeNo, int totalFee) {
		this();
		this.setBody(body);
		this.setOutTradeNo(tradeNo);
		this.setTotalFee(totalFee);
		this.setTradeType(NATIVE); // NATIVE--原生扫码支付
	}
	
	public PayData(Map<String, String> map) {
		this.fromMap(map);
	}
	
	/**
	 * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 * @param notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.data.put("notify_url", notifyUrl);
	}
	
	public String getNotifyUrl() {
		return String.valueOf(this.data.get("notify_url"));
	}

	/**
	 * 公众账号ID
	 * @return
	 */
	public void setAppId(String appId) {
		this.data.put("appid", appId);
	}
	
	public String getAppId() {
		return String.valueOf(this.data.get("appid"));
	}
	
	/**
	 * 微信支付分配的商户号
	 * @param mchId
	 */
	public void setMchId(String mchId) {
		this.data.put("mch_id", mchId);
	}
	
	public String getMchId() {
		return String.valueOf(this.data.get("mch_id"));
	}
	
	/**
	 * 以分为单位
	 * @param fen
	 */
	public void setTotalFee(int fen) {
		this.data.put("total_fee", fen);
	}
	
	public int getTotalFee() {
		Object fen = this.data.get("total_fee");
		if (fen == null) {
			return 0;
		} else {
			return Integer.valueOf(fen.toString());
		}
	}

	public void setProductID(String id) {
		this.data.put("product_id", id);
	}
	
	public String getProductID() {
		return String.valueOf(this.data.get("product_id"));
	}
	
	public void setGoodsTag(String goodsTag) {
		this.data.put("goods_tag", goodsTag);
	}
	
	public String getGoodsTag() {
		return String.valueOf(this.data.get("goods_tag"));
	}
	
	/**
	 * 取值如下：JSAPI，NATIVE，APP等
	 * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
	 * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	 * @param tradeType
	 */
	public void setTradeType(String tradeType) {
		this.data.put("trade_type", tradeType);
	}
	
	public String getTradeType() {
		return String.valueOf(this.data.get("trade_type"));
	}
	
	/**
	 * 商户系统内部订单号，要求32个字符内、且在同一个商户号下唯一
	 * @param tradeNo
	 */
	public void setOutTradeNo(String tradeNo) {
		this.data.put("out_trade_no", tradeNo);
	}
	
	public String getOutTradeNo() {
		return String.valueOf(this.data.get("out_trade_no"));
	}
	
	/**
	 * 附加数据
	 * @param attach
	 */
	public void setAttach(String attach) {
		this.data.put("attach", attach);
	}
	
	public String getAttach() {
		return String.valueOf(this.data.get("attach"));
	}
	
	/**
	 * 商品描述
	 * @param body
	 */
	public void setBody(String body) {
		this.data.put("body", body);
	}
	
	public String getBody() {
		return String.valueOf(this.data.get("body"));
	}
	
	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 * @param feeType
	 */
	public void setFeeType(String feeType) {
		this.data.put("fee_type", feeType);
	}
	
	public String getFeeType() {
		return String.valueOf(this.data.get("fee_type"));
	}
	
	public void setCodeUrl(String codeUrl) {
		this.data.put("code_url", codeUrl);
	}

	/**
	 * 统一下单接口返回的二维码链接
	 * @return
	 */
	public String getCodeUrl() {
		return String.valueOf(this.data.get("code_url"));
	}
	
	/**
	 * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 * @param ip
	 */
	public void setBillCreateIp(String ip) {
		this.data.put("spbill_create_ip", ip);
	}
	
	public String getBillCreateIp() {
		return String.valueOf(this.data.get("spbill_create_ip"));
	}

	public void setNonceStr(String rand) {
		this.data.put("nonce_str", rand);
	}
	
	public String getNonceStr() {
		return String.valueOf(this.data.get("nonce_str"));
	}
	
	public void setReturnCode(String returnCode) {
		this.data.put("return_code", returnCode);
	}
	
	public String getReturnCode() {
		return String.valueOf(this.data.get("return_code"));
	}
	
	public void setReturnMsg(String returnMsg) {
		this.data.put("return_msg", returnMsg);
	}
	
	public String getReturnMsg() {
		return String.valueOf(this.data.get("return_msg"));
	}
	
	public void setResultCode(String resultCode) {
		this.data.put("result_code", resultCode);
	}
	
	public String getResultCode() {
		return String.valueOf(this.data.get("result_code"));
	}
	
	public void setSign(String sign) {
		this.data.put("sign", sign);
	}
	
	public String getSign() {
		return String.valueOf(this.data.get("sign"));
	}
	
	public void setOpenId(String openid) {
		this.data.put("openid", openid);
	}
	
	public String getOpenId() {
		return String.valueOf(this.data.get("openid"));
	}
	
	public void setBankType(String bankType) {
		this.data.put("bank_type", bankType);
	}
	
	public String getBankType() {
		return String.valueOf(this.data.get("bank_type"));
	}
	
	public void setCashFee(String cashFee) {
		this.data.put("cash_fee", cashFee);
	}
	
	/**
	 * 现金支付金额订单现金支付金额
	 * @return
	 */
	public String getCashFee() {
		return String.valueOf(this.data.get("cash_fee"));
	}
	
	public void setTransactionId(String transactionId) {
		this.data.put("transaction_id", transactionId);
	}
	
	/**
	 * 微信支付订单号
	 * @return
	 */
	public String getTransactionId() {
		return String.valueOf(this.data.get("transaction_id"));
	}
	
	public void setTimeEnd(String timeEnd) {
		this.data.put("time_end", timeEnd);
	}
	
	/**
	 * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 * @return
	 */
	public String getTimeEnd() {
		return String.valueOf(this.data.get("time_end"));
	}
	
	public PayData fromXml(String xml) {
		Map<String, String> map = XmlUtil.parseXmlToMap(xml);
		if (map != null && map.size() > 0) {
			if(map.get("return_code").equals("SUCCESS") && map.get("sign") != null) {
				if (!CheckSign(map)) {
					System.err.println("CheckSign failed!");
					return null; //验证签名失败，返回空
				}
			}
			this.fromMap(map);
		}
		return this;
	}

	/**
	 * 将map的值赋予PayData
	 * @param map
	 */
	private void fromMap(Map<String, String> map) {
		this.data.putAll(map);
	}

	/**
	 * 将对象转成xml
	 * @return
	 */
	public String toXml() {
		if (data.size() > 0) {
			sign(); // 签名
			StringBuilder builder = new StringBuilder("<xml>");
			for (Entry<String, Object> item : data.entrySet()) {
				if (item != null && item.getValue() != null) {
					if (item.getValue().getClass().isPrimitive()) {
						builder.append("<").append(item.getKey()).append(">").append(item.getValue().toString()).append("</").append(item.getKey()).append(">");
					} else {
						builder.append("<").append(item.getKey()).append(">").append("<![CDATA[").append(item.getValue().toString()).append("]]></").append(item.getKey()).append(">");
					}
				}
			}
			builder.append("</xml>");
			return builder.toString();
		}
		return null;
	}

	/**
	 * 签名，设置签名结果
	 */
	private void sign() {
		this.data.put("sign", createSign());
	}
	
	/**
	 * 生成签名
	 * @return
	 */
	private String createSign() {
		this.data.remove("sign");
		String str = toUrl();
//		System.out.println("toUrl:" + str);
		return DigestUtils.md5Hex(str).toUpperCase();
	}

	/**
	 * 验证签名
	 * @param map 验证map的值
	 * @return
	 */
	private boolean CheckSign(Map<String, String> map) {
		String sign = map.get("sign").toString();
		if (StrUtil.isNotEmpty(sign)) {
			PayData tmp = new PayData(map);
			return sign.equals(tmp.createSign());
		}
		return false;
	}

	/**
	 * 格式转化成url参数格式
	 * @return
	 */
	private String toUrl() {
		StringBuilder builder = new StringBuilder("");
		for (Entry<String, Object> item : data.entrySet()) {
			if (item != null && StrUtil.isNotEmpty(item.getValue())) {
				builder.append(item.getKey()).append("=").append(item.getValue()).append("&");
			}
		}
		builder.append("key=").append(WXConfig.ApiKey);
		return builder.toString();
	}

}
