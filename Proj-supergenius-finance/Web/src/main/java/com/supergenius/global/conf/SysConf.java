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
	public static final int[][] AvatarSizes = new int[][] { { 300, 300 }, { 180, 180 }, { 50, 50 } };
	public static final int[][] ImgShowSizes = new int[][] { { 720, 540 }, { 480, 360 }, { 160, 120 } };
	public static int AliveTime;
	public static int SummarySize;
	public static int FirstArticleSize;
	public static int FirstLoadSize;
	public static int HotArticleSize;
	public static int HotLabelSize;
	public static int RecommendArticleSize;
	public static int CarouselArticleSize;
	public static int GetCommentsSize;	
	public static int RelatedArticleSize;	
	public static int ClickLable;	
	public static int RelateArticle;	
	public static float ClickcountPercent;
	public static float PrizecountPercent;
	public static float CommentscountPercent;
	public static float CollectcountPercent;
	public static String FinanceCatalogueOrder;
	public static String SerialFinanceArticlePath;
	public static String HtmlNewData;
	public static String SerialUserFinanceArticlePath;
	public static int SerialUserFinanceArticleNum;
	public static String UserIndexTemplatePath;
	public static String SerialFinanceCataloguePath;
	public static int HisArticleSize;
	public static String SerialUserVisitorPath;
	public static String UserBasePath;
	public static String UserIgnoreMsgFilePath;
	public static String UploadImgPath;
	public static int MyCollectPageSize;
	public static int MyMsgPageSize;
	public static int PhotoUploadSize;
	public static int MyArticleSize;
	public static int OfficialIndexFinanceSize;
	public static String OfficialIndexTemplatePath;
	public static String HtmlRecentData;
	public static String HtmlHotData;
	public static String MobileHtmlRecentData;
	public static String FileRecentPath;
	public static String MobileFileRecentPath;
	public static String FileHotPath;
	public static String wxcorpid;
	public static String wxsecrect;
	public static String getTokenURI;
	public static String getTicketURI;
	public static String SerialFinanceDataPath;

	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.SummarySize = Integer.valueOf(sys.getProperty("SummarySize"));
		SysConf.AliveTime = Integer.valueOf(sys.getProperty("AliveTime"));
		SysConf.FirstArticleSize = Integer.valueOf(sys.getProperty("FirstArticleSize"));
		SysConf.FirstLoadSize = Integer.valueOf(sys.getProperty("FirstLoadSize"));
		SysConf.HotArticleSize = Integer.valueOf(sys.getProperty("HotArticleSize"));
		SysConf.HotLabelSize = Integer.valueOf(sys.getProperty("HotLabelSize"));
		SysConf.RecommendArticleSize = Integer.valueOf(sys.getProperty("RecommendArticleSize"));
		SysConf.GetCommentsSize = Integer.valueOf(sys.getProperty("GetCommentsSize"));
		SysConf.RelatedArticleSize = Integer.valueOf(sys.getProperty("RelatedArticleSize"));
		SysConf.ClickLable = Integer.valueOf(sys.getProperty("ClickLable"));
		SysConf.RelateArticle = Integer.valueOf(sys.getProperty("RelateArticle"));
		SysConf.ClickcountPercent = Float.valueOf(sys.getProperty("ClickcountPercent"));
		SysConf.PrizecountPercent = Float.valueOf(sys.getProperty("PrizecountPercent"));
		SysConf.CommentscountPercent = Float.valueOf(sys.getProperty("CommentscountPercent"));
		SysConf.CollectcountPercent = Float.valueOf(sys.getProperty("CollectcountPercent"));
		SysConf.SerialFinanceCataloguePath = sys.getProperty("SerialFinanceCataloguePath");
		SysConf.SerialFinanceArticlePath = sys.getProperty("SerialFinanceArticlePath");
		SysConf.HtmlNewData = sys.getProperty("HtmlNewData");
		SysConf.SerialUserFinanceArticlePath = sys.getProperty("SerialUserFinanceArticlePath");
		SysConf.SerialUserFinanceArticleNum = Integer.valueOf(sys.getProperty("SerialUserFinanceArticleNum"));
		SysConf.UserIndexTemplatePath = sys.getProperty("UserIndexTemplatePath");
		SysConf.FinanceCatalogueOrder = sys.getProperty("FinanceCatalogueOrder");
		SysConf.HisArticleSize = Integer.valueOf(sys.getProperty("HisArticleSize"));
		SysConf.SerialUserVisitorPath = sys.getProperty("SerialUserVisitorPath");
		SysConf.UserBasePath = sys.getProperty("UserBasePath");
		SysConf.UserIgnoreMsgFilePath = sys.getProperty("UserIgnoreMsgFilePath");
		SysConf.UploadImgPath = sys.getProperty("UploadImgPath");
		SysConf.CarouselArticleSize = Integer.valueOf(sys.getProperty("CarouselArticleSize"));
		SysConf.MyCollectPageSize = Integer.valueOf(sys.getProperty("MyCollectPageSize"));
		SysConf.MyMsgPageSize = Integer.valueOf(sys.getProperty("MyMsgPageSize"));
		SysConf.PhotoUploadSize = Integer.valueOf(sys.getProperty("PhotoUploadSize"));
		SysConf.MyArticleSize = Integer.valueOf(sys.getProperty("MyArticleSize"));
		SysConf.OfficialIndexFinanceSize = Integer.valueOf(sys.getProperty("OfficialIndexFinanceSize"));
		SysConf.OfficialIndexTemplatePath = sys.getProperty("OfficialIndexTemplatePath");
		SysConf.HtmlRecentData = sys.getProperty("HtmlRecentData");
		SysConf.HtmlHotData = sys.getProperty("HtmlHotData");
		SysConf.MobileHtmlRecentData = sys.getProperty("MobileHtmlRecentData");
		SysConf.FileRecentPath = sys.getProperty("FileRecentPath");
		SysConf.MobileFileRecentPath = sys.getProperty("MobileFileRecentPath");
		SysConf.FileHotPath = sys.getProperty("FileHotPath");
		SysConf.wxsecrect = sys.getProperty("wxsecrect");
		SysConf.wxcorpid = sys.getProperty("wxcorpid");
		SysConf.getTokenURI = sys.getProperty("getTokenURI");
		SysConf.getTicketURI = sys.getProperty("getTicketURI");
		SysConf.SerialFinanceDataPath = sys.getProperty("SerialFinanceDataPath");
	}
	
	//获取用户忽略消息文件路径封装
	public static String getUserIgnoreFilePath() {
		return SysConf.UserBasePath + SysConf.UserIgnoreMsgFilePath;
	}
}
