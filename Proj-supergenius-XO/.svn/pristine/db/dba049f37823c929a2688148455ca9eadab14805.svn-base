package com.supergenius.xo.managernews.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 目录实体类
 * 
 * @author JIaShitao
 * @date 2018年7月3日09:46:30
 */
@Json(value = {"status", "createtime", "updatetime", "cid", "name", "data", "adminname"}, ignoreTypeStrategy = Json.webStrategy, strategy = {Json.webStrategy, Json.cacheStrategy})
public class Catalogue extends BaseEntity {

	private static final long serialVersionUID = 5084469089306907125L;
	private int cid; //模块的cid
	private String name; //模块名称
	private String data; //'存放模块的html代码'
	private String adminuid; //管理员uid
	
	// 下面属性不存在数据库中，封装为了使用
	private String adminname; //管理员姓名
	
	public String getAdminuid() {
		return adminuid;
	}
	
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	
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

}
