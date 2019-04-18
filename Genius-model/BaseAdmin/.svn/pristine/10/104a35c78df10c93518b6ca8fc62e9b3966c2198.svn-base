package com.genius.model.baseadmin.entity;

import java.util.ArrayList;
import java.util.List;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.JsonIgnore;
import com.genius.model.base.entity.BaseEntity;

/**
 * @author Architect.bian
 * 
 */

@Json(value = {"uid"}, ignoreTypeStrategy = {Json.webStrategy, Json.keyValueStrategy}, strategy = Json.webStrategy)
@JsonIgnore(value = {"status", "getUpdatetimeStr", "getStatusName", "getCreatetimeStr"}, strategy = Json.keyValueStrategy)
public class Role extends BaseEntity {

	private static final long serialVersionUID = 972796495577121350L;

	@Json(strategy = Json.webStrategy)
	private String adminuid;
	@Json(strategy = Json.webStrategy)
	private String adminid;
	@Json(strategy = Json.allStrategy)
	private String roleid;
	@Json(strategy = Json.allStrategy, alias="name")
	private String rolename;
	@Json(strategy = Json.webStrategy)
	private String desc;
	
	private String[] authorities;
	private List<Authority> authorityList = new ArrayList<>();
	
	public Role() {}

	public Role(String adminuid, String adminid, String roleid) {
		this.adminuid = adminuid;
		this.adminid = adminid;
		this.roleid = roleid;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}
	
	public void setName(String name) {
		this.rolename = name;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public List<Authority> getAuthorityList() {
		if (this.authorityList.size() == 0 && this.authorities != null) {
			for (String auth : this.authorities) {
				this.authorityList.add(new Authority(this.getUid(), this.getRoleid(), auth));
			}
		}
		return this.authorityList;
	}

	public void set(Role t) {
		this.setName(t.getRolename());
		this.setDesc(t.getDesc());
		this.setAuthorities(t.getAuthorities());
	}
}
