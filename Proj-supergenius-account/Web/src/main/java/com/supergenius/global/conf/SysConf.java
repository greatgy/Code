package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bocom.netpay.b2cAPI.BOCOMB2CClient;
import com.genius.core.base.conf.BaseSysConf;

/**
 * @author Architect.bian
 * 
 */
@Component
public class SysConf extends BaseSysConf {

	public static final int[][] ImgShowSizes = new int[][] { { 720, 540 }, { 480, 360 }, { 160, 120 } };

	public static String WebSiteUri;
	public static String SerialSeoPath;
	public static String BaseSeoPath;
	public static String ImgcontributePath;
	public static String dot = ".";
	public static String SerialCommentPath;
	public static String SerialCommentsExt;
	public static String SerialPraisePath;
	public static String SiteSudokuApiuid;
	public static String SiteSuperGeniusApiuid;
	public static String SiteUserApiuid;
	public static String SiteMoralApiuid;
	public static String SiteManagerApiuid;
	public static String SiteSnsApiuid;
	public static String SiteEnterpriserApiuid;
	public static String SiteTpiApiuid;
	public static String DemoPayApiuid;
	public static String BankRechargeResultUrl;
	public static String BankICBCConfigFile;
	public static String BankCommConfigFile;
	public static String BankUnionpayBaseUrl;
	public static String BankUnionpayBSpayBaseUrl;
	public static String BankUnionpayQueryBaseUrl;
	public static String BankUnionpayMerCode;
	public static String BankMerName;
	public static String BankMerFrontEndUrl;
	public static String BankMerBackEndUrl;
	public static String BankUnionpaySecurityKey;
	public static String BankCmbBranchId;
	public static String BankCmbCono;
	public static String BankABCAccountDBNo;
	public static String Corporation;
	public static String BankBOCPostUrl;
	public static String BankBOCMerchantno;
	public static String BankBOCPayType;
	public static String BankBOCCurcode;
	public static String BankBocBasePath;
	public static String BankBocPfxFilePath;
	public static String BankBocPasswd;
	public static String BankBocCerFilePath;
	public static String BankCmbPulicKeyFile;
	public static String BankPaypalCmd;
	public static String BankPaypalBn;
	public static String BankPaypalCurrency_code;
	public static String BankPaypalPostUrl;
	public static String[] ManagerEmailsSuccess;
	public static String[] ManagerEmailsFailed;
	public static String EmailTemplatePath = "";
	public static String EmailPaypalEmailAddr;

	public static String AlipayPartner; //合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串
	public static String AlipayKey; //MD5密钥，安全检验码
	public static String AlipayLogPath; // 调试用，创建TXT日志文件夹路径
	public static String AlipayAppid; // 
	public static String AlipayPrivateKey; //应用私钥
	public static String AlipayPublicKey; // 支付宝公钥

	public static String BankWXPayAppId; // 微信支付分配的公众账号ID（企业号corpid即为此appId）
	public static String BankWXPayMchId; // 微信支付分配的商户号
	public static String BankWXPayBillCreateIp; // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	public static String BankWXPayApiKey; // API key
	public static String BankWXPayBasePath; // 微信证书存储基本路径
	public static String BankWXPayp12FilePath; // 微信p12证书路径
	
	public static String BankPayeezyPostUrl;
	public static String BankPayeezylogin;
	public static String BankPayeezyTransactionKey;
	public static String BankPayeezyRechargeResultUrl;
	
	public static String emailrefund = "emailrefund";
	
	
	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.WebSiteUri = sys.getProperty("WebSiteUri");
		SysConf.SerialSeoPath = sys.getProperty("SerialSeoPath");
		SysConf.BaseSeoPath = sys.getProperty("BaseSeoPath");
		SysConf.ImgcontributePath = sys.getProperty("ImgcontributePath");
		SysConf.SerialCommentPath = sys.getProperty("SerialCommentPath");
		SysConf.SerialCommentsExt = sys.getProperty("SerialCommentsExt");
		SysConf.SerialPraisePath = sys.getProperty("SerialPraisePath");
		SysConf.SiteSudokuApiuid = sys.getProperty("SiteSudokuApiuid");
		SysConf.SiteSuperGeniusApiuid = sys.getProperty("SiteSuperGeniusApiuid");
		SysConf.SiteEnterpriserApiuid = sys.getProperty("SiteEnterpriserApiuid");
		SysConf.SiteUserApiuid = sys.getProperty("SiteUserApiuid");
		SysConf.SiteMoralApiuid = sys.getProperty("SiteMoralApiuid");
		SysConf.SiteManagerApiuid = sys.getProperty("SiteManagerApiuid");
		SysConf.SiteSnsApiuid = sys.getProperty("SiteSnsApiuid");
		SysConf.SiteTpiApiuid = sys.getProperty("SiteTpiApiuid");
		SysConf.DemoPayApiuid = sys.getProperty("DemoPayApiuid");
		SysConf.BankRechargeResultUrl = sys.getProperty("BankRechargeResultUrl");
		SysConf.BankICBCConfigFile = sys.getProperty("BankICBCConfigFile");
		SysConf.BankCommConfigFile = sys.getProperty("BankCommConfigFile");
		new BOCOMB2CClient().initialize(SysConf.BankCommConfigFile);//初始化交行配置文件
		SysConf.BankUnionpayBaseUrl = sys.getProperty("BankUnionpayBaseUrl");
		SysConf.BankUnionpayBSpayBaseUrl = sys.getProperty("BankUnionpayBSpayBaseUrl");
		SysConf.BankUnionpayQueryBaseUrl = sys.getProperty("BankUnionpayQueryBaseUrl");
		SysConf.BankUnionpayMerCode = sys.getProperty("BankUnionpayMerCode");
		SysConf.BankMerName = sys.getProperty("BankMerName");
		SysConf.BankMerFrontEndUrl = sys.getProperty("BankMerFrontEndUrl");
		SysConf.BankMerBackEndUrl = sys.getProperty("BankMerBackEndUrl");
		SysConf.BankUnionpaySecurityKey = sys.getProperty("BankUnionpaySecurityKey");
		SysConf.BankCmbBranchId = sys.getProperty("BankCmbBranchId");
		SysConf.BankCmbCono = sys.getProperty("BankCmbCono");
		SysConf.BankABCAccountDBNo = sys.getProperty("BankABCAccountDBNo");
		SysConf.Corporation = sys.getProperty("Corporation");
		SysConf.BankBOCPostUrl = sys.getProperty("BankBOCPostUrl");
		SysConf.BankBOCMerchantno = sys.getProperty("BankBOCMerchantno");
		SysConf.BankBOCPayType = sys.getProperty("BankBOCPayType");
		SysConf.BankBOCCurcode = sys.getProperty("BankBOCCurcode");
		SysConf.BankBocBasePath = sys.getProperty("BankBocBasePath");
		SysConf.BankBocPfxFilePath = sys.getProperty("BankBocPfxFilePath");
		SysConf.BankBocPasswd = sys.getProperty("BankBocPasswd");
		SysConf.BankBocCerFilePath = sys.getProperty("BankBocCerFilePath");
		SysConf.BankPaypalCmd = sys.getProperty("BankPaypalCmd");
		SysConf.BankPaypalBn = sys.getProperty("BankPaypalBn");
		SysConf.BankPaypalCurrency_code = sys.getProperty("BankPaypalCurrency_code");
		SysConf.BankPaypalPostUrl = sys.getProperty("BankPaypalPostUrl");
		SysConf.BankCmbPulicKeyFile = sys.getProperty("BankCmbPulicKeyFile");
		SysConf.ManagerEmailsSuccess = sys.getProperty("ManagerEmailsSuccess").split(",");
		SysConf.ManagerEmailsFailed = sys.getProperty("ManagerEmailsFailed").split(",");
		SysConf.EmailTemplatePath = sys.getProperty("EmailTemplatePath");
		SysConf.AlipayPartner = sys.getProperty("AlipayPartner");
		SysConf.AlipayKey = sys.getProperty("AlipayKey");
		SysConf.AlipayLogPath = sys.getProperty("AlipayLogPath");
		SysConf.AlipayPublicKey = sys.getProperty("AlipayPublicKey");
		SysConf.AlipayPrivateKey = sys.getProperty("AlipayPrivateKey");
		SysConf.AlipayAppid = sys.getProperty("AlipayAppid");
		SysConf.BankWXPayAppId = sys.getProperty("BankWXPayAppId");
		SysConf.BankWXPayMchId = sys.getProperty("BankWXPayMchId");
		SysConf.BankWXPayBillCreateIp = sys.getProperty("BankWXPayBillCreateIp");
		SysConf.BankWXPayApiKey = sys.getProperty("BankWXPayApiKey");
		SysConf.EmailPaypalEmailAddr = sys.getProperty("EmailPaypalEmailAddr");
		SysConf.BankPayeezyPostUrl = sys.getProperty("BankPayeezyPostUrl");
		SysConf.BankPayeezylogin = sys.getProperty("BankPayeezylogin");
		SysConf.BankPayeezyTransactionKey = sys.getProperty("BankPayeezyTransactionKey");
		SysConf.BankPayeezyRechargeResultUrl = sys.getProperty("BankPayeezyRechargeResultUrl");
		SysConf.BankWXPayBasePath = sys.getProperty("BankWXPayBasePath");
		SysConf.BankWXPayp12FilePath = sys.getProperty("BankWXPayp12FilePath");
	}
}