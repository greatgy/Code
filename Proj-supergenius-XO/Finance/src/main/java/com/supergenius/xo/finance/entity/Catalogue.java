package com.supergenius.xo.finance.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 目录实体类
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午2:04:52
 */
@Json(value = { "status", "createtime", "updatetime", "cid", "name", "data", "adminname" }, ignoreTypeStrategy = Json.webStrategy, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Catalogue extends BaseEntity {

	private static final long serialVersionUID = -5657738991329325473L;
	private int cid; // 模块的cid
	private String name; // 模块名称
	private String data; // 相关的其他json数据
	private String adminuid; // 操作人UID

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; //管理员姓名
	
	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

}
