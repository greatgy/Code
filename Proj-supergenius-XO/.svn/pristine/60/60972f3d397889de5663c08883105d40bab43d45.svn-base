package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/**
 * 证书明细实体类
 * 
 * @author XieMing
 * @date 2016-7-17 下午12:04:29
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value = { "uid" })
public class Certificate extends BaseEntity {

	private static final long serialVersionUID = 2416189379098476873L;
	private String useruid;// UserUID
	private String sn;// 证书编号
	private String refuid;// refUID相关uid
	private EMajor major;// 专业
	private ECertificate type;// 证书类型（裁判（普通|特批|特聘|专职）、专家（普通|特批|特聘|专职）、高级专家、特级专家、RMBA\SMBA\TMBA）
	private String imglittle;// 证书小图
	private String img;// 证书中图
	private String imgbig;// 证书大图
	@Json(strategy = Json.webStrategy)
	private String imgoriginal;// 证书原图
	private String desc;// 描述
	//以下为相关字段   不存入数据库
	private User user;//相关User
	private String certificatename;//证书名称
	private String studentsn;//学员号
	
	public Certificate() {
		super();
	}

	public Certificate(String useruid, String sn, String refuid, EMajor major, ECertificate type) {
		super();
		this.useruid = useruid;
		this.sn = sn;
		this.refuid = refuid;
		this.major = major;
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getStudentsn() {
		return studentsn;
	}

	public void setStudentsn(String studentsn) {
		this.studentsn = studentsn;
	}

	@Json(strategy = Json.webStrategy)
	public String getCertificatename() {
		return certificatename;
	}

	public void setCertificatename(String certificatename) {
		this.certificatename = certificatename;
	}

	@Json(strategy = Json.webStrategy)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return getMajor().getName();
	}
	
	public EMajor getMajor() {
		return major;
	}

	public void setMajor(EMajor major) {
		this.major = major;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return getType().getName();
	}

	public ECertificate getType() {
		return type;
	}

	public void setType(ECertificate type) {
		this.type = type;
	}

	@Json(strategy = Json.webStrategy)
	public String getImglittle() {
		return imglittle;
	}

	public void setImglittle(String imglittle) {
		this.imglittle = imglittle;
	}

	@Json(strategy = Json.webStrategy)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Json(strategy = Json.webStrategy)
	public String getImgbig() {
		return imgbig;
	}

	public void setImgbig(String imgbig) {
		this.imgbig = imgbig;
	}
	
	public String getImgoriginal() {
		return imgoriginal;
	}

	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
