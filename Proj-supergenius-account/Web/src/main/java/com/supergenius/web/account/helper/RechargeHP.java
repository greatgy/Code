package com.supergenius.web.account.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bocom.netpay.b2cAPI.BOCOMSetting;
import com.bocom.netpay.b2cAPI.NetSignServer;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.HttpUtil;
import com.genius.core.base.utils.I18N;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.MathUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.server.base.helper.BaseHP;
import com.hitrust.b2b.trustpay.client.b2b.FundTransferRequest;
import com.hitrust.b2b.trustpay.client.b2b.TrnxInfo;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItem;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItems;
import com.hitrust.b2b.trustpay.client.b2b.TrnxRemarks;
import com.hitrust.b2b.trustpay.client.b2b.TrnxResult;
import com.hitrust.trustpay.client.TrxException;
import com.hitrust.trustpay.client.TrxResponse;
import com.hitrust.trustpay.client.b2c.Order;
import com.hitrust.trustpay.client.b2c.OrderItem;
import com.hitrust.trustpay.client.b2c.PaymentRequest;
import com.hitrust.trustpay.client.b2c.PaymentResult;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.third.alipay.config.AlipayConfig;
import com.supergenius.third.alipay.util.AlipayNotify;
import com.supergenius.third.alipay.util.AlipaySubmit;
import com.supergenius.third.boc.util.BocOrderBuilder;
import com.supergenius.third.cmb.CmbUtil;
import com.supergenius.third.icbcB2C.pay.IcbcB2CNotifyImpl;
import com.supergenius.third.icbcB2C.pay.IcbcB2CPayImpl;
import com.supergenius.third.unionpay.QuickPayConf;
import com.supergenius.third.unionpay.UnionpayBase;
import com.supergenius.third.wxpay.qrcode.config.PayData;
import com.supergenius.third.wxpay.qrcode.config.WXConfig;
import com.supergenius.third.wxpay.util.WXPayUtil;
import com.supergenius.xo.account.entity.Account;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.third.unionpay.AcpService;
import com.supergenius.third.unionpay.LogUtil;
import com.supergenius.third.unionpay.SDKConfig;
import com.supergenius.third.unionpay.SDKConstants;

import icbcB2C.model.FormData;
import icbcB2C.model.NotifyData;
import icbcB2C.model.NotifyOrderInfo;
import icbcB2C.model.OrderInfo;
import icbcB2C.model.TranData;
import icbcB2C.notify.IcbcB2CNotify;
import icbcB2C.pay.IcbcB2CPay;

/**
 * 充值hp
 * @author liushaomin
 */
public class RechargeHP extends BaseHP{
	
	private static Logger log = LoggerFactory.getLogger(RechargeHP.class);
	
	/**
	 * 判断平台和对应的apiuid是否一致
	 * @param site
	 * @param apiuid
	 * @author liushaomin
	 */
	public static boolean CheckApiuid(String site, String apiuid) {
		if (ESite.sudoku.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteSudokuApiuid)) {
				return true;
			}
		} else if (ESite.supergenius.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteSuperGeniusApiuid)) {
				return true;
			}
		} else if (ESite.tpi.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteTpiApiuid)) {
				return true;
			}
		} else if (ESite.user.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteUserApiuid)) {
				return true;
			}
		} else if (ESite.moral.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteMoralApiuid)) {
				return true;
			}
		} else if (ESite.manager.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteManagerApiuid)) {
				return true;
			}
		} else if (ESite.sns.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteSnsApiuid)) {
				return true;
			}
		} else if (ESite.enterpriser.toString().equals(site)) {
			if (apiuid.equals(SysConf.SiteEnterpriserApiuid)) {
				return true;
			}
		} else if (ESite.demopay.toString().equals(site)) {
			if (apiuid.equals(SysConf.DemoPayApiuid)) {
				return true;
			}
		}
		// TODO 有新的站点使用account接口时需要改这里的代码
		return ESite.get(Integer.valueOf(site)) != null;
	}
	
	/**
	 * 据选择的银行获得post的数据，并post给银行
	 * @param model
	 * @param type
	 * @param account
	 * @param resulturl
	 * @param ipAddr
	 * @author liushaomin
	 */
	public static String getPostData(Map<String, Object> model, EBank type, Account account, String resulturl, String ipAddr) {
		log.info(String.format("begin to execute getPostData(EBank:%s, accountsn:%s, money:%s, resulturl:%s, customerIp:%s)", type.name(), account.getAccountsn(), account.getMoney(), resulturl, ipAddr));
		Map<String, String> errs = new HashMap<>();
		try {
			if (type == EBank.icbc) {//工商银行
				IcbcB2CPay pay = new IcbcB2CPayImpl();
				TranData tranData = new TranData();
				tranData.setOrderDate(account.getCreatetime().toString(DateUtil.FORMAT_DATE_YYYYMMddHHmmss));
//				tranData.setOrderDate("20131020" + DateUtil.NowTime().toString("HHmmss"));
//				tranData.setOrderDate("20130925123056");
				tranData.setGoodsType("1"); //取值“0”：虚拟商品；取值“1”，实物商品
				String merURL = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.icbc), account.getUid());
				tranData.setMerURL(merURL);//交易结束，将客户引导到商户的此url，即通过客户浏览器post交易结果信息到商户的此URL
				//tranData.setMerVAR();//商户自定义，当返回银行结果时，作为一个隐藏域变量，商户可以用此变量维护session等等
				//tranData.setMerCustomID();//买家用户号
				//tranData.setMerCustomPhone();//买家联系电话
				//tranData.setGoodsAddress();//收货地址
				List<OrderInfo> orders = new ArrayList<>();
				OrderInfo o = new OrderInfo();
				o.setInstallmentTimes("1");//分期付款期数
				o.setOrderid(account.getAccountsn());//订单号
				o.setAmount(StrUtil.format("#", MathUtil.muti(account.getMoney(), 100)));//订单金额，单位分,如1
				o.setGoodsName(account.getTypeName());//商品名称
//				o.setGoodsName(StrUtil.get(account.getName(), SysConst.UTF8, SysConst.GBK));//商品名称
//				o.setCarriageAmt(CarriageAmt);//已含运费金额
//				o.setGoodsID(GoodsID);//商品编号
//				o.setGoodsNum(GoodsNum);//商品数量
				orders.add(o);//至少有一个订单信息
				tranData.setOrderInfoVector(orders);
				FormData fd = pay.createFormData(SysConf.BankICBCConfigFile, tranData);
				String interfaceName = fd.getInterfaceName();
				String interfaceVersion = fd.getInterfaceVersion();
				String merCert = fd.getMerCert();
				String merSignMsg = fd.getMerSignMsg();
				String strTranData = fd.getTranData();
				model.put("interfaceName", interfaceName);//生成的接口名称
				model.put("interfaceVersion", interfaceVersion);//生成的接口版本号
				model.put("merCert", merCert);//生成的交易数据
				model.put("merSignMsg", merSignMsg);//生成的订单签名数据
				model.put("tranData", strTranData);//生成的商城证书公钥
				log.info("interfaceName:" + interfaceName);
				log.info("interfaceVersion:" + interfaceVersion);
				log.info("merCert:" + merCert);
				log.info("merSignMsg:" + merSignMsg);
				log.info("tranData:" + strTranData);
				log.info("returning bankpost_icbc ...");
				return "bankpost_icbc";
			} else if (type == EBank.unionpay) {//银联
				System.out.println(String.format("begin to execute getPostData(EBank:%s, accountsn:%s, money:%s, resulturl:%s, customerIp:%s)", type.name(), account.getAccountsn(), account.getMoney(), resulturl, ipAddr));
				//设置编码格式
				//resp.setContentType("text/html; charset="+ UnionpayBase.encoding);
				
				SDKConfig.getConfig().loadPropertiesFromSrc();
				String merId = QuickPayConf.merCode;           													 //商户号       
				String txnAmt = StrUtil.format("#", MathUtil.muti(account.getMoney(), 100));       			   //交易金额     
				String orderId = account.getAccountsn();      													  //订单号     
				String txnTime = account.getCreatetime().toString(DateUtil.FORMAT_DATE_YYYYMMddHHmmss);        //交易时间     
				Map<String, String> requestData = new HashMap<String, String>();
				/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
				requestData.put("version", UnionpayBase.version);   			  //版本号，全渠道默认值
				requestData.put("encoding", UnionpayBase.encoding); 			  //字符集编码，可以使用UTF-8,GBK两种方式
				requestData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
				requestData.put("txnType", "01");               			  //交易类型 ，01：消费
				requestData.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
				requestData.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
				requestData.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
				/***商户接入参数***/
				requestData.put("merId", merId);    	          			  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
				requestData.put("accessType", "0");             			  //接入类型，0：直连商户 
				requestData.put("orderId",orderId);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
				System.err.println("商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则"+orderId);
				requestData.put("txnTime", txnTime);        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
				requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）		
				requestData.put("txnAmt", txnAmt);             			      //交易金额，单位分，不要带小数点
				//requestData.put("reqReserved", "透传字段");        		      //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节。出现&={}[]符号时可能导致查询接口应答报文解析失败，建议尽量只传字母数字并使用|分割，或者可以最外层做一次base64编码(base64编码之后出现的等号不会导致解析失败可以不用管)。		
				
				requestData.put("riskRateInfo", "{commodityName=充值超天才}");
				//前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
				//如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
				//异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
				requestData.put("frontUrl",resulturl);
				//后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
				//后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
				//注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码 
				//    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
				//    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
				requestData.put("backUrl", String.format(QuickPayConf.merBackEndUrl, account.getUid()));
				// 订单超时时间。
				// 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
				// 此时间建议取支付时的北京时间加15分钟。
				// 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
				requestData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000));
				//////////////////////////////////////////////////
				//
				//       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
				//
				//////////////////////////////////////////////////
				
				/**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
				Map<String, String> submitFromData = AcpService.sign(requestData,UnionpayBase.encoding);  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
				String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();  //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
				System.err.println("请求地址"+requestFrontUrl);
				String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,UnionpayBase.encoding);   //生成自动跳转的Html表单
				LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);
				//将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
				//尝试注释，看看页面能否正常跳转
				//resp.getWriter().write(html);
				model.put(ViewKeyDict.html, html);
				System.err.println(html);
				log.info("returning bankpost_unionpay ...");
				return "bankpost_unionpay";
			} else if(type == EBank.cmbchina){//招商银行
				String branchId = SysConf.BankCmbBranchId; //开户分行号0010
				log.info("开户分行号branchId:" + branchId);
				String cono = SysConf.BankCmbCono;//商户号007639
				log.info("商户号cono:" + cono);
				String date = account.getCreatetime().toString(DateUtil.FORMAT_DATE_SHORT);//交易时间 格式20131115
				log.info("交易时间date:" + date);
				String money = StrUtil.format("#.##", account.getMoney());//交易金额 单位：元，如0.01
				log.info("交易金额money:" + money);
				String billno = account.getAccountsn().substring(6,16);//订单号（需要商户自己生成6-10位）
				log.info("订单号billno:" + billno);
				String merchantUrl = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.cmbchina), account.getUid());;//支付结果通知地址
				log.info("支付结果通知地址merchantUrl:" + merchantUrl);
				String MerchantPara = "";//商户参数
				log.info("商户参数MerchantPara:" + MerchantPara);
				model.put("branchid", branchId);
				model.put("cono", cono);
				model.put("date", date);
				model.put("money", money);
				model.put("billno", billno);
				model.put("merchantUrl",merchantUrl);
				model.put("MerchantPara",MerchantPara);
				log.info("returning bankpost_cmb ...");
				return "bankpost_cmb";
			} else if (type == EBank.paypal) {//paypal支付
				String money = StrUtil.format("#.##", account.getMoney());//交易金额 单位：元，如0.01
				log.info("交易金额money:" + money);
				String notify_url = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.paypal), account.getUid());
				log.info("后台处理地址notifyUrl:" + notify_url);
				model.put("money", money);
				model.put("notify_url", notify_url);
				model.put("resulturl", resulturl);
				model.put("business", SysConf.EmailPaypalEmailAddr);
				model.put("item_name", SysConf.WebSiteName);//商品名称（可选项）
				model.put("item_number", 125);//商品编码（可选项）
				model.put("currency_code", SysConf.BankPaypalCurrency_code);
				model.put("bn", SysConf.BankPaypalBn);
				model.put("cmd", SysConf.BankPaypalCmd);
				model.put("BankPaypalPostUrl", SysConf.BankPaypalPostUrl);
				log.info("returning bankpost_paypal ...");
				return "bankpost_paypal";
			}else if (type == EBank.bankcomm) {//交行
				String interfaceVersion = "1.0.0.0";
				String merID = BOCOMSetting.MerchantID;
				String orderid = account.getAccountsn();
				String orderDate = account.getCreatetime().toString(DateUtil.FORMAT_DATE_SHORT);//交易时间 格式20131115
				String orderTime = account.getCreatetime().toString("HHmmss");//交易时间 格式HHmmss
				String tranType = "0";// 0:B2C
				String amount = StrUtil.format("#.##", account.getMoney());//交易金额 单位：元，如0.01
				String curType = "CNY";
				String orderContent = "";//订单内容，非必填
				String orderMono = "";//商家备注，非必填
				String phdFlag = "";//物流配送标志，非必填
				String notifyType = "1";//0 不通知 1 通知 2 转页面
				String merURL = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.bankcomm), account.getUid());// 后台回调商户URL
				String goodsURL ="";//取货 URL,非必填
				String jumpSeconds ="";//自动跳转时间,非必填
				String payBatchNo = "";//商户批次号,非必填
				String proxyMerName = "";//代理商家名称，非必填
				String proxyMerType = "";//代理商家证件类型，非必填
				String proxyMerCredentials = "";//代理商家证件号码,非必填
				String netType = "0";//渠道编号
				String sourceMsg = interfaceVersion + "|" + merID + "|" + orderid + "|" + orderDate + "|" + orderTime + "|" + tranType
                        + "|" + amount + "|" + curType + "|" + orderContent + "|" + orderMono + "|" + phdFlag + "|"
                        + notifyType + "|" + merURL + "|" + goodsURL + "|" + jumpSeconds + "|" + payBatchNo + "|"
                        + proxyMerName + "|" + proxyMerType + "|" + proxyMerCredentials + "|" + netType;
				log.info("sourceMsg:" + sourceMsg);
				NetSignServer nss = new NetSignServer();
		        String merchantDN = BOCOMSetting.MerchantCertDN;
		        log.info("merchantDN:" + merchantDN);
		        nss.NSSetPlainText(sourceMsg.getBytes("GBK"));
		        byte bSignMsg [] = nss.NSDetachedSign(merchantDN);
		        if (nss.getLastErrnum() < 0) {
		        	log.info("ERROR:商户端签名失败");
		        }
		        String signMsg = new String(bSignMsg, "GBK");
		        log.info("signMsg:" + signMsg);
		        model.put("interfaceVersion", interfaceVersion);
		        model.put("merID", merID);
		        model.put("orderid", orderid);
		        model.put("orderDate", orderDate);
		        model.put("orderTime", orderTime);
		        model.put("tranType", tranType);
		        model.put("amount", amount);
		        model.put("curType", curType);
		        model.put("orderContent", orderContent);
		        model.put("orderMono", orderMono);
		        model.put("phdFlag", phdFlag);
		        model.put("notifyType", notifyType);
		        model.put("merURL", merURL);
		        model.put("goodsURL", goodsURL);
		        model.put("jumpSeconds", jumpSeconds);
		        model.put("payBatchNo", payBatchNo);
		        model.put("proxyMerName", proxyMerName);
		        model.put("proxyMerType", proxyMerType);
		        model.put("proxyMerCredentials", proxyMerCredentials);
		        model.put("netType", netType);
		        model.put("merSignMsg", signMsg);
		        model.put("orderUrl", BOCOMSetting.OrderURL);//提交到银行的URL
		        log.info("orderUrl:" + BOCOMSetting.OrderURL);
		        log.info("returning bankpost_comm ...");
		        return "bankpost_comm";
			} else if (type == EBank.abchina) {//农业银行
				Order tOrder = new Order();
				tOrder.setOrderNo(account.getAccountsn());//设定订单编号 （必要信息）
				tOrder.setExpiredDate(new Integer(30).intValue());//设定订单有效期 （必要信息）
				tOrder.setOrderDesc(account.getTypeName());//设定订单说明
				tOrder.setOrderDate(account.getCreatetime().toString("YYYY/MM/dd"));//设定订单日期（必要信息 - YYYY/MM/dd）
				tOrder.setOrderTime(account.getCreatetime().toString("HH:mm:ss"));//设定订单时间 （必要信息 - HH:mm:ss）
				tOrder.setOrderAmount(account.getMoney());//设定订单金额 （必要信息）
				tOrder.setOrderURL(SysConf.WebSiteUri);//设定订单网址
				tOrder.setBuyIP(ipAddr);//设定订单IP
				tOrder.addOrderItem(new OrderItem(account.getAccountsn(), account.getTypeName(), account.getMoney(), 1));//生成定单订单对象，并将订单明细加入定单中（可选信息）
				PaymentRequest tPaymentRequest = new PaymentRequest();
				tPaymentRequest.setOrder(tOrder);//设定支付请求的订单 （必要信息）
				tPaymentRequest.setProductType(PaymentRequest.PRD_TYPE_ONE);//设定商品种类 （必要信息） PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、... PaymentRequest.PRD_TYPE_TWO：实体商品
				tPaymentRequest.setPaymentType(PaymentRequest.PAY_TYPE_ABC);//设定支付类型 PaymentRequest.PAY_TYPE_ABC：农行卡支付 PaymentRequest.PAY_TYPE_INT：国际卡支付
				tPaymentRequest.setNotifyType("1");//设定商户通知方式 0：URL页面通知 1：服务器通知
				tPaymentRequest.setResultNotifyURL(resulturl);//设定支付结果回传网址 （必要信息）
				tPaymentRequest.setMerchantRemarks("");//设定商户备注信息
				tPaymentRequest.setPaymentLinkType("1");//设定支付接入方式
				TrxResponse tTrxResponse = tPaymentRequest.extendPostRequest(1);
				if (tTrxResponse.isSuccess()) {
					log.info("tTrxResponse.isSuccess(): return true");
					model.put(ViewKeyDict.url, tTrxResponse.getValue("PaymentURL"));
					log.info("ABChina url in getPostData(...):" + tTrxResponse.getValue("PaymentURL"));
					log.info("returning bankpost_abchina ...");
					return "bankpost_abchina";
				} else {
					log.info("failed tTrxResponse.isSuccess(): return false");
					log.info("tTrxResponse.getReturnCode()：" + tTrxResponse.getReturnCode());
					log.info("tTrxResponse.getErrorMessage()：" + tTrxResponse.getErrorMessage());
					errs.put(ViewKeyDict.result, "支付请求提交失败");
				}
			} else if (type == EBank.abchina_b2b) {//农业银行 企业B2B
				String merURL = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.abchina_b2b), account.getUid());
				String tMerchantTrnxNo = account.getAccountsn();//商户交易编号
				String tTrnxAmountStr = StrUtil.format("#.##", account.getMoney());//交易金额,如0.01
				String tTrnxDate = account.getCreatetime().toString("yyyy/MM/dd");//交易时间
				String tTrnxTime = account.getCreatetime().toString("HH:mm:ss");//交易时间
				String tAccountDBNo = SysConf.BankABCAccountDBNo;//收款方账号
				String tAccountDBName = new String(SysConf.Corporation.getBytes("UTF-8"), "GB2312");//收款方账户名
				String tAccountDBBank = "000000";//收款方账户开户行联行号
				String tResultNotifyURL = merURL;//
				String tMerchantRemarks = "";//商户备注信息
				double tTrnxAmount = 0;
				try {
					tTrnxAmount = Double.parseDouble(tTrnxAmountStr);
				} catch (NumberFormatException e) {
					logException(log, e);
				}
				TrnxItems tTrnxItems = new TrnxItems();
				tTrnxItems.addTrnxItem(new TrnxItem(account.getAccountsn(), account.getTypeName(), account.getMoney(), 1));
				TrnxRemarks tTrnxRemarks = new TrnxRemarks();
//				tTrnxRemarks.addTrnxRemark(new TrnxRemark("付款时间", tTrnxDate + " " + tTrnxTime));
//				tTrnxRemarks.addTrnxRemark(new TrnxRemark("其它说明", "能够使买方确信该交易是自己交易的信息"));
				TrnxInfo tTrnxInfo = new TrnxInfo();
				tTrnxInfo.setTrnxOpr(account.getUsername());//交易人员（可选信息）（提供买方操作员在商户的交易网站上输入姓名或员工代码，便于买方在后续交易中知道此交易是由谁发起的）
				tTrnxInfo.setTrnxRemarks(tTrnxRemarks);
				tTrnxInfo.setTrnxItems(tTrnxItems);
				FundTransferRequest tFundTransferRequest = new FundTransferRequest();
				tFundTransferRequest.setTrnxInfo(tTrnxInfo);//设定交易细项 （必要信息）
				tFundTransferRequest.setMerchantTrnxNo(tMerchantTrnxNo);//设定商户交易编号（必要信息）
				log.info("设定商户交易编号setMerchantTrnxNo:" + tMerchantTrnxNo);
				tFundTransferRequest.setTrnxAmount(tTrnxAmount);//设定交易金额 （必要信息）
				log.info("设定交易金额setTrnxAmount:" + tTrnxAmount);
				tFundTransferRequest.setTrnxDate(tTrnxDate);//设定交易日期 （必要信息）
				log.info("设定交易日期setTrnxDate:" + tTrnxDate);
				tFundTransferRequest.setTrnxTime(tTrnxTime);//设定交易时间 （必要信息）
				log.info("设定交易时间setTrnxTime:" + tTrnxTime);
				tFundTransferRequest.setAccountDBNo(tAccountDBNo);//设定收款方账号 （必要信息）
				log.info("设定收款方账号setAccountDBNo:" + tAccountDBNo);
				tFundTransferRequest.setAccountDBName(tAccountDBName);//设定收款方账户名（必要信息）
				log.info("设定收款方账户名setAccountDBName:" + tAccountDBName);
				tFundTransferRequest.setAccountDBBank(tAccountDBBank);//设定收款方账户开户行联行号（必要信息）
				log.info("设定收款方账户开户行联行号setAccountDBBank:" + tAccountDBBank);
				tFundTransferRequest.setResultNotifyURL(tResultNotifyURL);//设定交易结果回传网址（必要信息）
				log.info("设定交易结果回传网址setResultNotifyURL:" + tResultNotifyURL);
				tFundTransferRequest.setMerchantRemarks(tMerchantRemarks);//设定商户备注信息
				com.hitrust.b2b.trustpay.client.TrxResponse tTrxResponse = tFundTransferRequest.postRequest();
				if (tTrxResponse.isSuccess()) {//直接支付请求提交成功,将客户端导向出示买方企业客户证书页面
					log.info("TrnxType:" + tTrxResponse.getValue("TrnxType"));
					log.info("TrnxAMT:" + tTrxResponse.getValue("TrnxAMT"));
					log.info("MerchantID:" + tTrxResponse.getValue("MerchantID"));
					log.info("MerchantTrnxNo:" + tTrxResponse.getValue("MerchantTrnxNo"));
					String url = tTrxResponse.getValue("PaymentURL");
					log.info("PaymentURL:" + url);
					model.put(ViewKeyDict.url, url);
					return "bankpost_abchina";
				} else {//直接支付请求提交失败，商户自定后续动作
					log.info("ReturnCode:" + tTrxResponse.getReturnCode());
					log.info("ErrorMessage:" + tTrxResponse.getErrorMessage());
				}
			} else if (type == EBank.boc) {//中国银行
				String bankpostbockurl = SysConf.BankBOCPostUrl;
				String merchantno = SysConf.BankBOCMerchantno;//商户号 104110059475555
				String orderNo = account.getAccountsn();//订单号
				String orderTime = account.getCreatetime().toString(DateUtil.FORMAT_DATE_YYYYMMddHHmmss);//设定订单时间 YYYYMMDDHHMISS
				String orderTimeoutDate = account.getCreatetime().plusDays(30).toString(DateUtil.FORMAT_DATE_YYYYMMddHHmmss) ;// 过期时间:订单时间加30天
				String orderAmount = StrUtil.format("#.##", account.getMoney());//订单金额，如0.01
				String payType = SysConf.BankBOCPayType;//支付类型： 1
				String curcode = SysConf.BankBOCCurcode;//支付币种： 目前只支持人保民币001；
				String orderNote = account.getTypeName();//订单说明，必填!
				String orderUrl = resulturl;//回调商户url
				log.info("商户号merchantno:" + merchantno);
				log.info("订单号orderNo:" + orderNo);
				log.info("订单时间orderTime:" + orderTime);
				log.info("过期时间orderTimeoutDate:" + orderTimeoutDate);
				log.info("订单金额orderAmount:" + orderAmount);
				log.info("支付类型payType:" + payType);
				log.info("支付币种curcode:" + curcode);
				log.info("订单说明orderNote:" + orderNote);
				log.info("回调商户url orderUrl:" + orderUrl);
				BocOrderBuilder bob = new BocOrderBuilder(orderNo,orderTime,orderAmount);//数字签名
				try {
					String signatureData = bob.getSignatureData();
					log.info("signatureData：" + signatureData);
					model.put("signData:", signatureData);
					model.put("orderNo:", orderNo);
					model.put("merchantNo:", merchantno);
					model.put("payType:", payType);
					model.put("curCode:", curcode);
					model.put("orderNote:", orderNote);
					model.put("orderUrl:", orderUrl);
					model.put("orderTime:", orderTime);
					model.put("orderAmount:", orderAmount);
					model.put("orderTimeoutDate:", orderTimeoutDate);
					model.put("bankpostbocurl",bankpostbockurl);
					log.info("returning bankpost_boc ...");
					return "bankpost_boc";
				} catch (Exception e) {
					e.printStackTrace();
					logException(log, e);
					errs.put(ViewKeyDict.result, e.getMessage());
				}
			} else if (type == EBank.alipay) {//支付宝 参数说明https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Za3oIY&treeId=62&articleId=104743&docType=1#s0
				//把请求参数打包成数组
				String notifyUrl = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.alipay), account.getUid());
				log.info("notifyUrl:" + notifyUrl);
				log.info("订单号orderNo:" + account.getAccountsn());
				log.info("订单时间orderTime:" + account.getCreatetime().toString(DateUtil.FORMAT_DATE_YYYYMMddHHmmss));
				log.info("订单金额money:" + StrUtil.format("#.##", account.getMoney()));
				log.info("订单类型typeName:" + account.getTypeName());
				Map<String, String> params = new HashMap<String, String>();
				 
				if (model.get(ViewKeyDict.ismobile) != null && (Boolean) model.get(ViewKeyDict.ismobile)) {
					params.put("service", AlipayConfig.serviceMobilePay);
					log.info("service:" + AlipayConfig.serviceMobilePay);
				} else {
					params.put("service", AlipayConfig.servicePCPay);
					log.info("service:" + AlipayConfig.servicePCPay);
				}
		        params.put("partner", AlipayConfig.partner);
		        params.put("seller_id", AlipayConfig.seller_id);
		        params.put("_input_charset", AlipayConfig.input_charset);
				params.put("payment_type", AlipayConfig.payment_type);
				params.put("notify_url", notifyUrl);
				params.put("return_url", resulturl);
				params.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
				params.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
				params.put("out_trade_no", account.getAccountsn());
				params.put("subject", account.getTypeName());
				params.put("total_fee", String.valueOf(account.getMoney()));
//				sParaTemp.put("body", "商品描述");
				//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
				String html = AlipaySubmit.buildRequest(params, "get", "Submit");
				model.put(ViewKeyDict.html, html);
				log.info("Alipay html in getPostData(...):" + html);
				log.info("returning bankpost_alipay ...");
				return "bankpost_alipay";
			} else if (type == EBank.tenpay) {//财付通
				return "bankpost_tenpay";
			} else if (type == EBank.wxpay) {// 微信支付
				String notifyUrl = String.format(String.format(SysConf.BankMerBackEndUrl, EBank.wxpay), account.getUid());
				PayData data = new PayData("超天才-" + account.getTypeName(), account.getAccountsn(), (int)MathUtil.muti(account.getMoney(), 100));
				data.setNotifyUrl(notifyUrl);
				String url = WXPayUtil.GetPayUrl(data);
				log.info("wxpay url:" + url);
				if (url != null) {
					model.put(ViewKeyDict.url, WebUtil.encodeURI(url));
				} else {
					model.put(ViewKeyDict.err, true);
				}
				model.put(ViewKeyDict.account, account);
				return "bankpost_wxpay";
			} else if (type == EBank.payeezy) {// 美洲银行  相关帮助信息https://support.payeezy.com/hc/en-us/sections/200927229-Hosted-Checkout
				String x_login = SysConf.BankPayeezylogin;
				String x_amount = StrUtil.format("#.##", account.getMoney());//交易金额 单位：元，如0.01
				log.info("交易金额money:" + x_amount);
				String transactionKey = SysConf.BankPayeezyTransactionKey;
				Random generator = new Random();
				int x_fp_sequence = generator.nextInt(1000); // 计算hash所需随机参数
				long x_fp_timestamp = System.currentTimeMillis()/1000;
				SecretKey key = new SecretKeySpec(transactionKey.getBytes(), "HmacMD5");
				Mac mac = null;
				try {
					mac = Mac.getInstance("HmacMD5");
					mac.init(key);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				String inputstring = x_login + "^" + x_fp_sequence + "^" + x_fp_timestamp + "^" + x_amount + "^";
				byte[] result = mac.doFinal(inputstring.getBytes());
				StringBuffer strbuf = new StringBuffer(result.length * 2);
				for(int i=0; i< result.length; i++){
				    if(((int) result[i] & 0xff) < 0x10)
				        strbuf.append("0");
				    strbuf.append(Long.toString((int) result[i] & 0xff, 16));
				}
				String x_fp_hash = strbuf.toString();
				log.info("+++++++++++hash:"+x_fp_hash);
				model.put("x_login", x_login);
				model.put("x_amount", x_amount);
				model.put("x_fp_sequence", String.valueOf(x_fp_sequence));
				model.put("x_fp_timestamp", String.valueOf(x_fp_timestamp));
				model.put("x_fp_hash", x_fp_hash);
				model.put("BankPayeezyPostUrl", SysConf.BankPayeezyPostUrl);
				model.put("x_relay_url", SysConf.BankPayeezyRechargeResultUrl);
				log.info("returning bankpost_payeezy ...");
				return "bankpost_payeezy";
			}
		} catch (UnsupportedEncodingException e) {
			errs.put(ViewKeyDict.result, e.getMessage());
			logException(log, e);
		}
		model.put(ViewKeyDict.err, errs);
		return "error";
	}

	/**
	 * 处理从银行传递过来的参数
	 * 返回ture 为充值成功
	 * @param model
	 * @param type
	 * @param account 
	 * @param request
	 * @return 是否成功支付
	 * @throws IOException 
	 */
	public static boolean receivePostData(Map<String, Object> model, Account account, Map<String, String> msgmap, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info(String.format("begin to execute receivePostData(EBank:%s, accountsn:%s, money:%s)", account.getBankName(), account.getAccountsn(), account.getMoney()));
		if (receiveDataFromBankHandler(model, account, msgmap, request, response)) {
			log.info("success, return true execute receiveDataFromBankHandler(...)");
			return true;
		} else {
			log.info("failed, return false execute receiveDataFromBankHandler(...)");
			return false;
		}
	}
	
	/**
	 * 处理银行返回的充值信息，判断是否充值成功
	 * @param account
	 * @param errs
	 * @param request
	 * @return
	 * @author liushaomin
	 * @throws IOException 
	 */
	private static boolean receiveDataFromBankHandler(Map<String, Object> model, Account account, Map<String, String> msgmap, HttpServletRequest request ,HttpServletResponse response) throws IOException {
		log.info(String.format("begin to execute receiveDataFromBankHandler(EBank:%s, accountsn:%s, money:%s)", account.getBankName(), account.getAccountsn(), account.getMoney()));
		if (msgmap == null) {                                                                                                        
			msgmap = new HashMap<String, String>();
		}
		if (account.getBank() == EBank.icbc) {//工行
			String strNotifyData = request.getParameter("notifyData");
			try {
				log.info("notifyData:" + StrUtil.get(strNotifyData, "GBK", "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				logException(log, e1);
			}
			String strSignMsg = request.getParameter("signMsg");
			log.info("signMsg:" + strSignMsg);
			IcbcB2CNotify notify = new IcbcB2CNotifyImpl();
			NotifyData notifyData = new NotifyData();
			notifyData = notify.createNotifyData(strNotifyData, strSignMsg, SysConf.BankICBCConfigFile);
			if(notifyData==null) {
				msgmap.put(ViewKeyDict.bank_notifydata_null, I18N.getError(ViewKeyDict.bank_notifydata_null, Locale.CHINA));
			} else {
				log.info("TranStat:" + notifyData.getTranStat());//订单处理状态
				String comment = notifyData.getComment();
				log.info("Comment:" + comment);
				log.info("CurType:" + notifyData.getCurType());//支付币种
				log.info("InterfaceName:" + notifyData.getInterfaceName());//接口名称
				log.info("InterfaceVersion:" + notifyData.getInterfaceVersion());//接口版本号
				log.info("JoinFlag:" + notifyData.getJoinFlag());//客户联名标志
				log.info("MerID:" + notifyData.getMerID());//商户代码
				log.info("NotifyData:" + notifyData.getNotifyData());//NotifyData的明文XML串
				log.info("NotifyDate:" + notifyData.getNotifyDate());//返回通知日期时间
				log.info("OrderDate:" + notifyData.getOrderDate());//交易日期时间
				log.info("TranBatchNo:" + notifyData.getTranBatchNo());//批次号
				log.info("UserNum:" + notifyData.getUserNum());//联名会员号
				log.info("VerifyJoinFlag:" + notifyData.getVerifyJoinFlag());//检验联名标志
				List<?> orderInfoList = notifyData.getSubOrderInfoList();
				for(int i = 0; i < orderInfoList.size(); i++) {
					int num = i + 1;
					log.info("第" + num + "笔订单信息：");
					NotifyOrderInfo notifyOrderInfo = (NotifyOrderInfo)orderInfoList.get(i);
					log.info("orderid:" + notifyOrderInfo.getOrderid());//订单号
					log.info("amount:" + notifyOrderInfo.getAmount());//订单金额
					log.info("installmentTimes:" + notifyOrderInfo.getInstallmentTimes());//分期付款期数
					log.info("merAcct:" + notifyOrderInfo.getMerAcct());//商户账号
					log.info("tranSerialNo:" + notifyOrderInfo.getTranSerialNo());//银行指令序号
				}
				if (notifyData.getTranStat().equals("1")) {
					log.info("success in receiveDataFromBankHandler, return true");
					return true;
				} else if(notifyData.getTranStat().equals("1")) {
					msgmap.put(ViewKeyDict.result, "交易失败");
				} else if(notifyData.getTranStat().equals("3")) {
					msgmap.put(ViewKeyDict.result, "交易可疑");
				}
			}
		} else if (account.getBank() == EBank.unionpay) {//银联
			LogUtil.writeLog("BackRcvResponse接收后台通知开始");
			
			String encoding = request.getParameter(SDKConstants.param_encoding);
			// 获取银联通知服务器发送的后台通知参数
			Map<String, String> reqParam = getAllRequestParam(request);
			LogUtil.printRequestLog(reqParam);
			
			//重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
			if (!AcpService.validate(reqParam, encoding)) {
				LogUtil.writeLog("验证签名结果[失败].");
				//验签失败，需解决验签问题
				
			} else {
				LogUtil.writeLog("验证签名结果[成功].");
				//【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
				
				String orderId =reqParam.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
				String respCode = reqParam.get("respCode");
				//判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
				return true;
			}
			LogUtil.writeLog("BackRcvResponse接收后台通知结束");
			//返回给银联服务器http 200  状态码
			//response.getWriter().print("ok");
		} else if(account.getBank() == EBank.cmbchina){//招商银行
			String url = request.getQueryString();
			log.info("url:" + url);
			try {
				url = URIUtil.decode(url);
				log.info("URIUtil.decode: " + url);
			} catch (URIException e) {
				e.printStackTrace();
				logException(log, e);
			}
			String succeed = request.getParameter("Succeed");//取值Y(成功)或N(失败)；
			String billNo = request.getParameter("BillNo");//定单号
			String amount = request.getParameter("Amount");//实际支付金额
			String date = request.getParameter("Date");//交易日期
			String msg = request.getParameter("Msg");//银行通知用户的支付结果消息。信息的前38个字符格式为：4位分行号＋6位商户号＋8位银行接受交易的日期＋20位银行流水号；可以利用交易日期＋银行流水号＋定单号对该定单进行结帐处理；
			String signature = request.getParameter("Signature");
			log.info("Succeed：" + succeed);
			log.info("billNo：" + billNo);
			log.info("amount：" + amount);
			log.info("date：" + date);
			log.info("msg：" + msg);
			log.info("signature：" + signature);
			if (CmbUtil.checkFromBank(url)) {
				log.info(String.format("CmbUtil.checkFromBank(%s) == true", url));
				if (succeed.equals("Y")) {
					log.info("success in receiveDataFromBankHandler, succeed.equals(Y)");
					return true;
//					if(SettleOrderHandler(account)) {
//						log.info("SettleOrderHandler(account) return true");
//						log.info("success in receiveDataFromBankHandler, return true");
//						return true;
//					} else {
//						errs.put(ErrKeyConst.result, "支付成功后，银行结账失败");
//						return false;
//					}
				}
			} else {
				log.info(String.format("CmbUtil.checkFromBank(%s) == false", url));
				msgmap.put(ViewKeyDict.result, "URL不正确");
			}
		} else if (account.getBank() == EBank.bankcomm) {//交行
			String notifyMsg = request.getParameter("notifyMsg");//获取银行通知结果
			log.info("notifyMsg:" + notifyMsg);
			int lastIndex = notifyMsg.lastIndexOf("|");
			String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length());//获取签名信息
			log.info("signMsg:" + signMsg);
			String srcMsg = notifyMsg.substring(0, lastIndex + 1);
			log.info("srcMsg:" + srcMsg);
			int veriyCode = -1;
			NetSignServer nss = new NetSignServer();
			try {
				nss.NSDetachedVerify(signMsg.getBytes("GBK"), srcMsg.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logException(log, e);
				return false;
			}
			veriyCode = nss.getLastErrnum();
			if (veriyCode < 0) {//验签出错
				msgmap.put(ViewKeyDict.result, "商户端验证签名失败：return code:" + veriyCode);
			}
			String[] arr = srcMsg.split(ViewKeyDict.vLineRegSplitter);
			try {
				log.info(String.format("商户客户号:%s", arr[0]));
				log.info(String.format("订单编号:%s", arr[1]));
				log.info(String.format("交易金额:%s", arr[2]));
				log.info(String.format("交易币种:%s", arr[3]));
				log.info(String.format("平台批次号:%s", arr[4]));
				log.info(String.format("商户批次号:%s", arr[5]));
				log.info(String.format("交易日期:%s", arr[6]));
				log.info(String.format("交易时间:%s", arr[7]));
				log.info(String.format("交易流水号:%s", arr[8]));
				log.info(String.format("交易结果:%s", arr[9]));
				log.info(String.format("手续费总额:%s", arr[10]));
				log.info(String.format("银行卡类型:%s", arr[11]));
				log.info(String.format("银行备注:%s", arr[12]));
				log.info(String.format("错误信息描述:%s", arr[13]));
				if (arr[9].equals("1")) {
					log.info("success in receiveDataFromBankHandler, return true");
					return true;
				} else {
					msgmap.put(ViewKeyDict.result, arr[13]);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				logException(log, e);
			}
		} else if (account.getBank() == EBank.abchina) {//农业银行
			PaymentResult tResult;
			try {
				String msg = request.getParameter("MSG");
				tResult = new PaymentResult(msg);
				log.info(String.format("交易类型TrxType:%s", tResult.getValue("TrxType")));
				log.info(String.format("订单号OrderNo:%s", tResult.getValue("OrderNo")));
				log.info(String.format("订单金额Amount:%s", tResult.getValue("Amount")));
				log.info(String.format("交易批次号BatchNo:%s", tResult.getValue("BatchNo")));
				log.info(String.format("交易凭证号VoucherNo:%s", tResult.getValue("VoucherNo")));
				log.info(String.format("银行交易日期HostDate:%s", tResult.getValue("HostDate")));
				log.info(String.format("银行交易时间HostTime:%s", tResult.getValue("HostTime")));
				log.info(String.format("商户备注信息MerchantRemarks:%s", tResult.getValue("MerchantRemarks")));
				log.info(String.format("消费者支付方式PayType:%s", tResult.getValue("PayType")));
				log.info(String.format("支付结果通知方式NotifyType:%s", tResult.getValue("NotifyType")));
				log.info(String.format("银行返回交易流水号iRspRef:%s", tResult.getValue("iRspRef")));
				if (tResult.isSuccess()) {//支付成功
					log.info("success in receiveDataFromBankHandler, return true");
					return true;
				} else {//支付失败
					log.info(String.format("响应码returnCode:%s", tResult.getReturnCode()));
					log.info(String.format("支付失败原因errorMessage:%s", tResult.getErrorMessage()));
				}
			} catch (TrxException e) {
				e.printStackTrace();
				logException(log, e);
			}
		} else if (account.getBank() == EBank.abchina_b2b) {//农业银行 企业B2B
			try {
				String msg = request.getParameter("MSG");
				log.info("msg:" + msg);
				TrnxResult tResult = new TrnxResult(msg);
				if (tResult.isSuccess()) {//交易成功
					log.info(String.format("商户代码MerchantID:%s", tResult.getValue("MerchantID")));
					log.info(String.format("企业客户代码CorporationCustomerNo:%s", tResult.getValue("CorporationCustomerNo")));
					log.info(String.format("商户交易编号MerchantTrnxNo:%s", tResult.getValue("MerchantTrnxNo")));
					log.info(String.format("交易流水号TrnxSN:%s", tResult.getValue("TrnxSN")));
					log.info(String.format("交易类型TrnxType:%s", tResult.getValue("TrnxType")));
					log.info(String.format("交易金额TrnxAMT:%s", tResult.getValue("TrnxAMT")));
					log.info(String.format("冻结序号FreezeNo:%s", tResult.getValue("FreezeNo")));
					log.info(String.format("原冻结序号OrginalFreezeNo:%s", tResult.getValue("OrginalFreezeNo")));
					log.info(String.format("付款方帐号AccountNo:%s", tResult.getValue("AccountNo")));
					log.info(String.format("付款方帐户名AccountName:%s", tResult.getValue("AccountName")));
					log.info(String.format("付款方开户行联行号AccountBank:%s", tResult.getValue("AccountBank")));
					log.info(String.format("收款方帐号AccountDBNo:%s", tResult.getValue("AccountDBNo")));
					log.info(String.format("收款方帐户名AccountDBName:%s", tResult.getValue("AccountDBName")));
					log.info(String.format("收款方开户行联行号AccountDBBank:%s", tResult.getValue("AccountDBBank")));
					log.info(String.format("交易时间TrnxTime:%s", tResult.getValue("TrnxTime")));
					log.info(String.format("交易状态TrnxStatus:%s", tResult.getValue("TrnxStatus")));
					log.info("success in receiveDataFromBankHandler, return true");
					return true;
				} else {//交易失败
					log.info(String.format("商户代码MerchantID:%s", tResult.getValue("MerchantID")));
					log.info(String.format("企业客户代码CorporationCustomerNo:%s", tResult.getValue("CorporationCustomerNo")));
					log.info(String.format("商户交易编号MerchantTrnxNo:%s", tResult.getValue("MerchantTrnxNo")));
					log.info(String.format("交易类型TrnxType:%s", tResult.getValue("TrnxType")));
					log.info(String.format("交易金额TrnxAMT:%s", tResult.getValue("TrnxAMT")));
					log.info(String.format("交易时间TrnxTime:%s", tResult.getValue("TrnxTime")));
					log.info(String.format("交易状态TrnxStatus:%s", tResult.getValue("TrnxStatus")));
					log.info(String.format("响应码returnCode:%s", tResult.getReturnCode()));
					log.info(String.format("支付失败原因errorMessage:%s", tResult.getErrorMessage()));
				}
			} catch (com.hitrust.b2b.trustpay.client.TrxException e) {
				e.printStackTrace();
				logException(log, e);
			}
		} else if (account.getBank() == EBank.boc) {//中国银行
			String url = request.getQueryString();
			log.info("url:" + url);
			try {
				url = URIUtil.decode(url);
				log.info("URIUtil.decode: " + url);
			} catch (URIException e) {
				e.printStackTrace();
				logException(log, e);
			}
			String merchantNo = request.getParameter("merchantNo");//商户号
			String orderNo = request.getParameter("orderNo");//订单号
			String orderSeq = request.getParameter("orderSeq");//银行订单流水号
			String cardTyp = request.getParameter("cardTyp");//银行卡类别
			String payTime = request.getParameter("payTime");//支付时间
			String orderStatus = request.getParameter("orderStatus");//订单状态
			String payAmount = request.getParameter("payAmount");//支付金额
			String orderIp = request.getParameter("orderIp");//客户支付IP地址
			String orderRefer = request.getParameter("orderRefer");//客户浏览器Refer信息
			String bankTranSeq = request.getParameter("bankTranSeq");//银行交易流水号
			String returnActFlag = request.getParameter("returnActFlag");//返回操作类型
			String phoneNum = request.getParameter("phoneNum");//电话号码
			String signData = request.getParameter("signData");//中行签名数据
			String dn="CN=铁道部资金清算中心_0c7192965f46f7a0e9069c90016596ff55da3073_铁道部_11,C=CN";
			log.info("商户号merchantNo:" + merchantNo);
			log.info("订单号orderNo:" + orderNo);
			log.info("银行订单流水号orderSeq:" + orderSeq);
			log.info("银行卡类别cardTyp:" + cardTyp);
			log.info("支付时间payTime:" + payTime);
			log.info("订单状态orderStatus:" + orderStatus);
			log.info("支付金额payAmount:" + payAmount);
			log.info("客户支付IP地址orderIp:" + orderIp);
			log.info("客户浏览器Refer信息orderRefer:" + orderRefer);
			log.info("银行交易流水号bankTranSeq:" + bankTranSeq);
			log.info("返回操作类型returnActFlag:" + returnActFlag);
			log.info("电话号码phoneNum:" + phoneNum);
			log.info("中行签名数据signData:" + signData);
			StringBuffer sb = new StringBuffer();
			//示例明文由管道符“|”分割，如：商户号|商户订单号|银行订单流水号|银行卡类别|支付时间|订单状态|支付金额		merchantNo|orderNo|orderSeq|cardTyp|payTime|orderStatus|payAmount
			String spliter="|";
			sb.append(merchantNo).append(spliter);
			sb.append(orderNo).append(spliter);
			sb.append(orderSeq).append(spliter);
			sb.append(cardTyp).append(spliter);
			sb.append(payTime).append(spliter);
			sb.append(orderStatus).append(spliter);
			sb.append(payAmount);
			BocOrderBuilder boc = new BocOrderBuilder();
			try{
				if (boc.verifyData(signData, sb.toString(), dn)) {
					log.info("boc.verifyData return true");
					if (orderStatus.equals("0")) {//0:未处理 1:支付 2:撤销 3:退货 4:未明 5:失败
						msgmap.put(ViewKeyDict.msg, "未处理");
						log.info("0:未处理");
					} else if (orderStatus.equals("1")) {
						log.info("1:支付");
						log.info("success in receiveDataFromBankHandler, return true");
					} else if (orderStatus.equals("2")) {
						msgmap.put(ViewKeyDict.msg, "撤销");
						log.info("2:撤销");
					} else if (orderStatus.equals("3")) {
						msgmap.put(ViewKeyDict.msg, "退货");
						log.info("3:退货");
					} else if (orderStatus.equals("4")) {
						msgmap.put(ViewKeyDict.msg, "未明");
						log.info("4:未明");
					} else if (orderStatus.equals("5")) {
						msgmap.put(ViewKeyDict.msg, "失败");
						log.info("5:失败");
					}
					return true;
				} else {
					log.info("boc.verifyData return false");
				}
				
			}catch(Exception e){
				logException(log, e);
				msgmap.put(ViewKeyDict.exception, e.getMessage());
			}
		} else if (account.getBank() == EBank.alipay) {//支付宝
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map<?, ?> requestParams = request.getParameterMap();
			for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				log.info(String.format("request.Param %s:" , name) + valueStr);
				params.put(name, valueStr);
			}

			try {
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8"); //商户订单号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8"); //支付宝交易号
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8"); //交易状态
				log.info("支付宝传递过来的订单号out_trade_no:" + out_trade_no);
				log.info("支付宝传递过来的交易号trade_no:" + trade_no);
				log.info("支付宝传递过来的交易状态trade_status:" + trade_status);
				if(AlipayNotify.verify(params)){//验证成功
					if(trade_status.equals("TRADE_FINISHED")){ //TRADE_FINISHED（普通即时到账的交易成功状态）；
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
						log.info("success in receiveDataFromBankHandler, return true");
						return true;
					//注意：退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					} else if (trade_status.equals("TRADE_SUCCESS")){ //TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
						log.info("success in receiveDataFromBankHandler, return true");
						return true;
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					}
					msgmap.put(ViewKeyDict.result, "trade_status不正确");
				} else {
					msgmap.put(ViewKeyDict.result, "验证失败");
				}
			} catch (UnsupportedEncodingException e) {
				logException(log, e);
			}
		} else if (account.getBank() == EBank.tenpay) {//财付通
		} else if (account.getBank() == EBank.wxpay) {//微信支付
			if (model.get(ViewKeyDict.data) != null) {
				PayData data = new PayData();
				data.fromXml(model.get(ViewKeyDict.data).toString());
				PayData res = new PayData();
				if (StrUtil.isNotEmpty(data.getTransactionId())) {
					if (WXPayUtil.queryOrder(data.getTransactionId())) {
						res.setReturnCode(WXConfig.SUCCESS);
						res.setReturnMsg(WXConfig.MsgOk);
						log.info("success in receiveDataFromBankHandler, return true");
						model.put(ViewKeyDict.response, res.toXml());
						return true;
					}
				}
				res.setReturnCode(WXConfig.FAIL);
				res.setReturnMsg(WXConfig.MsgFail);
				log.info("failed in receiveDataFromBankHandler, return false");
				model.put(ViewKeyDict.response, res.toXml());
			}
			return false;
		} else if (account.getBank() == EBank.paypal) {
				//  从 PayPal ft读取 POST 信息同时添加变量"cmd"
				@SuppressWarnings("unchecked")
				Enumeration<Object> en = request.getParameterNames();
				String str = "cmd=_notify-validate";
				while(en.hasMoreElements()) {
					String paramName = (String)en.nextElement();
					String paramValue = request.getParameter(paramName);
					str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue, "utf-8");
				}
				log.info("------------------------------回返给paypal的表单验证：" + str);
				//建议在此将接受到的信息 str 记录到日志文件中以确认是否收到 IPN 信息
				//将信息 POST 回给 PayPal 进行验证
				//设置 HTTP 的头信息
				//在 Sandbox 情况下，设置：
				//URL u= new URL(“http://www.sandbox.paypal.com/cgi-bin/webscr”);
				URL u = new URL(SysConf.BankPaypalPostUrl);
				URLConnection uc = u.openConnection();
				HttpURLConnection httpUrlConnection = (HttpURLConnection) uc;
				httpUrlConnection.setRequestMethod("POST");
				httpUrlConnection.setDoOutput(true);
				httpUrlConnection.setDoInput(true);
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				httpUrlConnection.connect(); 
//				uc.setDoOutput(true);
//				uc.setDoInput(true);
//				uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//				uc.connect();
//				PrintWriter pw = new PrintWriter(uc.getOutputStream());
				PrintWriter pw = new PrintWriter(httpUrlConnection.getOutputStream());
				pw.println(str);
				pw.close();
				//接受 PayPal 对 IPN 回发的回复信息
				BufferedReader in= new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
				String res = in.readLine();
				in.close();
				
				//将 POST 信息分配给本地变量，可以根据您的需要添加
				//该付款明细所有变量可参考：
				//https://www.paypal.com/IntegrationCenter/ic_ipn-pdt-variable-reference.html
				log.info("------------------------------------------paypal反回的验证信息："+res);
				String itemName = request.getParameter("item_name");
				String itemNumber = request.getParameter("item_number");
				String paymentStatus = request.getParameter("payment_status");
				String paymentAmount = request.getParameter("mc_gross");
				String paymentCurrency = request.getParameter("mc_currency");//货币种类
				String txnId = request.getParameter("txn_id");//事务id
				String receiverEmail = request.getParameter("receiver_email");
				String payerEmail = request.getParameter("payer_email");
				log.info("商品信息:"+itemName);
				log.info("paymentCurrency="+paymentCurrency);
				log.info("商品编码:"+itemNumber);
				log.info("支付状态："+paymentStatus);
				log.info("支付金额："+paymentAmount);
				log.info("txnId交易码："+txnId);
				log.info("receiverEmail收款人邮箱"+receiverEmail);
				log.info("payerEmail付款人邮箱"+payerEmail);
				//…
				//获取 PayPal 对回发信息的回复信息，判断刚才的通知是否为 PayPal 发ft的
				if(res.equals("VERIFIED")) {
					boolean flag = true;
					if (!paymentStatus.equalsIgnoreCase("Completed")) {
						flag = false;
					}
//					if (!paymentCurrency.equals(SysConf.BankPaypalCurrency_code) || !paymentAmount.equals(account.getMoney())) {
//						flag = false;
//					}
//					Cookie cookie = CookieUtil.getCookie(request, EChannel.account.name() + txnId + ViewKeyDict.totalpay);
//					if (cookie != null) {
//						flag = false;
//					} else {
//						CookieUtil.addCookieSess(response, EChannel.account.name() + txnId + ViewKeyDict.totalpay, txnId);
//					}
					if (flag) {
						log.info("success in receiveDataFromBankHandler, return true");
						return true;
					}
				//检查付款状态
				//检查 txn_id 是否已经处理过
				//检查 receiver_email 是否是您的 PayPal 账户中的 EMAIL 地址
				//检查付款金额和货币单位是否正确
				//处理其他数据，包括写数据库
				} else if(res.equals("INVALID")) {
					msgmap.put(ViewKeyDict.result, "非法信息");
				//非法信息，可以将此记录到您的日志文件中以备调查
				} else {
					msgmap.put(ViewKeyDict.result, "validate不正确");
				//处理其他错误
				}
		}
		log.info("failed in receiveDataFromBankHandler, return false");
		return false;
	}
	
	/**
	 * 充值成功后，回调站点提供的NotifyUr，判断是否请求到NotifyUr
	 * 返回true或false
	 * @param account
	 * @return
	 * @author liushaomin
	 */
	public static boolean getSiteNotifyUrl(Account account) {
		log.info("begin to invoke getSiteResultUrl");
		Map<String, String> map = new HashMap<String, String>();
//		map.put(ViewKeyDict.apiuid, SysConf.SudokuApiuid); //TODO 确定是否需要
		map.put(ViewKeyDict.payuid, account.getPayuid());
		map.put(ViewKeyDict.money, String.valueOf(account.getMoney()));
		map.put(ViewKeyDict.banktype, account.getBank().toString());
		map.put(ViewKeyDict.useruid, account.getUseruid());
		map.put(ViewKeyDict.pwdmd5, AccountHP.getPwdmd5(account));
		log.info(String.format("request notifyUrl url( url:%s)", account.getNotifyurl()));
		log.info(String.format("request notifyUrl map( map:%s)", JsonUtil.toJson(map)));
		String result = HttpUtil.get(account.getNotifyurl(), map);
		log.info(String.format("notifyUrl result:%s", result));
//		Map<String, Object> resultMap = JsonUtil.fromJson(result, Map.class);
		if (result != null && result.equals("0")) {
			log.info("request notifyUrl success");
			return true;
		} else if (StrUtil.isNotEmpty(result)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> resultMap = JsonUtil.fromJson(result, Map.class);
			if (StrUtil.isNotEmpty(resultMap.get(MapperDict.result)) && String.valueOf(resultMap.get(MapperDict.result)).equals("0")) {
				log.info("request notifyUrl success");
				return true;
			}
		} else {
			//TODO 如果请求失败，加入队列定时请求notifyurl
		}
		log.info("request notifyUrl failed");
		return false;
	}
	
	/**
	 * 给银行后台调用的服务端接口处理逻辑
	 * @param model
	 * @param eBank
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public static boolean backReceiveHandler(Map<String, Object> model, Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info(String.format("begin to execute backReceiveHandler(EBank:%s, accountsn:%s, money:%s)", account.getBankName(), account.getAccountsn(), account.getMoney()));
		if (receiveDataFromBankHandler(model, account, null, request, response)) {
			log.info("success, return true execute receiveDataFromBankHandler(...)");
			return true;//成功
		} else {
			log.info("failed, return false execute receiveDataFromBankHandler(...)");
		}
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		log.info("response.setStatus:500");
		return false;
	}

	/**
	 * 返回需要跳转的结果页地址
	 * @param account
	 * @param i
	 * @return
	 * @author Architect.bian
	 * @createtime 2016-4-29 下午5:19:47
	 */
	public static String getResultUrl(Account account, int i) {
		String resulturl = String.format(String.format(account.getResulturl()), i);
		resulturl += "?useruid=" + account.getUseruid() + "?payuid=" + account.getPayuid();
		return resulturl;
	}
	/**
	 * 获取请求参数中所有的信息
	 * 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
	 * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	
}
