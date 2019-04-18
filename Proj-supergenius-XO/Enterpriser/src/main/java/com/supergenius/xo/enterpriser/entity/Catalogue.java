package com.supergenius.xo.enterpriser.entity;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;


/**
 * 目录模块实体类
 * 
 * @author loupengyu
 * @date 2018年1月29日11:35:56
 */
@Json(value = {"status", "createtime", "updatetime", "cid", "pcid", "name", "data", "adminname", "adminuid", "isspecial"}, ignoreTypeStrategy = Json.webStrategy, strategy = {Json.webStrategy, Json.cacheStrategy})
public class Catalogue extends BaseEntity {

	private static final long serialVersionUID = -4640791860397985121L;
	private int cid; //模块的cid
	private int pcid; //父模块的cid
	private String name; //模块名称
	private String content; //'相关的其他json数据'
	private String adminuid; //管理员uid
	
	// 下面属性不存在数据库中，封装为了使用
	private String adminname; //管理员姓名
	
	public int getPcid() {
		return pcid;
	}

	public void setPcid(int pcid) {
		this.pcid = pcid;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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
}
