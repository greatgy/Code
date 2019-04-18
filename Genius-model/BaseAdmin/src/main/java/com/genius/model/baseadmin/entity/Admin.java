package com.genius.model.baseadmin.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * @author Architect.bian
 * 
 */
@Json(value = {"uid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Admin extends BaseEntity {

	private static final long serialVersionUID = -5311925550058902281L;

	@Json(strategy = Json.webStrategy)
	private String adminid;
	@Json(strategy = Json.webStrategy)
	private String name;
	@Json(strategy = Json.webStrategy)
	private String email;
	private String pwd;
	private String dopwd;
	@Json(strategy = Json.webStrategy)
	private String mobile;
	
	private List<Role> roleList = new ArrayList<>();

	private String[] roles;

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}


	/**
	 * 判断密码匹配是否一致
	 * @param pwd
	 * @return
	 */
	public boolean isEqualPwd(String pwd) {
		return this.pwd.equals(DigestUtils.md5Hex(pwd));
	}
	
	public void initPwd(String pwd) {
		this.pwd = DigestUtils.md5Hex(pwd);
	}
	
	/**
	 * 重新设定操作密码
	 * @param pwd
	 */
	public void initDoPwd(String dopwd) {
		this.dopwd = DigestUtils.md5Hex(dopwd);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDopwd() {
		return dopwd;
	}

	public void setDopwd(String dopwd) {
		this.dopwd = dopwd;
	}
	
	/**
	 * 判断操作密码匹配是否一致
	 * @param pwd
	 * @return
	 * @author Architect.bian
	 * @createtime 2014-7-27 下午6:04:23
	 */
	public boolean isEqualDopwd(String dopwd) {
		return this.dopwd.equals(DigestUtils.md5Hex(dopwd));
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Role> getRoleList() {
		if (this.roleList.size() == 0 && this.roles != null) {
			for (String r : this.roles) {
				this.roleList.add(new Role(this.getUid(), this.adminid, r));
			}
		}
		return roleList;
	}
	
	public String[] getRoles() {
		return this.roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * 用从页面传递过来的参数重新赋值
	 * @param t
	 * @author Architect.bian
	 * @createtime 2014-7-25 下午9:12:30
	 */
	public void set(Admin t) {
		this.name = t.getName();
		this.email = t.getEmail();
		this.mobile = t.getMobile();
		this.setRoles(t.getRoles());
	}
}
