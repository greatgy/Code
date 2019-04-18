package com.supergenius.xo.manager.entity;

import java.util.List;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.user.entity.User;

/** 
 * 申请答辩实体类
 * @author chenminchang
 * @date 2016-7-17 下午3:05:47 
 */
public class AppReply extends BaseEntity {

	private static final long serialVersionUID = 7768729548588678877L;
	@Json(strategy = Json.webStrategy)
	private String sn; //答辩号
	private String useruid; //用户Uid
	@Json(strategy = Json.webStrategy)
	private EMajor major; //专业(二进制)
	private EStudentLevel majorlevel;
	private String appstudentuid; //申请表学员的Uid
	private String confuid; //会议室Uid
	private String desc; //申请描述
	private String descto; //反馈描述
	@Json(strategy = Json.webStrategy)
	private String file; //学员上传材料
	@Json(strategy = Json.webStrategy)
	private String file2; //反馈材料
	private String topic; //题目
	private String certificated; //已获得的证书
	private String certificate; //申请获得的证书
	private boolean isvideoreply; //答辩是否通过视频系统(0否1是)
	@Json(strategy = Json.webStrategy)
	private boolean isvideotopic; //开题是否通过视频系统(0否1是)
	@Json(strategy = Json.webStrategy)
	private String opentimes; //提供的开题论证会的时间，逗号分隔
	@Json(strategy = Json.webStrategy)
	private String opentimeok; //确定的开题论证会时间
	@Json(strategy = Json.webStrategy)
	private String replytimes; //提供的答辩的时间，逗号分隔
	@Json(strategy = Json.webStrategy)
	private String replytimeok; //确定的答辩时间
	@Json(strategy = Json.webStrategy)
	private EReplyStage replystage; //申请状态(开题|预答辩|答辩|获得证书)
	private String name;//答辩的名称
	private String openaddress;//开题论证会地点
	private String replyaddress;//答辩地点
	@Json(strategy = Json.webStrategy)
	private String qqgroup;//挑战视频qq群
	private String data;//存放数据
	private boolean iscomplaint;//是否有举报记录
	private String degree;//正在申请的级别
	private AppReplyDetail appReplyDetail;//级别明细
	private double replyfee;//答辩费用
	private List<AppReplyDetail> appreplydetaillist;//挑战状态明细 
	private User user;//关联user对象
	private Expert expert;//关联答辩专家对象
	private Expert expert2;//关联答辩专家对象
	private Expert expert3;//关联答辩专家对象

	public AppReply() {
		super();
	}
	
	/**
	 * 提交开题申请时生成一条记录的构造函数
	 * @param sn
	 * @param useruid
	 * @param major
	 * @param majorlevel
	 * @param appstudentuid
	 * @param replystage
	 * @author: XieMing
	 */
	public AppReply(String sn, String useruid, EMajor major, EStudentLevel majorlevel,
					String appstudentuid, String certificated, EReplyStage replystage) {
		super();
		this.sn = sn;
		this.useruid = useruid;
		this.major = major;
		this.majorlevel = majorlevel;
		this.appstudentuid = appstudentuid;
		this.certificated = certificated;
		this.replystage = replystage;
	}
	
	@Json(strategy = Json.webStrategy)
	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	@Json(strategy = Json.webStrategy)
	public Expert getExpert2() {
		return expert2;
	}

	public void setExpert2(Expert expert2) {
		this.expert2 = expert2;
	}

	@Json(strategy = Json.webStrategy)
	public Expert getExpert3() {
		return expert3;
	}

	public void setExpert3(Expert expert3) {
		this.expert3 = expert3;
	}

	@Json(strategy = Json.webStrategy)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Json(strategy = Json.webStrategy)
	public List<AppReplyDetail> getAppreplydetaillist() {
		return appreplydetaillist;
	}

	public void setAppreplydetaillist(List<AppReplyDetail> appreplydetaillist) {
		this.appreplydetaillist = appreplydetaillist;
	}

	@Json(strategy = Json.webStrategy)
	public double getReplyfee() {
		return replyfee;
	}

	public void setReplyfee(double replyfee) {
		this.replyfee = replyfee;
	}

	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return getMajor().getName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public AppReplyDetail getAppReplyDetail() {
		return appReplyDetail;
	}

	public void setAppReplyDetail(AppReplyDetail appReplyDetail) {
		this.appReplyDetail = appReplyDetail;
	}

	@Json(strategy = Json.webStrategy)
	public String getDegree() {
		return degree;
	}
	
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public boolean isIscomplaint() {
		return iscomplaint;
	}
	
	public void setIscomplaint(boolean iscomplaint) {
		this.iscomplaint = iscomplaint;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public EMajor getMajor() {
		return major;
	}
	
	public void setMajor(EMajor major) {
		this.major = major;
	}
	
	public String getOpenaddress() {
		return openaddress;
	}

	public void setOpenaddress(String openaddress) {
		this.openaddress = openaddress;
	}

	@Json(strategy = Json.webStrategy)
	public String getReplyaddress() {
		return replyaddress;
	}

	public void setReplyaddress(String replyaddress) {
		this.replyaddress = replyaddress;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Json(strategy = Json.webStrategy)
	public String getMajorlevelName() {
		return getMajorlevel().getName();
	}
	
	public EStudentLevel getMajorlevel() {
		return majorlevel;
	}
	
	public void setMajorlevel(EStudentLevel majorlevel) {
		this.majorlevel = majorlevel;
	}
	
	public String getAppstudentuid() {
		return appstudentuid;
	}
	
	public void setAppstudentuid(String appstudentuid) {
		this.appstudentuid = appstudentuid;
	}
	
	public String getConfuid() {
		return confuid;
	}
	
	public void setConfuid(String confuid) {
		this.confuid = confuid;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDescto() {
		return descto;
	}
	
	public void setDescto(String descto) {
		this.descto = descto;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile2() {
		return file2;
	}
	
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getCertificated() {
		return certificated;
	}
	
	public void setCertificated(String certificated) {
		this.certificated = certificated;
	}
	
	public String getCertificate() {
		return certificate;
	}
	
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public boolean isIsvideoreply() {
		return isvideoreply;
	}
	
	public void setIsvideoreply(boolean isvideoreply) {
		this.isvideoreply = isvideoreply;
	}
	
	public boolean isIsvideotopic() {
		return isvideotopic;
	}
	
	public void setIsvideotopic(boolean isvideotopic) {
		this.isvideotopic = isvideotopic;
	}
	
	public String getOpentimes() {
		return opentimes;
	}
	
	public void setOpentimes(String opentimes) {
		this.opentimes = opentimes;
	}
	
	public String getOpentimeok() {
		return opentimeok;
	}
	
	public void setOpentimeok(String opentimeok) {
		this.opentimeok = opentimeok;
	}
	
	public String getReplytimes() {
		return replytimes;
	}
	
	public void setReplytimes(String replytimes) {
		this.replytimes = replytimes;
	}
	
	public String getReplytimeok() {
		return replytimeok;
	}
	
	public void setReplytimeok(String replytimeok) {
		this.replytimeok = replytimeok;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getReplystageName() {
	    return this.replystage.getName();	
	}
	
	public EReplyStage getReplystage() {
		return replystage;
	}
	
	public void setReplystage(EReplyStage replystage) {
		this.replystage = replystage;
	}

	public String getQqgroup() {
		return qqgroup;
	}

	public void setQqgroup(String qqgroup) {
		this.qqgroup = qqgroup;
	}
	
}