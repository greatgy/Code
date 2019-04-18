package com.supergenius.xo.manager.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EAppExpert;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EStudentLevel;

/** 
 * 申请专家
 * @author chenminchang
 * @date 2016-7-18 上午10:34:40 
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class AppExpert extends BaseEntity {

	private static final long serialVersionUID = 482746891171298830L;
	private String useruid; //申请人uid
	private String expertuid; //特批专家的专家表uid，否则为空
	@Json(strategy = Json.webStrategy)
	private EMajor major; //专业
	@Json(strategy = Json.webStrategy)
	private EStudentLevel majorlevel; //用户级别
	@Json(strategy = Json.webStrategy)
	private String certificateuid; //已获得的证书uid
	private String file; //上传研究报告
	private String file2; //反馈材料
	@Json(strategy = Json.webStrategy)
	private String providetimes;// 提供的空闲时间
	@Json(strategy = Json.webStrategy)
	private String providetime;// 采纳时间
	private EExpertLevel levelfrom; //申请前的等级，如果是会员/学员申请成为专家的话，则为null
	private EExpertLevel levelto; //申请后的等级
	private EAppExpert type; //申请类型：用户申请成为专家、专家晋级、管理员申请成为专家、特批专家申请、特聘专家申请
	@Json(strategy = Json.webStrategy)
	private EAppExpertStage stage; //申请状态
	private int replycount; //作为专家参加的答辩场数
	private int complaintcount; //被举报次数
	@Json(strategy = Json.webStrategy)
	private String desc; //描述
	private String auditdesc; //管理员审批描述
	private String auditadminuid; //审批管理员adminuid
	private String certificate;
	private String userSn;
	private List<String> fileList;
	private List<String> file2List;
	private String userName;
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getExpertuid() {
		return expertuid;
	}
	
	public void setExpertuid(String expertuid) {
		this.expertuid = expertuid;
	}
	
	public EMajor getMajor() {
		return major;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return this.major.getName();
	}
	
	public void setMajor(EMajor major) {
		this.major = major;
	}
	
	public EStudentLevel getMajorlevel() {
		return majorlevel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getMajorlevelName() {
		return this.majorlevel.getName();
	}
	
	public void setMajorlevel(EStudentLevel majorlevel) {
		this.majorlevel = majorlevel;
	}
	
	public String getCertificateuid() {
		return certificateuid;
	}
	
	public void setCertificateuid(String certificateuid) {
		this.certificateuid = certificateuid;
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
	
	public EExpertLevel getLevelfrom() {
		return levelfrom;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getLevelFromName() {
		return (this.levelfrom != null) ? this.levelfrom.getName() : "";
	}
	
	public void setLevelfrom(EExpertLevel levelfrom) {
		this.levelfrom = levelfrom;
	}
	
	public EExpertLevel getLevelto() {
		return levelto;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getLevelToName() {
		return this.levelto.getName();
	}
	
	public void setLevelto(EExpertLevel levelto) {
		this.levelto = levelto;
	}
	
	public EAppExpert getType() {
		return type;
	}
	
	public void setType(EAppExpert type) {
		this.type = type;
	}
	
	public EAppExpertStage getStage() {
		return stage;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getStageName() {
		return this.stage.getName();
	}
	
	public void setStage(EAppExpertStage stage) {
		this.stage = stage;
	}
	
	public int getReplycount() {
		return replycount;
	}
	
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	
	public int getComplaintcount() {
		return complaintcount;
	}
	
	public void setComplaintcount(int complaintcount) {
		this.complaintcount = complaintcount;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getAuditdesc() {
		return auditdesc;
	}
	
	public void setAuditdesc(String auditdesc) {
		this.auditdesc = auditdesc;
	}
	
	public String getAuditadminuid() {
		return auditadminuid;
	}
	
	public void setAuditadminuid(String auditadminuid) {
		this.auditadminuid = auditadminuid;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getCertificate() {
		return (this.certificate != null) ? this.certificate : "";
	}

	public void setUserSn(String usersn) {
		this.userSn = usersn;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserSn() {
		return this.userSn;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserName() {
		return this.userName;
	}
	
}