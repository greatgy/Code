package com.genius.core.cache.mock.testentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.JsonIgnore;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.cache.mock.testenums.EGender;
import com.genius.core.cache.mock.testenums.EStatus;

/**
 * @author Architect.bian
 * 
 */
@Component
@Scope("prototype")
@JsonIgnoreProperties(value = { "oid", "tid", "toid", "createtime", "updatetime", "date", "time", "clickcount", "commentcount", "prizecount", "sharecount", "click"
		,"genderName", "isInit", "isEnable", "isDisable", "isStart", "isEnd", "isPayuserfee", "createtimeStr", "createDate"})
//@Json({"userid", "groupuid"})
@Json(strategy={Json.defaultStrategy, "forsave"}, ignoreTypeStrategy = "forsave")
@JsonIgnore(strategy={"forsave"}, value={"sharecount", "clickcount", "logincount"})
public class User extends BaseEntity {

	private static final long serialVersionUID = 4105665925642979854L;

	// @NotNull(message="valid.userid.null")
	private String userid;// 用户登录ID
	private String groupuid;// 群组UID
	private double account;// 账户余额
	private String nickname;
	private String pwd;
	@Json(strategy = Json.allStrategy)
	private String name;
	public EGender gender;// 性别
	private String realname;
	private LocalDate birthday;
	private String email;
	private String intro;// 个人简介
	private String thumb;// 缩略图
	private String avatar;// 个人头像
	private String original;// 原图
	private String signed;// 个性签名
	private String qq;
	private String msn;
	private String phone;
	private String mobile;
	private int salary;
	public EStatus status;// 是否激活等等状态
	private String resetpwd;// 重置密码VALUE
	private int logincount;// 登录次数
	private int msgcount;// 未读信息
	private DateTime errorlasttime;// 登录错误时间
	private int errorcount;// 登录错误次数
	private String errorip;// 登录错误IP
	private DateTime lastlogintime;// 最后登录时间
	private String lastloginip = "";// 最后登录IP
	private String registerip = "";// 注册IP

	List<String> childuids;
	List<String> friends;
	List<User> children = new ArrayList<>();

	public List<User> getChildren() {
		return children;
	}

	public void setChildren(List<User> children) {
		this.children = children;
	}

	public User() {
	}

	/**
	 * @param name
	 */
	public User(String name) {
		this.setUid(GlobalUtil.getUUID());
		this.name = name;
		this.setCreatetime(new DateTime());
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGroupuid() {
		return groupuid;
	}

	public void setGroupuid(String groupuid) {
		this.groupuid = groupuid;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EGender getGender() {
		return gender;
	}

	public String getGenderName() {
		return EGender.getName(gender, Locale.CHINA);
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getSigned() {
		return signed;
	}

	public void setSigned(String signed) {
		this.signed = signed;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public EStatus getStatus() {
		if (status == null) {
			status = EStatus.enable;
		}
		return status;
	}
	
	@Json(strategy = Json.allStrategy)
	public String getStatusName() {
		return EStatus.getName(status, Locale.CHINA);
	}

	// public void setStatus(int status) {
	// this.status = EStatus.get(status);
	// }
	public void setStatus(EStatus status) {
		this.status = status;
	}

	public String getResetpwd() {
		return resetpwd;
	}

	public void setResetpwd(String resetpwd) {
		this.resetpwd = resetpwd;
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public int getMsgcount() {
		return msgcount;
	}

	public void setMsgcount(int msgcount) {
		this.msgcount = msgcount;
	}

	public DateTime getErrorlasttime() {
		return errorlasttime;
	}

	public void setErrorlasttime(DateTime errorlasttime) {
		this.errorlasttime = errorlasttime;
	}

	public int getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(int loginErrCount) {
		this.errorcount = loginErrCount;
	}

	public String getErrorip() {
		return errorip;
	}

	public void setErrorip(String errorip) {
		this.errorip = errorip;
	}

	public DateTime getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(DateTime lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getRegisterip() {
		return registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}

	public List<String> getChilduids() {
		return childuids;
	}

	public void setChilduids(List<String> childuids) {
		this.childuids = childuids;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 对象的初始化
	 */
	public void initialize() {
		this.setPwd(DigestUtils.md5Hex(this.pwd));
		this.setStatus(EStatus.init);
		this.setAvatar("/imgs/default/defaulthead.jpg");
	}

}
