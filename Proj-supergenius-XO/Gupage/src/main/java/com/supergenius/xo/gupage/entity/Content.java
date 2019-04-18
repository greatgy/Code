package com.supergenius.xo.gupage.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 内容实体类
 * 
 * @author loupengyu
 * @date 2018年1月10日10:54:42
 */
@Json(value = { "uid","name","adminname", "data", "adminuid", "status", "type", "createtime", "originurl" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Content extends BaseEntity {

	private static final long serialVersionUID = 6278739981931916365L;

	private String name;// 名字
	private int type; // 类型(广告位|其他)
	private String data;// 以json的方式记录
	private String summary;// 简介
	private String content;// 详情
	private String originurl;// 链接
	private String adminuid;// 操作人uid
	private String adminname;// 操作人name

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
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
}
