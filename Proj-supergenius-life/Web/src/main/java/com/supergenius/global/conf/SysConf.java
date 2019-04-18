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
	public static int ismember = 1;
	public static int notmember = 0;
	
	public static int SummarySize;
	public static float ClickcountPercent;
	public static float PrizecountPercent;
	public static float CommentscountPercent;
	public static float CollectcountPercent;
	public static int FirstArticleSize;
	public static int FirstVideoSize;
	public static int FirstProblemSize;
	public static int FirstTopicSize;
	public static int LoadSize;
	public static int HotArticleSize;
	public static int MyCommentSize;
	public static int RelateSize;
	public static int TopicLoadSize;
	public static int GetCommentsSize;
	public static int MyArticleSize;
	public static int MyMsgPageSize;
	public static int MyCollectPageSize;
	public static int MyVideoSize;
	public static int MyProblemSize;
	public static int AliveTime;
	public static String SerialBasePath;
	public static long CourseCid;
	public static int DesignProblemSize;
	public static int CourseProblemSize;
	public static int VideoSize;
	public static int RelateVieoSize;
	public static int LoadEssaySize;
	public static int EssaySize;
	public static int ReviewTopicSize;
	public static int ReviewFirstSize;
	public static String SerialUserVisitorPath;
	public static String SerialArticlesPath;
	public static String SerialTopicsPath;
	public static String SerialCommentsPath;
	public static String SerialEssayPath;
	public static String SerialLifeCataloguePath;
	public static String SerialTravelPath;
	public static String UploadImgPath;
	public static String SerialAnswerPath;
	public static String UserIndexTemplatePath;
	public static int SerialUserLifeArticleNum;
	public static String LifeMusicPath;
	public static String OfficialIndexTemplatePath;
	public static String HtmlOfficialRecentData;
	public static String SerialOfficialLifeArticlePath;
	public static int bookGetSize;
	public static int bookSize;
	public static int SerialOfficialArticleNum;
	public static String SerialUserLifeArticlePath;
	public static String HtmlNewData;
	public static String SerialLifeDataPath;
	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.AliveTime = Integer.valueOf(sys.getProperty("AliveTime"));
		SysConf.SummarySize = Integer.valueOf(sys.getProperty("SummarySize"));
		SysConf.ClickcountPercent = Float.valueOf(sys.getProperty("ClickcountPercent"));
		SysConf.PrizecountPercent = Float.valueOf(sys.getProperty("PrizecountPercent"));
		SysConf.CommentscountPercent = Float.valueOf(sys.getProperty("CommentscountPercent"));
		SysConf.CollectcountPercent = Float.valueOf(sys.getProperty("CollectcountPercent"));
		SysConf.FirstArticleSize = Integer.valueOf(sys.getProperty("FirstArticleSize"));
		SysConf.bookGetSize = Integer.valueOf(sys.getProperty("bookGetSize"));
		SysConf.bookSize = Integer.valueOf(sys.getProperty("bookSize"));
		SysConf.FirstVideoSize = Integer.valueOf(sys.getProperty("FirstVideoSize"));
		SysConf.FirstProblemSize = Integer.valueOf(sys.getProperty("FirstProblemSize"));
		SysConf.FirstTopicSize = Integer.valueOf(sys.getProperty("FirstTopicSize"));
		SysConf.MyCommentSize = Integer.valueOf(sys.getProperty("MyCommentSize"));
		SysConf.LoadSize = Integer.valueOf(sys.getProperty("LoadSize"));
		SysConf.HtmlNewData = sys.getProperty("HtmlNewData");
		SysConf.SerialUserLifeArticleNum = Integer.valueOf(sys.getProperty("SerialUserLifeArticleNum"));
		SysConf.HotArticleSize = Integer.valueOf(sys.getProperty("HotArticleSize"));
		SysConf.UserIndexTemplatePath = sys.getProperty("UserIndexTemplatePath");
		SysConf.GetCommentsSize = Integer.valueOf(sys.getProperty("GetCommentsSize"));
		SysConf.RelateSize = Integer.valueOf(sys.getProperty("RelateSize"));
		SysConf.SerialUserLifeArticlePath = sys.getProperty("SerialUserLifeArticlePath");
		SysConf.TopicLoadSize = Integer.valueOf(sys.getProperty("TopicLoadSize"));
		SysConf.MyArticleSize = Integer.valueOf(sys.getProperty("MyArticleSize"));
		SysConf.MyMsgPageSize = Integer.valueOf(sys.getProperty("MyMsgPageSize"));
		SysConf.MyCollectPageSize = Integer.valueOf(sys.getProperty("MyCollectPageSize"));
		SysConf.MyVideoSize = Integer.valueOf(sys.getProperty("MyVideoSize"));
		SysConf.MyProblemSize = Integer.valueOf(sys.getProperty("MyProblemSize"));
		SysConf.SerialBasePath = sys.getProperty("SerialBasePath");
		SysConf.CourseCid = Long.parseLong(sys.getProperty("CourseCid"));
		SysConf.DesignProblemSize = Integer.valueOf(sys.getProperty("DesignProblemSize"));
		SysConf.CourseProblemSize = Integer.valueOf(sys.getProperty("CourseProblemSize"));
		SysConf.VideoSize = Integer.valueOf(sys.getProperty("VideoSize"));
		SysConf.RelateVieoSize = Integer.valueOf(sys.getProperty("RelateVieoSize"));
		SysConf.LoadEssaySize = Integer.valueOf(sys.getProperty("LoadEssaySize"));
		SysConf.EssaySize = Integer.valueOf(sys.getProperty("EssaySize"));
		SysConf.ReviewTopicSize = Integer.valueOf(sys.getProperty("ReviewTopicSize"));
		SysConf.ReviewFirstSize = Integer.valueOf(sys.getProperty("ReviewFirstSize"));
		SysConf.SerialOfficialArticleNum = Integer.valueOf(sys.getProperty("SerialOfficialArticleNum"));
		SysConf.SerialUserVisitorPath = sys.getProperty("SerialUserVisitorPath");
		SysConf.SerialArticlesPath = sys.getProperty("SerialArticlesPath");
		SysConf.SerialTopicsPath = sys.getProperty("SerialTopicsPath");
		SysConf.SerialCommentsPath = sys.getProperty("SerialCommentsPath");
		SysConf.SerialLifeCataloguePath = sys.getProperty("SerialLifeCataloguePath");
		SysConf.SerialTravelPath = sys.getProperty("SerialTravelPath");
		SysConf.UploadImgPath = sys.getProperty("UploadImgPath");
		SysConf.SerialAnswerPath = sys.getProperty("SerialAnswerPath");
		SysConf.SerialEssayPath = sys.getProperty("SerialEssayPath");
		SysConf.LifeMusicPath = sys.getProperty("LifeMusicPath");
		SysConf.OfficialIndexTemplatePath = sys.getProperty("OfficialIndexTemplatePath");
		SysConf.HtmlOfficialRecentData = sys.getProperty("HtmlOfficialRecentData");
		SysConf.SerialOfficialLifeArticlePath = sys.getProperty("SerialOfficialLifeArticlePath");
		SysConf.SerialLifeDataPath = sys.getProperty("SerialLifeDataPath");
	}
	public static int DefaultAnonymousOid = 0;
	
}