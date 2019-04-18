package com.genius.model.baseadmin.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.JsonIgnore;
import com.genius.model.base.entity.BaseEntity;

@Json(value = {"uid"}, ignoreTypeStrategy = {Json.webStrategy, Json.keyValueStrategy}, strategy = Json.webStrategy)
@JsonIgnore(value = {"status", "getUpdatetimeStr", "getStatusName", "getCreatetimeStr"}, strategy = Json.keyValueStrategy)
public class Authority extends BaseEntity {

	private static final long serialVersionUID = -5311925550058902281L;
	
	private String roleuid;
	private String roleid;
	@Json(strategy = Json.keyValueStrategy)
	private String url;
	private String desc;
	private String name;
	
	private Role role;

	public Authority() {}
	
	public Authority(String roleuid, String roleid, String url) {
		this.roleuid = roleuid;
		this.roleid = roleid;
		this.url = url;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleuid() {
		return roleuid;
	}

	public void setRoleuid(String roleuid) {
		this.roleuid = roleuid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
