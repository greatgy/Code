package com.supergenius.global.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.genius.core.base.conf.BaseSysConf;

/**
 * @author Architect.bian
 * 
 */
@Component
public class SysConf extends BaseSysConf {

	public static final int[][] ImgShowSizes = new int[][] { { 930, 600 }, { 310, 200 }, { 155, 100 } };
	public static final int[][] AvatarSizes = new int[][] { { 300, 300 }, { 180, 180 }, { 50, 50 } };
	
	public static int AliveTime;
	public static int smsAliveTime;
	public static int DefaultAnonymousOid = 0;
	public static int ResetPwdDays;
	public static int MyMsgPageSize;
	public static String DefaultAnonymousUid = "00000000000000000000000000000000";
	public static String EmailRebindingUrlFormat;
	public static String EmailPwdResetUrlFormat;//重置密码邮件
	public static String EmailFinaPayPwdUrlFormat;//找回支付密码邮件
	public static String UserBasePath;
	public static String UserPayApiuid;
	public static String RegisterPayMonney;
	public static String AccountQueryUrl;
	public static String IndexMaskOrderPath;
	public static String MaskOrder;
	public static String aliyunReTempId;
	public static String FileAiRecentPath;
	public static String FileLifeRecentPath;
	public static String FileFinanceRecentPath;
	public static String FileStartupRecentPath;
	public static String FileEntrepreneurRecentPath;
	public static String FileManagernewsRecentPath;
	public static String QRcodeurl;
	public static String wxappid;
	public static String wxredirect_uri;
	public static String wxscope;
	
	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.EmailRebindingUrlFormat = sys.getProperty("EmailRebindingUrlFormat");
		SysConf.UserBasePath = sys.getProperty("UserBasePath");
		SysConf.EmailPwdResetUrlFormat = sys.getProperty("EmailPwdResetUrlFormat");
		SysConf.EmailFinaPayPwdUrlFormat = sys.getProperty("EmailFinaPayPwdUrlFormat");
		SysConf.UserPayApiuid = sys.getProperty("UserPayApiuid");
		SysConf.RegisterPayMonney = sys.getProperty("RegisterPayMonney");
		SysConf.AccountQueryUrl = sys.getProperty("AccountQueryUrl");
		SysConf.IndexMaskOrderPath = sys.getProperty("IndexMaskOrderPath");
		SysConf.MaskOrder = sys.getProperty("MaskOrder");
		SysConf.aliyunReTempId = sys.getProperty("AliyunReTempId");
		SysConf.ResetPwdDays = Integer.parseInt(sys.getProperty("ResetPwdDays"));
		SysConf.AliveTime = Integer.parseInt(sys.getProperty("AliveTime"));
		SysConf.MyMsgPageSize = Integer.parseInt(sys.getProperty("MyMsgPageSize"));
		SysConf.smsAliveTime = Integer.parseInt(sys.getProperty("SmsAliveTime"));
		SysConf.FileAiRecentPath = sys.getProperty("FileAiRecentPath");
		SysConf.FileLifeRecentPath = sys.getProperty("FileLifeRecentPath");
		SysConf.FileFinanceRecentPath = sys.getProperty("FileFinanceRecentPath");
		SysConf.FileStartupRecentPath = sys.getProperty("FileStartupRecentPath");
		SysConf.FileEntrepreneurRecentPath = sys.getProperty("FileEntrepreneurRecentPath");
		SysConf.FileManagernewsRecentPath = sys.getProperty("FileManagernewsRecentPath");
		SysConf.QRcodeurl = sys.getProperty("QRcodeurl");
		SysConf.wxappid = sys.getProperty("wxappid");
		SysConf.wxredirect_uri = sys.getProperty("wxredirect_uri");
		SysConf.wxscope = sys.getProperty("wxscope");
	}

}
