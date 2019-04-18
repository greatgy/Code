package com.supergenius.xo.manager.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EJudgementLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;

/**
 * 申请裁判实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午3:13:57
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class AppJudgement extends BaseEntity {

	private static final long serialVersionUID = 7205737685389452826L;
	private String useruid;// 申请人Uid
	@Json(strategy = Json.webStrategy)
	private String username;// 学员姓名
	@Json(strategy = Json.webStrategy)
	private EMajor major;// 专业
	@Json(strategy = Json.webStrategy)
	private EJudge type;// 裁判申请的类别
	@Json(strategy = Json.webStrategy)
	private EStudentLevel majorlevel;// 专业级别
	private EUser userlevel;// 学员级别
	private EJudgementLevel judgelevel;// 裁判级别
	private String appstudentuid;// 学员申请Uid
	@Json(strategy = Json.webStrategy)
	private String desc;// 申请描述
	@Json(strategy = Json.webStrategy)
	private String file;// 学员（裁判）上传题目
	@Json(strategy = Json.webStrategy)
	private String file2;// 反馈材料
	@Json(strategy = Json.webStrategy)
	private int topiccount;// 通过审核的上传题目数
	private String descto;// 反馈描述
	@Json(strategy = Json.webStrategy)
	private boolean isonline;// 是否通过在线
	@Json(strategy = Json.webStrategy)
	private String providetimes;// 提供的空闲时间
	@Json(strategy = Json.webStrategy)
	private String providetime;// 采纳时间
	private EJudgementLevel levelfrom;// 申请前裁判级别(暂时保留)
	private EJudgementLevel levelto;// 申请后裁判级别(暂时保留)
	@Json(strategy = Json.webStrategy)
	private EAppJudgementStage stage;// 可申请阶段，未提交申请、裁判申请审核、裁判申请未通过、裁判申请通过，上传资料、裁判资料审核中、材料审核未通过，请上重新传资料、裁判材料审核通过等待面试中、面试进行中、面试结束等待结果、面试为通过、面试通过获得X级裁判资格、专职裁判
	private String confuid;// 会议室Uid
	private String args;// 参数，可灵活使用
	private String userSn;
	private String certificate;
	private List<String> fileList;
	private List<String> file2List;
	private DateTime interviewTime;

	public AppJudgement() {
		super();
	}
	
	public AppJudgement(String useruid, String username, EMajor major, EJudge type, EStudentLevel majorlevel, EUser userlevel, String appstudentuid, String desc, EAppJudgementStage stage) {
		super();
		this.useruid = useruid;
		this.username = username;
		this.major = major;
		this.type = type;
		this.majorlevel = majorlevel;
		this.userlevel = userlevel;
		this.appstudentuid = appstudentuid;
		this.desc = desc;
		this.stage = stage;
	}
	
	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return (this.major != null) ? this.major.getName() : "";
	}
	
	public EMajor getMajor() {
		return major;
	}

	public void setMajor(EMajor major) {
		this.major = major;
	}

	public EJudge getType() {
		return type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}
	
	public void setType(EJudge type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getMajorlevelName() {
		return (this.majorlevel != null) ? this.majorlevel.getName() : "";
	}
	
	public EStudentLevel getMajorlevel() {
		return majorlevel;
	}

	public void setMajorlevel(EStudentLevel majorlevel) {
		this.majorlevel = majorlevel;
	}

	public EUser getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(EUser userlevel) {
		this.userlevel = userlevel;
	}

	public EJudgementLevel getJudgelevel() {
		return judgelevel;
	}

	public void setJudgelevel(EJudgementLevel judgelevel) {
		this.judgelevel = judgelevel;
	}

	public String getAppstudentuid() {
		return appstudentuid;
	}

	public void setAppstudentuid(String appstudentuid) {
		this.appstudentuid = appstudentuid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public int getTopiccount() {
		return topiccount;
	}

	public void setTopiccount(int topiccount) {
		this.topiccount = topiccount;
	}

	public String getDescto() {
		return descto;
	}

	public void setDescto(String descto) {
		this.descto = descto;
	}

	public boolean isIsonline() {
		return isonline;
	}

	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}

	public String getProvidetimes() {
		return providetimes;
	}

	public void setProvidetimes(String providetimes) {
		this.providetimes = providetimes;
	}

	public String getProvidetime() {
		return providetime;
	}

	public void setProvidetime(String providetime) {
		this.providetime = providetime;
	}

	public EJudgementLevel getLevelfrom() {
		return levelfrom;
	}

	public void setLevelfrom(EJudgementLevel levelfrom) {
		this.levelfrom = levelfrom;
	}

	public EJudgementLevel getLevelto() {
		return levelto;
	}

	public void setLevelto(EJudgementLevel levelto) {
		this.levelto = levelto;
	}

	public EAppJudgementStage getStage() {
		return stage;
	}

	@Json(strategy = Json.webStrategy)
	public String getStageName() {
		return (this.stage != null) ? this.getStage().getName() : "";
	}
	
	public void setStage(EAppJudgementStage stage) {
		this.stage = stage;
	}

	public String getConfuid() {
		return confuid;
	}

	public void setConfuid(String confuid) {
		this.confuid = confuid;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	
	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserSn() {
		return this.userSn;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getCertificate() {
		return this.certificate;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	
	@Json(strategy = Json.webStrategy)
	public List<String> getFileList() {
		return this.fileList;
	}
	
	public void setFile2List(List<String> file2List) {
		this.file2List = file2List;
	}
	
	@Json(strategy = Json.webStrategy)
	public List<String> getFile2List() {
		return this.file2List;
	}
	public void setInterviewTime(DateTime interviewTime) {
		this.interviewTime = interviewTime;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getInterviewTime() {
		return (this.interviewTime != null) ? this.interviewTime.toString(DateUtil.FORMAT_DATETIME_CHINA) : "";
	}
}
