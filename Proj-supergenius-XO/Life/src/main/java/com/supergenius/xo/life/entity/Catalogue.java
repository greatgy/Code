package com.supergenius.xo.life.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.ECatalogueType;

/**
 * 创业平台目录模块实体类
 * 
 * @author 许志翔
 * @date 2017年8月23日14:23:14
 */
@Json(value = {"status", "createtime", "updatetime", "cid", "pcid", "name", "data", "adminname"}, ignoreTypeStrategy = Json.webStrategy, strategy = {Json.webStrategy, Json.cacheStrategy})
public class Catalogue extends BaseEntity {

	private static final long serialVersionUID = 5084469089306907125L;
	private long cid; //模块的cid
	private long pcid; //父模块的cid 0表示是一级目录
	private String name; //模块名称
	private String data; //相关的其他json数据
	private String adminuid; //管理员uid
	private ECatalogueType type; //模块类别（0无类别，1文章，2话题，3视频）
	
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
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public long getPcid() {
		return pcid;
	}

	public void setPcid(long pcid) {
		this.pcid = pcid;
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

	public ECatalogueType getType() {
		return type;
	}

	public void setType(ECatalogueType type) {
		this.type = type;
	}

}
