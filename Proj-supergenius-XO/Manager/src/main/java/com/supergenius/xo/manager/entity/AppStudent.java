package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EAppStudent;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.user.entity.User;

/**
 * 学员申请实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午12:02:41
 */
@Json(value = {"uid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class AppStudent extends BaseEntity {
	
	private static final long serialVersionUID = 3370840352517677428L;
	private String useruid;// 会员uid
	@Json(strategy = Json.webStrategy)
	private String usersn;// 会员号
	@Json(strategy = Json.webStrategy)
	private EMajor major;// 专业
	@Json(strategy = Json.webStrategy)
	private String semester;// 年度学期
	private EAppStudent type;// 申请表类别，是职业经理人培训还是企业家培训
	private double money;// 金额
	private String reason;// 审批理由
	//以下字段只用作页面显示，不保存数据库
	private User user;//相关用户
	@Json(strategy = Json.webStrategy)
	private String workdaytime;// 工作日禁止联系时间,回车分割
	@Json(strategy = Json.webStrategy)
	private String saturdaytime;// 周末禁止联系时间,回车分割

	public AppStudent() {
		super();
	}

	public AppStudent(String useruid, String usersn, EMajor major, EAppStudent type, double money) {
		super();
		this.useruid = useruid;
		this.usersn = usersn;
		this.major = major;
		this.type = type;
		this.money = money;
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

	public EMajor getMajor() {
		return major;
	}

	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return major.getName();
	}
	
	public void setMajor(EMajor major) {
		this.major = major;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public EAppStudent getType() {
		return type;
	}

	public void setType(EAppStudent type) {
		this.type = type;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getGenderName() {
		if (this.user.getGender() != null)
			return this.user.getGender().getName();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getEmail() {
		if (this.user.getEmail() != null) 
			return this.user.getEmail();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserName() {
		return this.user.getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getStatusName() {
		return this.status.getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getNickname() {
		if (this.user.getNickname() != null) 
			return this.user.getNickname();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getDepartment() {
		if (this.user.getDepartment() != null) 
			return this.user.getDepartment();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getJob() {
		if (this.user.getJob() != null) 
			return this.user.getJob();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getSummary() {
		if (this.user.getSummary() != null) 
			return this.user.getSummary();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getCompany() {
		if (this.user.getCompany() != null)
			return this.user.getCompany();
		return null;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getIdentityid() {
		if (this.user.getIdentityid() != null)
			return this.user.getIdentityid();
		return null;
	}
	
	public String getWorkdaytime() {
		return workdaytime;
	}

	public void setWorkdaytime(String workdaytime) {
		this.workdaytime = workdaytime;
	}

	public String getSaturdaytime() {
		return saturdaytime;
	}

	public void setSaturdaytime(String saturdaytime) {
		this.saturdaytime = saturdaytime;
	}

}
