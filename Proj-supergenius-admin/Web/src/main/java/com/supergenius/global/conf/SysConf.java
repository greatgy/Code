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

	public static final int[][] TitleImgShowSizes = new int[][] { { 900, 300 }, { 300, 200 }, { 150, 100 } };
	public static final int[][] ImgShowSizes = new int[][] { { 720, 540 }, { 480, 360 }, { 160, 120 } };
	public static final int[][] AvatarSizes = new int[][] { { 300, 300 }, { 180, 180 }, { 50, 50 } };
	public static final int [] Adcatalogue = {4, 5, 31, 33, 35, 61, 62, 63 };
	
	public static String AllarticlePhotoPath;
	public static String BaseSeoPath;
	public static String ImgFinancePath;
	public static String dot = ".";
	public static String SerialCommentsExt;
	public static String SerialCommentPath;
	public static String TpiProjectReportPath;
	public static String DefaultSupenGeniusUid = "00000000000000000000000000000000";
	public static String LabelClickCount ="LabelClickCount";
	public static String SerialAuditAdminPath;
	public static String EmailTemplateDirectoryPath;
	public static String TpiSysMsgImg;
	public static String OfficialSysMsgImg;
	public static String SerialOfficialPath;
	public static String OfficialIndexTemplatePath;
	public static String HtmlFinanceData;
	public static String MobileHtmlFinanceData;
	public static String HtmlTopicData;
	public static String HtmlDebateData;
	public static String HtmlProjectData;
	public static String HtmlArticleData;
	public static String FileFinancePath;
	public static String MobileFileFinancePath;
	public static String FileTopicPath;
	public static String FileDebatePath;
	public static String FileProjectPath;
	public static String MobileFileProjectPath;
	public static String FileArticlePath;
	public static String MobileHtmlProjectData;
	public static String SerialFiancePath;
	public static String QuotesPath;
	public static String Quotes;
	public static String VideoPlayerName;
	public static String OfficialPhotoPath;
	//职业道德培训
	public static String MoralDocPath;
	public static String MoralCasePath;
	public static String MoralSysMsgImg;
	public static String MoralMsgTemplPath;
	public static String MoralMsgTempName;
	//account
	public static String BaseDataPath;
	public static String ExcelPath;
	public static String ExcelName;
	
	public static String UserBasePath;
	public static String UserIgnoreMsgFilePath;
	public static String TpiUserIgnoreMsgFilePath;
	public static String MyArticlePath;
	public static String SerialUserVisitorPath;
	public static String VisitorAvatarPath;
	public static String DefaultVisitorAvatar;
	//manager
	public static String ManagerMsgTmplPath;
	public static String AppChallengingName;
	public static String ChallengingName;
	public static String ChallengeOverName;
	public static String ChallengeCancelName;
	public static String PKUserWin;
	public static String PKUser2Win;
	public static String PKDeuce;
	public static String PKNoResult;
	public static float PercentJudgePkResult;
	public static String password;
	public static String Max_conf_user;
	public static String Max_conf_tourist;
	public static String Max_conf_spokesman;
	public static int StartBeforeInto;
	public static int DefaultConferenceTime;
	public static String PKDetailUrl;
	public static int PKDownLevelNumber;
	public static int PKJudgementRewardBasic;
	public static int PKJudgementRewardManager;
	public static int PKWinRewardMoney;
	public static String JudgeInterviewNotPassTitle;
	public static String JudgeInterviewPassTitle;
	public static String JudgeAppPassTitle;
	public static String JudgeAppNotPassTitle;
	public static String MyJudgePath;
	public static String MyExpertPath;
	public static int ApplyJudgeQuestionCount;
	public static String JudgeCheckNotPassTitle;
	public static String JudgeCheckPassTitle;
	public static String AppJudgeFilePath;
	public static String AppExpertFilePath;
	public static String ChangeExpertLevelTitle;
	public static String ExpertAppPassTitle;
	public static String ExpertAppNotPassTitle;
	public static String ExpertInterviewNotPassTitle;
	public static String ExpertInterviewPassTitle;
	public static String ExpertCheckPassTitle;
	public static String ExpertCheckNotPassTitle;
	public static String VideoDetailHref;
	public static String SerialManagerCommentPath;
	public static int FullJudgeCount;
	public static int FullExpertCount;
	public static String ComplaintFailedTitle;
	public static String CancelJudgementQualificationTitle;
	public static String AddOneComplaintToJudgeTitle;
	public static String ComplaintJudgeUrl;
	public static String ComplaintExpertUrl;
	public static String MyHome;
	public static String ComplaintExpertSuccess;
	public static String CertificateName;
	public static String AwardCertificateTitle;
	public static double TMBAReplyFee;
	public static double TMBASecondReplyFee;
	public static double SMBAReplyFee;
	public static double SMBASecondReplyFee;
	public static double RMBAReplyFee;
	public static double RMBASecondReplyFee;
	public static String CheckAppOpenreplyNoPassTitle;
	public static String CheckAppOpenreplyPassTitle;
	public static String AppReplyFilePath;
	public static String PKFilePath;
	public static String CheckAppOpenreplyFileNoPassTitle;
	public static String CheckAppOpenreplyFilePassTitle;
	public static String CheckAppreplyNoPassTitle;
	public static String CheckAppreplyPassTitle;
	public static String MyReplyUrl;
	public static String CheckReportNoPassTitle;
	public static String CheckReportPassTitle;
	public static String OpenReplyStartTitle;
	public static String OpenReplyEndTitle;
	public static String OpenReplyNoPasstTitle;
	public static String OpenReplyPasstTitle;
	public static String CheckAppPrereplyFileNoPassTitle;
	public static String CheckAppPrereplyFilePassTitle;
	public static String CheckAppreplyFileNoPassTitle;
	public static String CheckAppreplyFilePassTitle;
	public static String ReplyStartTitle;
	public static String ReplyEndTitle;
	public static String ReplyNoPassTitle;
	public static String ReplyPassTitle;
	public static String SecondReplyNoPassTitle;
	public static String SecondReplyPassTitle;
	public static String AppReplyConfHref;
	public static String PkConfHref;
	public static String CreatReplyQQGroupMetting;
	public static String LaunchPKPath;
	public static String MajorPath;
	public static int JudgeComplaintCountGetPunish;
	public static int FromBasicToManagerUseScore;
	public static int FromaManagerToMajordomoUseScore;
	public static double UseForwardScoreLevelUpOfFactor;
	public static double UseOriginalScoreLevelUpOfFactor;
	public static String ChallengMettingOver;
	public static String WaitingForChalleng;
	public static String MyCenterExpertUrl;
	public static String MyTradeDetailUrl;
	
	//enterpriser
	public static String ChangerSemester;
	public static String LectuerFilePath;
	
	//天才创业
	public static String UnSureRulerUid = "00000000000000000000000000000000";
	public static String StartupPhotoPath;
	public static String StartupMusicPath;
	public static String SerialArticlePath;
	public static String SerialCataloguePath;
	public static String startupCatalogueOrder;
	
	//天才AI
	public static String AiPhotoPath;
	public static String SerialAiArticlePath;
	public static String SerialAiCataloguePath;
	public static String AiCatalogueOrder;
	public static String HomeCid;
	public static int flashCid;
	public static long RelateArticle;
	
	//搜索推荐管理
	public static String SolrPhotoPath;
	
	//天才职场
	public static String SerialCareerCataloguePath;
	public static String CareerCatalogueOrder;
	public static int MemberCid;
	public static String CareerPhotoPath;
	public static String CareerMusicPath;
	
	//天财评论
	public static String SerialFinanceCataloguePath;
	public static String FinanceCatalogueOrder;
	public static String FinancePhotoPath;
	public static String FileRecentPath;
	
	//顾雏军专栏
	public static String GupagePhotoPath;
	public static String SerialGupageCataloguePath;	
	public static String GupageCatalogueOrder;	
	public static String GupagePagerPath;
	
	//引资购商
	public static String EnterpriserPhotoPath;
	public static String SerialEnterpriserCataloguePath;
	public static String EnterpriserArticlePath;
	public static String EnterpriserCatalogueOrder;
	
	//天才人生
	public static String LifePhotoPath;
	public static String LifeCatalogueOrder;
	public static String SerialLifeCataloguePath;
	public static String SerialCommentsPath;
	public static String SerialTopicsPath;
	public static String SerialTravelPath;
	public static String SerialEssayPath;
	public static String SerialArticlesPath;
	
	//职业经理人培训文章
	public static String SerialManagerNewsCataloguePath;
	public static String ManagerNewsCatalogueOrder;
	public static String ManagernewsPhotoPath;
	public static int IndexCid;
	//企业家培训文章
	public static String EntrepreneurArticlesPath;
	public static String EntrepreneurPhotoPath;
	public static String EntrepreneurCataloguePath;
	//职业道德培训
	public static String MoralNewsPhotoPath;
	public static String MoralNewsCataloguePath;
	public static String SerialMoralNewsCataloguePath;

	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		SysConf.BaseSeoPath = sys.getProperty("BaseSeoPath");
		SysConf.ImgFinancePath = sys.getProperty("ImgFinancePath");
		SysConf.SerialCommentsExt = sys.getProperty("SerialCommentsExt");
		SysConf.SerialCommentPath = sys.getProperty("SerialCommentPath");
		SysConf.TpiProjectReportPath = sys.getProperty("TpiProjectReportPath");
		SysConf.SerialAuditAdminPath = sys.getProperty("SerialAuditAdminPath");
		SysConf.EmailTemplateDirectoryPath = sys.getProperty("EmailTemplateDirectoryPath");
		SysConf.TpiSysMsgImg = sys.getProperty("TpiSysMsgImg");
		SysConf.OfficialSysMsgImg = sys.getProperty("OfficialSysMsgImg");
		SysConf.SerialOfficialPath = sys.getProperty("SerialOfficialPath");
		SysConf.OfficialIndexTemplatePath = sys.getProperty("OfficialIndexTemplatePath");
		SysConf.HtmlFinanceData = sys.getProperty("HtmlFinanceData");
		SysConf.HtmlTopicData = sys.getProperty("HtmlTopicData");
		SysConf.HtmlDebateData = sys.getProperty("HtmlDebateData");
		SysConf.HtmlProjectData = sys.getProperty("HtmlProjectData");
		SysConf.HtmlArticleData = sys.getProperty("HtmlArticleData");
		SysConf.FileFinancePath = sys.getProperty("FileFinancePath");
		SysConf.FileTopicPath = sys.getProperty("FileTopicPath");
		SysConf.FileDebatePath = sys.getProperty("FileDebatePath");
		SysConf.MobileFileFinancePath = sys.getProperty("MobileFileFinancePath");
		SysConf.FileProjectPath = sys.getProperty("FileProjectPath");
		SysConf.MobileFileProjectPath = sys.getProperty("MobileFileProjectPath");
		SysConf.FileArticlePath = sys.getProperty("FileArticlePath");
		SysConf.MobileHtmlFinanceData = sys.getProperty("MobileHtmlFinanceData");
		SysConf.MobileHtmlProjectData = sys.getProperty("MobileHtmlProjectData");
		SysConf.SerialFiancePath = sys.getProperty("SerialFiancePath");
		SysConf.MoralDocPath = sys.getProperty("MoralDocPath");
		SysConf.MoralCasePath = sys.getProperty("MoralCasePath");
		SysConf.BaseDataPath = sys.getProperty("BaseDataPath");
		SysConf.ExcelPath = sys.getProperty("ExcelPath");
		SysConf.ExcelName = sys.getProperty("ExcelName");
		SysConf.MoralSysMsgImg = sys.getProperty("MoralSysMsgImg");
		SysConf.MoralMsgTemplPath = sys.getProperty("MoralMsgTemplPath");
		SysConf.MoralMsgTempName = sys.getProperty("MoralMsgTempName");
		SysConf.QuotesPath = sys.getProperty("QuotesPath");
		SysConf.Quotes = sys.getProperty("Quotes");
		SysConf.VideoPlayerName = sys.getProperty("VideoPlayerName");
		SysConf.UserIgnoreMsgFilePath = sys.getProperty("UserIgnoreMsgFilePath");
		SysConf.TpiUserIgnoreMsgFilePath = sys.getProperty("TpiUserIgnoreMsgFilePath");
		SysConf.UserBasePath = sys.getProperty("UserBasePath");
		SysConf.MyArticlePath = sys.getProperty("MyArticlePath");
		SysConf.ManagerMsgTmplPath = sys.getProperty("ManagerMsgTmplPath");
		SysConf.AppChallengingName = sys.getProperty("AppChallengingName");
		SysConf.ChallengingName = sys.getProperty("ChallengingName");
		SysConf.ChallengeOverName = sys.getProperty("ChallengeOverName");
		SysConf.ChallengeCancelName = sys.getProperty("ChallengeCancelName");
		SysConf.PKUserWin = sys.getProperty("PKUserWin");
		SysConf.PKUser2Win = sys.getProperty("PKUser2Win");
		SysConf.PKDeuce = sys.getProperty("PKDeuce");
		SysConf.PKNoResult = sys.getProperty("PKNoResult");
		SysConf.PercentJudgePkResult = Float.parseFloat(sys.getProperty("PercentJudgePkResult"));
		SysConf.ChangerSemester = sys.getProperty("ChangerSemester");
		SysConf.LectuerFilePath = sys.getProperty("LectuerFilePath");
		SysConf.password = sys.getProperty("password");
		SysConf.Max_conf_user = sys.getProperty("Max_conf_user");
		SysConf.Max_conf_tourist = sys.getProperty("Max_conf_tourist");
		SysConf.Max_conf_spokesman = sys.getProperty("Max_conf_spokesman");
		SysConf.StartBeforeInto = Integer.parseInt(sys.getProperty("StartBeforeInto"));
		SysConf.DefaultConferenceTime = Integer.parseInt(sys.getProperty("DefaultConferenceTime"));
		SysConf.PKDetailUrl = sys.getProperty("PKDetailUrl");
		SysConf.PKDownLevelNumber = Integer.parseInt(sys.getProperty("PKDownLevelNumber"));
		SysConf.PKJudgementRewardBasic = Integer.parseInt(sys.getProperty("PKJudgementRewardBasic"));
		SysConf.PKJudgementRewardManager = Integer.parseInt(sys.getProperty("PKJudgementRewardManager"));
		SysConf.PKWinRewardMoney = Integer.parseInt(sys.getProperty("PKWinRewardMoney"));
		SysConf.JudgeInterviewNotPassTitle = sys.getProperty("JudgeInterviewNotPassTitle");
		SysConf.JudgeInterviewPassTitle = sys.getProperty("JudgeInterviewPassTitle");
		SysConf.MyJudgePath = sys.getProperty("MyJudgePath");
		SysConf.MyExpertPath = sys.getProperty("MyExpertPath");
		SysConf.JudgeAppNotPassTitle = sys.getProperty("JudgeAppNotPassTitle");
		SysConf.JudgeAppPassTitle = sys.getProperty("JudgeAppPassTitle");
		SysConf.ApplyJudgeQuestionCount = Integer.parseInt(sys.getProperty("ApplyJudgeQuestionCount"));
		SysConf.JudgeCheckNotPassTitle = sys.getProperty("JudgeCheckNotPassTitle");
		SysConf.JudgeCheckPassTitle = sys.getProperty("JudgeCheckPassTitle");
		SysConf.AppJudgeFilePath = sys.getProperty("AppJudgeFilePath");
		SysConf.AppExpertFilePath = sys.getProperty("AppExpertFilePath");
		SysConf.ChangeExpertLevelTitle = sys.getProperty("ChangeExpertLevelTitle");
		SysConf.ExpertAppPassTitle = sys.getProperty("ExpertAppPassTitle");
		SysConf.ExpertAppNotPassTitle = sys.getProperty("ExpertAppNotPassTitle");
		SysConf.ExpertInterviewNotPassTitle = sys.getProperty("ExpertInterviewNotPassTitle");
		SysConf.ExpertInterviewPassTitle = sys.getProperty("ExpertInterviewPassTitle");
		SysConf.ExpertCheckPassTitle = sys.getProperty("ExpertCheckPassTitle");
		SysConf.ExpertCheckNotPassTitle = sys.getProperty("ExpertCheckNotPassTitle");
		SysConf.VideoDetailHref = sys.getProperty("VideoDetailHref");
		SysConf.SerialManagerCommentPath = sys.getProperty("SerialManagerCommentPath");
		SysConf.FullJudgeCount = Integer.parseInt(sys.getProperty("FullJudgeCount"));
		SysConf.FullExpertCount = Integer.parseInt(sys.getProperty("FullExpertCount"));
		SysConf.ComplaintFailedTitle = sys.getProperty("ComplaintFailedTitle");
		SysConf.CancelJudgementQualificationTitle = sys.getProperty("CancelJudgementQualificationTitle");
		SysConf.AddOneComplaintToJudgeTitle = sys.getProperty("AddOneComplaintToJudgeTitle");
		SysConf.ComplaintJudgeUrl = sys.getProperty("ComplaintJudgeUrl");
		SysConf.ComplaintExpertUrl = sys.getProperty("ComplaintExpertUrl");
		SysConf.MyHome = sys.getProperty("MyHome");
		SysConf.ComplaintExpertSuccess = sys.getProperty("ComplaintExpertSuccess");
		SysConf.CertificateName = sys.getProperty("CertificateName");
		SysConf.AwardCertificateTitle = sys.getProperty("AwardCertificateTitle");
		SysConf.TMBAReplyFee = Double.parseDouble(sys.getProperty("TMBAReplyFee"));
		SysConf.TMBASecondReplyFee = Double.parseDouble(sys.getProperty("TMBASecondReplyFee"));
		SysConf.SMBAReplyFee = Double.parseDouble(sys.getProperty("SMBAReplyFee"));
		SysConf.SMBASecondReplyFee = Double.parseDouble(sys.getProperty("SMBASecondReplyFee"));
		SysConf.RMBAReplyFee = Double.parseDouble(sys.getProperty("RMBAReplyFee"));
		SysConf.RMBASecondReplyFee = Double.parseDouble(sys.getProperty("RMBASecondReplyFee"));
		SysConf.CheckAppOpenreplyNoPassTitle = sys.getProperty("CheckAppOpenreplyNoPassTitle");
		SysConf.CheckAppOpenreplyPassTitle = sys.getProperty("CheckAppOpenreplyPassTitle");
		SysConf.AppReplyFilePath = sys.getProperty("AppReplyFilePath");
		SysConf.PKFilePath = sys.getProperty("PKFilePath");
		SysConf.CheckAppOpenreplyFileNoPassTitle = sys.getProperty("CheckAppOpenreplyFileNoPassTitle");
		SysConf.CheckAppOpenreplyFilePassTitle = sys.getProperty("CheckAppOpenreplyFilePassTitle");
		SysConf.CheckAppreplyNoPassTitle = sys.getProperty("CheckAppreplyNoPassTitle");
		SysConf.CheckAppreplyPassTitle = sys.getProperty("CheckAppreplyPassTitle");
		SysConf.MyReplyUrl = sys.getProperty("MyReplyUrl");
		SysConf.CheckReportNoPassTitle = sys.getProperty("CheckReportNoPassTitle");
		SysConf.CheckReportPassTitle = sys.getProperty("CheckReportPassTitle");
		SysConf.OpenReplyStartTitle = sys.getProperty("OpenReplyStartTitle");
		SysConf.OpenReplyEndTitle = sys.getProperty("OpenReplyEndTitle");
		SysConf.OpenReplyNoPasstTitle = sys.getProperty("OpenReplyNoPasstTitle");
		SysConf.OpenReplyPasstTitle = sys.getProperty("OpenReplyPasstTitle");
		SysConf.ReplyStartTitle = sys.getProperty("ReplyStartTitle");
		SysConf.ReplyEndTitle = sys.getProperty("ReplyEndTitle");
		SysConf.ReplyNoPassTitle = sys.getProperty("ReplyNoPassTitle");
		SysConf.ReplyPassTitle = sys.getProperty("ReplyPassTitle");
		SysConf.SecondReplyNoPassTitle = sys.getProperty("SecondReplyNoPassTitle");
		SysConf.SecondReplyPassTitle = sys.getProperty("SecondReplyPassTitle");
		SysConf.AppReplyConfHref = sys.getProperty("AppReplyConfHref");
		SysConf.PkConfHref = sys.getProperty("PkConfHref");
		SysConf.CreatReplyQQGroupMetting = sys.getProperty("CreatReplyQQGroupMetting");
		SysConf.LaunchPKPath = sys.getProperty("LaunchPKPath");
		SysConf.MajorPath = sys.getProperty("MajorPath");
		SysConf.JudgeComplaintCountGetPunish = Integer.parseInt(sys.getProperty("JudgeComplaintCountGetPunish"));
		SysConf.CheckAppPrereplyFileNoPassTitle = sys.getProperty("CheckAppPrereplyFileNoPassTitle");
		SysConf.CheckAppPrereplyFilePassTitle = sys.getProperty("CheckAppPrereplyFilePassTitle");
		SysConf.CheckAppreplyFileNoPassTitle = sys.getProperty("CheckAppPrereplyFileNoPassTitle");
		SysConf.CheckAppreplyFilePassTitle = sys.getProperty("CheckAppPrereplyFilePassTitle");
		SysConf.FromBasicToManagerUseScore = Integer.parseInt(sys.getProperty("FromBasicToManagerUseScore"));
		SysConf.FromaManagerToMajordomoUseScore = Integer.parseInt(sys.getProperty("FromaManagerToMajordomoUseScore"));
		SysConf.UseForwardScoreLevelUpOfFactor = Double.parseDouble(sys.getProperty("UseForwardScoreLevelUpOfFactor"));
		SysConf.UseOriginalScoreLevelUpOfFactor = Double.parseDouble(sys.getProperty("UseOriginalScoreLevelUpOfFactor"));
		SysConf.ChallengMettingOver = sys.getProperty("ChallengMettingOver");
		SysConf.MyCenterExpertUrl = sys.getProperty("MyCenterExpertUrl");
		SysConf.WaitingForChalleng = sys.getProperty("WaitingForChalleng");
		SysConf.MyTradeDetailUrl = sys.getProperty("MyTradeDetailUrl");
		SysConf.StartupPhotoPath = sys.getProperty("StartupPhotoPath");
		SysConf.AiPhotoPath = sys.getProperty("AiPhotoPath");
		SysConf.StartupMusicPath = sys.getProperty("StartupMusicPath");
		SysConf.SerialArticlePath = sys.getProperty("SerialArticlePath");
		SysConf.SerialCataloguePath = sys.getProperty("SerialCataloguePath");
		SysConf.startupCatalogueOrder = sys.getProperty("startupCatalogueOrder");
		SysConf.SerialAiArticlePath = sys.getProperty("SerialAiArticlePath");
		SysConf.SerialAiCataloguePath = sys.getProperty("SerialAiCataloguePath");
		SysConf.AiCatalogueOrder = sys.getProperty("AiCatalogueOrder");
		SysConf.HomeCid = sys.getProperty("HomeCid");
		SysConf.SerialUserVisitorPath = sys.getProperty("SerialUserVisitorPath");
		SysConf.VisitorAvatarPath = sys.getProperty("VisitorAvatarPath");
		SysConf.DefaultVisitorAvatar = sys.getProperty("DefaultVisitorAvatar");
		SysConf.flashCid = Integer.parseInt(sys.getProperty("FlashCid"));
		SysConf.RelateArticle = Long.valueOf(sys.getProperty("RelateArticle"));
		SysConf.SolrPhotoPath = sys.getProperty("SolrPhotoPath");
		SysConf.SerialCareerCataloguePath = sys.getProperty("SerialCareerCataloguePath");
		SysConf.SerialFinanceCataloguePath = sys.getProperty("SerialFinanceCataloguePath");
		SysConf.CareerCatalogueOrder = sys.getProperty("CareerCatalogueOrder");
		SysConf.FinanceCatalogueOrder = sys.getProperty("FinanceCatalogueOrder");
		SysConf.MemberCid = Integer.parseInt(sys.getProperty("MemberCid"));
		SysConf.CareerPhotoPath = sys.getProperty("CareerPhotoPath");
		SysConf.CareerMusicPath = sys.getProperty("CareerMusicPath");
		SysConf.FinancePhotoPath = sys.getProperty("FinancePhotoPath");
		SysConf.FileRecentPath = sys.getProperty("FileRecentPath");
		SysConf.GupagePhotoPath = sys.getProperty("GupagePhotoPath");
		SysConf.SerialGupageCataloguePath = sys.getProperty("SerialGupageCataloguePath");
		SysConf.GupageCatalogueOrder = sys.getProperty("GupageCatalogueOrder");
		SysConf.GupagePagerPath = sys.getProperty("GupagePagerPath");
		SysConf.EnterpriserPhotoPath = sys.getProperty("EnterpriserPhotoPath");
		SysConf.ManagernewsPhotoPath = sys.getProperty("ManagernewsPhotoPath");
		SysConf.SerialEnterpriserCataloguePath = sys.getProperty("SerialEnterpriserCataloguePath");
		SysConf.EnterpriserArticlePath = sys.getProperty("EnterpriserArticlePath");
		SysConf.AllarticlePhotoPath = sys.getProperty("AllarticlePhotoPath");
		SysConf.EnterpriserCatalogueOrder = sys.getProperty("EnterpriserCatalogueOrder");
		SysConf.LifePhotoPath = sys.getProperty("LifePhotoPath");
		SysConf.LifeCatalogueOrder = sys.getProperty("LifeCatalogueOrder");
		SysConf.SerialLifeCataloguePath = sys.getProperty("SerialLifeCataloguePath");
		SysConf.SerialManagerNewsCataloguePath = sys.getProperty("SerialManagerNewsCataloguePath");
		SysConf.SerialCommentsPath = sys.getProperty("SerialCommentsPath");
		SysConf.SerialTopicsPath = sys.getProperty("SerialTopicsPath");
		SysConf.SerialTravelPath = sys.getProperty("SerialTravelPath");
		SysConf.SerialEssayPath = sys.getProperty("SerialEssayPath");
		SysConf.EntrepreneurArticlesPath = sys.getProperty("EntrepreneurArticlesPath");
		SysConf.EntrepreneurPhotoPath = sys.getProperty("EntrepreneurPhotoPath");
		SysConf.EntrepreneurCataloguePath = sys.getProperty("EntrepreneurCataloguePath");
		SysConf.MoralNewsPhotoPath = sys.getProperty("MoralNewsPhotoPath");
		SysConf.MoralNewsCataloguePath = sys.getProperty("MoralNewsCataloguePath");
		SysConf.SerialMoralNewsCataloguePath = sys.getProperty("SerialMoralNewsCataloguePath");
		SysConf.IndexCid = Integer.parseInt(sys.getProperty("IndexCid"));
		SysConf.OfficialPhotoPath = sys.getProperty("OfficialPhotoPath");
	}
	
	//获取用户忽略消息文件路径封装
	public static String getUserIgnoreFilePath() {
		return SysConf.UserBasePath + SysConf.UserIgnoreMsgFilePath;
	}
	
	public static String getTpiUserIgnoreMsgFilePath() {
		return SysConf.UserBasePath + SysConf.TpiUserIgnoreMsgFilePath;
	}
}