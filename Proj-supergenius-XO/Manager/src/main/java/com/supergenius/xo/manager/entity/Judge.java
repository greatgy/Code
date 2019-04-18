package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EJudgementLevel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/**
 * 裁判实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午2:59:03
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Judge extends BaseEntity {

	private static final long serialVersionUID = -7223228121002896714L;
	private String useruid;// 用户Uid
	@Json(strategy = Json.webStrategy)
	private String sn;// 裁判编号
	@Json(strategy = Json.webStrategy)
	private EJudge type;// 裁判类别（普通裁判，专职裁判，特批裁判，特聘裁判）
	@Json(strategy = Json.webStrategy)
	private EMajor major;// 专业
	private EJudgementLevel level;// 裁判级别(暂时保留，以免需求再改)
	private String certificateuid;// 裁判证书uid
	@Json(strategy = Json.webStrategy)
	private int judgecount;// 评判场数
	@Json(strategy = Json.webStrategy)
	private int complaintcount;// 被举报次数
	@Json(strategy = Json.webStrategy)
	private String desc;// 描述
	private User user; //关联用户
	private String username;//用户姓名

	public Judge() {
		super();
	}
	
	public Judge(String useruid, String sn, EJudge type, EMajor major) {
		super();
		this.useruid = useruid;
		this.sn = sn;
		this.type = type;
		this.major = major;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return this.type.getName();
	}
	
	public EJudge getType() {
		return type;
	}

	public void setType(EJudge type) {
		this.type = type;
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

	public EJudgementLevel getLevel() {
		return level;
	}

	public void setLevel(EJudgementLevel level) {
		this.level = level;
	}

	public String getCertificateuid() {
		return certificateuid;
	}

	public void setCertificateuid(String certificateuid) {
		this.certificateuid = certificateuid;
	}

	public int getJudgecount() {
		return judgecount;
	}

	public void setJudgecount(int judgecount) {
		this.judgecount = judgecount;
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
	
	@Json(strategy = Json.webStrategy)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
