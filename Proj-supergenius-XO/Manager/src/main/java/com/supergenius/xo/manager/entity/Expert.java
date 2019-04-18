package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/** 
 * 专家实体
 * @author chenminchang
 * @date 2016-7-18 上午10:12:48 
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Expert extends BaseEntity {

	private static final long serialVersionUID = -53626855547968238L;
	@Json(strategy = Json.webStrategy)
	private String useruid; //用户Uid
	@Json(strategy = Json.webStrategy)
	private String sn; //专家编号
	@Json(strategy = Json.webStrategy)
	private EMajor major; //专业
	@Json(strategy = Json.webStrategy)
	private EExpertLevel level; //专家等级
	@Json(strategy = Json.webStrategy)
	private int chaircount; //参加的答辩场数
	@Json(strategy = Json.webStrategy)
	private int complaintcount;// 被举报次数
	private String desc; //描述
	@Json(strategy = Json.webStrategy)
	private EExpert type; //专家类型：0：普通专家（学员晋级）,1：专职专家,2:特批专家,3:特聘专家
	private String certificateuid; //证书uid
	private String file; //上传研究报告
	private User user; //关联用户
	private Certificate certificate;//关联证书对象
	private String appexpertuid;//关联申请专家表的uid
	private String usersn;//会员号
	private String username;//会员姓名
	
	public Expert() {
		super();
	}
	
	public Expert(String useruid, String sn, EMajor major, EExpertLevel level, String desc, EExpert type, String certificateuid, String file) {
		super();
		this.useruid = useruid;
		this.sn = sn;
		this.major = major;
		this.level = level;
		this.desc = desc;
		this.type = type;
		this.certificateuid = certificateuid;
		this.file = file;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUsersn() {
		return usersn;
	}

	public void setUsersn(String usersn) {
		this.usersn = usersn;
	}

	@Json(strategy = Json.webStrategy)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAppexpertuid() {
		return appexpertuid;
	}

	public void setAppexpertuid(String appexpertuid) {
		this.appexpertuid = appexpertuid;
	}

	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
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
	
	public EExpertLevel getLevel() {
		return level;
	}
	
	public void setLevel(EExpertLevel level) {
		this.level = level;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getLevelName() {
		return this.level.getName();
	}
	
	public int getChaircount() {
		return chaircount;
	}
	
	public int getComplaintcount() {
		return complaintcount;
	}

	public void setComplaintcount(int complaintcount) {
		this.complaintcount = complaintcount;
	}

	public void setChaircount(int chaircount) {
		this.chaircount = chaircount;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public EExpert getType() {
		return type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}
	
	public void setType(EExpert type) {
		this.type = type;
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
	
	@Json(strategy = Json.webStrategy)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

}