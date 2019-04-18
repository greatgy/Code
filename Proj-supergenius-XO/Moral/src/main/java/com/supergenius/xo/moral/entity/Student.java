package com.supergenius.xo.moral.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.ELevel;

/**
 * 学员
 * @author liushaomin
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Student extends BaseEntity{

	private static final long serialVersionUID = -4545760402938852305L;
	@Json(strategy = Json.webStrategy)
	private String name;
	@Json(strategy = Json.webStrategy)
	private String useruid;//会员uid
	@Json(strategy = Json.webStrategy)
	private String sn;//学员学号
	@Json(strategy = Json.webStrategy)
	private ELevel level;//等级（头衔）
	@Json(strategy = Json.webStrategy)
	private Double score;//考试成绩
	@Json(strategy = Json.webStrategy)
	private Certificate certificate;//证书
	@Json(strategy = Json.webStrategy)
	private String address;//常用地址
	@Json(strategy = Json.webStrategy)
	private String mobile;//联系电话
	@Json(strategy = Json.webStrategy)
	private String email;//常用邮箱
	@Json(strategy = Json.webStrategy)
	private String qq;//qq
	@Json(strategy = Json.webStrategy)
	private UserStatistics userStatistics;
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getName() {
		return name;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setName(String name) {
		this.name = name;
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
	
	public ELevel getLevel() {
		return level;
	}

	public void setLevel(ELevel level) {
		this.level = level;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getLevelName() {
		return ELevel.getName(level, Locale.CHINA);
	}

	public Double getScore() {
		return score;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public Certificate getCertificate() {
		return certificate;
	}
	
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public UserStatistics getUserStatistics() {
		return userStatistics;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setUserStatistics(UserStatistics userStatistics) {
		this.userStatistics = userStatistics;
	}

	/**
	 * 清除xss
	 * @author liushaomin
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.address != null) {
			this.address = WebUtil.clearXSS(this.address);
		}
		if (this.mobile != null) {
			this.mobile = WebUtil.clearXSS(this.mobile);
		}
		if (this.email != null) {
			this.email = WebUtil.clearXSS(this.email);
		}
		if (this.qq != null) {
			this.qq = WebUtil.clearXSS(this.qq);
		}
	}
}
