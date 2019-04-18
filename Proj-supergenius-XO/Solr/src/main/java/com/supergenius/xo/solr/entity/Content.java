package com.supergenius.xo.solr.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.solr.enums.EContent;

/**
 * 内容实体类
 * 
 * @author ChenQi
 * @date 2017年8月23日10:46:08
 */
@Json(value = { "uid", "name", "data", "content", "adminname", "type", "originurl", "status", "createtime",
		"updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Content extends BaseEntity {

	private static final long serialVersionUID = -18608029222140021L;
	
	private String name;// 名字
	private EContent type;// 类型(广告位|其他)
	private String data;// 以json的方式记录
	private String summary;// 简介
	private String content;// 详情
	private String originurl;// 链接
	private String adminuid;// 操作人UID

	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EContent getType() {
		return type;
	}

	public void setType(EContent type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginurl() {
		return originurl;
	}

	public void setOriginurl(String originurl) {
		this.originurl = originurl;
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

}
