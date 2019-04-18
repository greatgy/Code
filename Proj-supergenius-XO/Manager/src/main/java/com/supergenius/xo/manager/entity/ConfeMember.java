package com.supergenius.xo.manager.entity;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;

/**
 * 会议室成员实体类
 * @author XieMing
 * @date 2016-7-17 下午12:04:59
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class ConfeMember extends BaseEntity {

	private static final long serialVersionUID = 795453688754496668L;
	private String useruid;//用户的UID
	private String confuid;//会议室UID
	private String confsn;//会议室编号
	private String cid;//会议室ID
	private String token;//用户token
	private String pkuid;//挑战UID
	private String pkname;//挑战名称
	private EConfer type;//用户类型,挑战双方、裁判、观众、主裁判
	private String userid;//用户id
	private String username;//会员名
	private String useralias;//用户昵称
	private String password;//密码
	private String email;//用户邮件
	private String phone;//用户电话
	private boolean isjoin;//是否已加入会议室
	private String args;//会议室参数，回车分割
	private EConfemember usertype;//视高的用户类型,user/tourist/spokesman/manager
	private DateTime jointimefrom;//加入会议室的开始时间
	private DateTime jointimeto;//加入会议室的结束时间
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getConfuid() {
		return confuid;
	}
	
	public void setConfuid(String confuid) {
		this.confuid = confuid;
	}
	
	public String getConfsn() {
		return confsn;
	}
	
	public void setConfsn(String confsn) {
		this.confsn = confsn;
	}
	
	public String getCid() {
		return cid;
	}
	
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getPkuid() {
		return pkuid;
	}
	
	public void setPkuid(String pkuid) {
		this.pkuid = pkuid;
	}
	
	public String getPkname() {
		return pkname;
	}
	
	public void setPkname(String pkname) {
		this.pkname = pkname;
	}
	
	public EConfer getType() {
		return type;
	}
	
	public void setType(EConfer type) {
		this.type = type;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUseralias() {
		return useralias;
	}
	
	public void setUseralias(String useralias) {
		this.useralias = useralias;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isIsjoin() {
		return isjoin;
	}
	
	public void setIsjoin(boolean isjoin) {
		this.isjoin = isjoin;
	}
	
	public String getArgs() {
		return args;
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	public EConfemember getUsertype() {
		return usertype;
	}
	
	public void setUsertype(EConfemember usertype) {
		this.usertype = usertype;
	}
	
	public DateTime getJointimefrom() {
		return jointimefrom;
	}
	
	public void setJointimefrom(DateTime jointimefrom) {
		this.jointimefrom = jointimefrom;
	}
	
	public DateTime getJointimeto() {
		return jointimeto;
	}
	
	public void setJointimeto(DateTime jointimeto) {
		this.jointimeto = jointimeto;
	}
	
}
