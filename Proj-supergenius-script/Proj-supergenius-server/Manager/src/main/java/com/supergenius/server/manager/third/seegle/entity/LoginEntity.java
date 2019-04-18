package com.supergenius.server.manager.third.seegle.entity;

import java.io.Serializable;
/**
 * 
 * 会议登录参数
 * @author chenminchang
 * @createtime 2016-9-9上午10:49:25
 */
public class LoginEntity implements Serializable {

	private static final long serialVersionUID = -3477470224537494893L;
	private String rid;// 会议室真实ID
	private String ips;// 会议室集群服务器IP
	private String orgid;// 企业ID
	private String userid;// 自动分配的账号
	private String userpass;// 自动分配的密码
	private String ualias;// 别名

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUalias() {
		return ualias;
	}

	public void setUalias(String ualias) {
		this.ualias = ualias;
	}

	@Override
	public String toString() {
		return "LoginEntity [rid=" + rid + ", ips=" + ips + ", orgid=" + orgid + ", userid=" + userid + ", userpass=" + userpass + "]";
	}

}
