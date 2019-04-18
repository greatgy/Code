package com.supergenius.xo.enterpriser.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 报名实体类Register
 * 
 * @author liubin
 * @date 2016-10-24 下午5:05:39
 */
public class Participate extends BaseEntity {

	private static final long serialVersionUID = 1076006905829612076L;

	private String useruid; // 学员uid
	@Json(strategy = Json.webStrategy)
	private String usersn; // 学员号
	private String lectureuid; // 讲座uid
	@Json(strategy = Json.webStrategy)
	private String lecturename; // 讲座名称
	@Json(strategy = Json.webStrategy)
	private int semester; // 学期
	@Json(strategy = Json.webStrategy)
	private String name; // 姓名
	@Json(strategy = Json.webStrategy)
	private String mobile; // 手机
	@Json(strategy = Json.webStrategy)
	private String email; // 邮箱(用此字段判断是否已是会员，提示用户已是会员，不会赠送会员)
	@Json(strategy = Json.webStrategy)
	private String address; // 邮寄地址

	public Participate() {}
	
	public Participate(String useruid, String usersn, String lectureuid, String lecturename, int semester, String name, String mobile, String email, String address) {
		super();
		this.useruid = useruid;
		this.usersn = usersn;
		this.lectureuid = lectureuid;
		this.lecturename = lecturename;
		this.semester = semester;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}
	
	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getUsersn() {
		return usersn;
	}

	public void setUsersn(String usersn) {
		this.usersn = usersn;
	}

	public String getLectureuid() {
		return lectureuid;
	}

	public void setLectureuid(String lectureuid) {
		this.lectureuid = lectureuid;
	}

	public String getLecturename() {
		return lecturename;
	}

	public void setLecturename(String lecturename) {
		this.lecturename = lecturename;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
