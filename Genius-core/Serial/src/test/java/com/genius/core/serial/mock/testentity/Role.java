package com.genius.core.serial.mock.testentity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.JsonIgnore;

@Json(value = {"uid"}, ignoreTypeStrategy = {Json.webStrategy, Json.keyValueStrategy}, strategy = Json.allStrategy)
@JsonIgnore(value = {"getUpdatetimeStr", "getStatusName", "getCreatetimeStr"}, strategy = Json.keyValueStrategy)
public class Role extends BaseEntity {

	private static final long serialVersionUID = 972796495577121350L;

	@Json(strategy = Json.webStrategy)
	private String adminid;
	@Json(strategy = Json.webStrategy)
	private String authority;
	@Json(strategy = Json.allStrategy, alias="name")
	private String authorityname;
	@Json(strategy = Json.webStrategy)
	private String desc;
	
	public Role() {
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthorityname() {
		return authorityname;
	}

	public void setAuthorityname(String authorityname) {
		this.authorityname = authorityname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}