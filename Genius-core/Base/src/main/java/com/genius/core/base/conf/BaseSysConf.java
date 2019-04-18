package com.genius.core.base.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.StrUtil;

/**
 * @author Architect.bian
 *
 */
public abstract class BaseSysConf {

	public static final String Separator_Directory = "/";

	public static final int SessionIDLength = 6;
	
	// 随机生成的字符组成
	public static String CaptchaRandomWordGenerator = "abcdefghijklmnopquvwxyz123456789";
	// 验证码图片上显示的字符个数
	public static int CaptchaMinTextSize = 4;
	public static int CaptchaMaxTextSize = 4;
	// 验证码图片上显示的字符的大小设置
	public static int CaptchaMinFontSize = 15;
	public static int CaptchaMaxFontSize = 18;
	public static int CaptchaImgWidth = 100;
	public static int CaptchaImgHeight = 30;
	public static int captchaShowUntilLoginCount;
	
	//超过最多登录次数显示验证码
	public static final int maxLoginCount = 6;
	public static int Expire_UserSession = 30*60;

	public static String EmailResetPwdUrlFormat;
	public static String EmailHost;
	public static String EmailHostName;
	public static String EmailHostPwd;
	public static String EmailHostEmailAddr;
	public static Map<String, String> EmailMainMap = new HashMap<String, String>();
	public static String EmailValidateUrlFormat;
	public static String EmailTemplateDirectoryPath = "";
	public static String EmailTemplaterSplitter = "[#------beautiful splitter for admin--------]";

	public static String WebSiteName;
	public static String WebSiteUri;
	public static String CookieDomain;
	
	public static String WebAppDir;//网站App所在绝对路径
	public static String ImgSiteBasePath;//图片服务器所在绝对路径
	public static String ImgUploadBasePath;//图片上传所在的绝对路径
	public static String FileSiteBasePath;//文件服务器所在绝对路径
	private static String ImgUserDir = "/imgs/user";//用户图片的相对路径
	public static String ImgUserDataDir_Slash;//BaseSysConf.ImgSiteBasePath + SysConf.ImgUserDataDir_Slash = /root/basepath/imguserpath/
	private static String ImgWebDataDir = "/imgs/webdata";
	public static String CaptchaImgDir = "/captcha/imgs";//默认验证码图片地址
	//网站图片数据的相对路径，文件分隔符结尾
	public static String ImgWebDataDir_Slash;
	private static String VideoWebDataDir = "/file/video";
	//网站视频数据的相对路径，文件分隔符结尾
	public static String VideoWebDataDir_Slash;
	public static String VideoPlayerName = "";//网站播放器的名称及参数
	public static String ImgWaterMark;
	public static int ImgWaterMarkMinSize = 300;
	public static int[][] ImgDefaultSizes = new int[][] {{900, 300}, {300, 200}, {150, 100}};
	
	public static String NotDeleteImgsRegex = ".*noavatar\\.png|.*noavatar180x180\\.png";

	public static String SerialBasePath;
	public static String SerialSEOPath;
	public static String SerialSkinPath;
	public static String SerialSkinExt = ".skin";
	public static String SerialMorePath;
	public static String SerialDataBoxExt = ".box";
	
	public static int descriptionMaxWordCount = 80;
	public static int keywordsMaxWordCount = 30;
	
	public static String aliyunAccessKeyId;
	public static String aliyunAccessKeySecret;
	public static String aliyunSignName;
	
	private static Properties sys;
	
	public static String getValue(String key){
		return sys.getProperty(key);
	}
	
	public static String getSEOPath(String site){
		return sys.getProperty("SerialBasePath") + sys.getProperty("SerialSEOPath" + BaseStrDict.dot + site);
	}
	
	@Value("#{sys}")
	public void setBaseSysConf(Properties sys) {
		BaseSysConf.sys = sys;
		String captchaRandomWordGenerator = sys.getProperty("CaptchaRandomWordGenerator");
		if (StrUtil.isNotEmpty(captchaRandomWordGenerator)) {
			BaseSysConf.CaptchaRandomWordGenerator = sys.getProperty("CaptchaRandomWordGenerator");
		}
		String CaptchaMinTextSize = sys.getProperty("CaptchaMinTextLen");
		if (StrUtil.isNumeric(CaptchaMinTextSize)) {
			BaseSysConf.CaptchaMinTextSize = Integer.valueOf(CaptchaMinTextSize);
		}
		String CaptchaMaxTextSize = sys.getProperty("CaptchaMaxTextLen");
		if (StrUtil.isNumeric(CaptchaMaxTextSize)) {
			BaseSysConf.CaptchaMaxTextSize = Integer.valueOf(CaptchaMaxTextSize);
		}
		String CaptchaMinFontSize = sys.getProperty("CaptchaMinFontSize");
		if (StrUtil.isNumeric(CaptchaMinFontSize)) {
			BaseSysConf.CaptchaMinFontSize = Integer.valueOf(CaptchaMinFontSize);
		}
		String CaptchaMaxFontSize = sys.getProperty("CaptchaMaxFontSize");
		if (StrUtil.isNumeric(CaptchaMaxFontSize)) {
			BaseSysConf.CaptchaMaxFontSize = Integer.valueOf(CaptchaMaxFontSize);
		}
		String CaptchaImgWidth = sys.getProperty("CaptchaImgWidth");
		if (StrUtil.isNumeric(CaptchaImgWidth)) {
			BaseSysConf.CaptchaImgWidth = Integer.valueOf(CaptchaImgWidth);
		}
		String CaptchaImgHeight = sys.getProperty("CaptchaImgHeight");
		if (StrUtil.isNumeric(CaptchaImgHeight)) {
			BaseSysConf.CaptchaImgHeight = Integer.valueOf(CaptchaImgHeight);
		}
		String captchaShowUntilLoginCount = sys.getProperty("captchaShowUntilLoginCount");
		if (StrUtil.isNumeric(captchaShowUntilLoginCount)) {
			BaseSysConf.captchaShowUntilLoginCount = Integer.valueOf(captchaShowUntilLoginCount);
		}
		String Expire_UserSession = sys.getProperty("Expire_UserSession");
		if (StrUtil.isNumeric(Expire_UserSession)) {
			BaseSysConf.Expire_UserSession = Integer.valueOf(Expire_UserSession);
		}
		BaseSysConf.EmailHost = sys.getProperty("EmailHost");
		BaseSysConf.EmailHostName = sys.getProperty("EmailHostName");
		BaseSysConf.EmailHostPwd = sys.getProperty("EmailHostPwd");
		BaseSysConf.EmailHostEmailAddr = sys.getProperty("EmailHostEmailAddr");
		BaseSysConf.EmailTemplateDirectoryPath = sys.getProperty("EmailTemplateDirectoryPath");
		String EmailTemplaterSplitter = sys.getProperty("EmailTemplaterSplitter");
		if (EmailTemplaterSplitter != null) {
			BaseSysConf.EmailTemplaterSplitter = EmailTemplaterSplitter;
		}
		
		BaseSysConf.WebSiteName = sys.getProperty("WebSiteName");
		BaseSysConf.WebSiteUri = sys.getProperty("WebSiteUri");
		BaseSysConf.CookieDomain = sys.getProperty("CookieDomain");
		BaseSysConf.WebAppDir = sys.getProperty("WebAppDir");
		BaseSysConf.ImgSiteBasePath = sys.getProperty("ImgSiteBasePath");
		BaseSysConf.ImgUploadBasePath = sys.getProperty("ImgUploadBasePath");
		if(StrUtil.isEmpty(BaseSysConf.ImgUploadBasePath)) {
			BaseSysConf.ImgUploadBasePath = BaseSysConf.ImgSiteBasePath;
		}
		BaseSysConf.FileSiteBasePath = sys.getProperty("FileSiteBasePath");
		String ImgUserDir = sys.getProperty("ImgUserDir");
		if (StrUtil.isNotEmpty(ImgUserDir)) {
			BaseSysConf.ImgUserDir = ImgUserDir;
		}
		BaseSysConf.ImgUserDataDir_Slash = BaseSysConf.ImgUserDir + BaseStrDict.slash;
		String ImgWebDataDir = sys.getProperty("ImgWebDataDir");
		if (ImgWebDataDir != null) {
			BaseSysConf.ImgWebDataDir = ImgWebDataDir;
		}
		String CaptchaImgDir = sys.getProperty("CaptchaImgDir");
		if (CaptchaImgDir != null) {
			BaseSysConf.CaptchaImgDir = CaptchaImgDir;
		}
		BaseSysConf.ImgWebDataDir_Slash = BaseSysConf.ImgWebDataDir + BaseStrDict.slash;
		String VideoWebDataDir = sys.getProperty("VideoWebDataDir");
		if (VideoWebDataDir != null) {
			BaseSysConf.VideoWebDataDir = VideoWebDataDir;
		}
		BaseSysConf.VideoWebDataDir_Slash = BaseSysConf.VideoWebDataDir + BaseStrDict.slash;
		String VideoPlayerName = sys.getProperty("VideoPlayerName");
		if (VideoPlayerName != null) {
			BaseSysConf.VideoPlayerName = VideoPlayerName;
		}
		BaseSysConf.ImgWaterMark = sys.getProperty("ImgWaterMark");
		String ImgWaterMarkMinSize = sys.getProperty("ImgWaterMarkMinSize");
		if (StrUtil.isNumeric(ImgWaterMarkMinSize)) {
			BaseSysConf.ImgWaterMarkMinSize = Integer.valueOf(ImgWaterMarkMinSize);
		}
		String NotDeleteImgsRegex = sys.getProperty("NotDeleteImgsRegex");
		if (NotDeleteImgsRegex != null) {
			BaseSysConf.NotDeleteImgsRegex = NotDeleteImgsRegex;
		}
		BaseSysConf.EmailResetPwdUrlFormat = sys.getProperty("EmailResetPwdUrlFormat");
		BaseSysConf.EmailValidateUrlFormat = sys.getProperty("EmailValidateUrlFormat");
		BaseSysConf.SerialBasePath = sys.getProperty("SerialBasePath");
		
		String serialSEOPath = sys.getProperty("SerialSEOPath");
		if (StrUtil.isNotEmpty(serialSEOPath)) {
			BaseSysConf.SerialSEOPath = BaseSysConf.SerialBasePath + serialSEOPath;
		}
		
		String descriptionMaxWordCount = sys.getProperty("descriptionMaxWordCount");
		if (StrUtil.isNumeric(descriptionMaxWordCount)) {
			BaseSysConf.descriptionMaxWordCount = Integer.valueOf(descriptionMaxWordCount);
		}
		String keywordsMaxWordCount = sys.getProperty("keywordsMaxWordCount");
		if (StrUtil.isNumeric(keywordsMaxWordCount)) {
			BaseSysConf.keywordsMaxWordCount = Integer.valueOf(keywordsMaxWordCount);
		}
		
		String EmailMainList = sys.getProperty("EmailMainList");
		if (EmailMainList == null) {
			EmailMainList = "@163.com|http://mail.163.com/,@126.com|http://www.126.com/,@yeah.net|http://email.yeah.net/,@sina.com|http://mail.sina.com.cn/,@qq.com|http://mail.qq.com/,@gmail.com|https://mail.google.com/,@139.com|http://mail.10086.cn/,@189.cn|http://mail.189.cn/,@sohu.com|http://mail.sohu.com/,@tom.com|http://mail.tom.com/,@yahoo.com|http://mail.yahoo.com,@outlook.com|http://www.hotmail.com/,@sogou.com|http://mail.sogou.com/,@263.net|http://www.263.net/,@21cn.com|http://mail.21cn.com/,@vip.163.com|http://vipmail.163.com,@vipmail.163.com|http://vipmail.163.com,@188.com|http://www.188.com/,@vip.126.com|http://vipmail.163.com,@sina.cn|http://mail.sina.com.cn/,@vip.sina.com|http://vip.sina.com.cn/,@2008.sina.com|http://mail.sina.com.cn/,@vip.qq.com|http://mail.qq.com/,@foxmail.com||http://mail.qq.com/,@hotmail.com|http://www.hotmail.com/,@live.cn|http://www.hotmail.com/,@live.com|http://www.hotmail.com/,@yahoo.com.cn|http://mail.cn.yahoo.com/,@yahoo.cn|http://mail.cn.yahoo.com/,@aliyun.com|http://mail.aliyun.com,@china.com|http://mail.china.com/,@wo.com.cn|http://mail.wo.com.cn/,@2980.com|http://www.2980.com/";
		}
		String[] emails = EmailMainList.split(BaseStrDict.comma);
		for (String email : emails) {
			String[] kv = email.split(BaseStrDict.vLineRegSplitter);
			if (kv.length == 2) {
				BaseSysConf.EmailMainMap.put(kv[0], kv[1]);
			}
		}
		
		BaseSysConf.aliyunAccessKeyId = sys.getProperty("AliyunAccessKeyId");
		BaseSysConf.aliyunAccessKeySecret = sys.getProperty("AliyunAccessKeySecret");
		BaseSysConf.aliyunSignName = sys.getProperty("AliyunSignName");
		
	}
}