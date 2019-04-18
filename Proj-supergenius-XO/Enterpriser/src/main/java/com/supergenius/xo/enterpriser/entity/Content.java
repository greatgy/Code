package com.supergenius.xo.enterpriser.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.enterpriser.enums.EContent;

/**
 * 内容实体
 * @author XieMing
 * @date 2016-10-24 下午4:20:01
 */
public class Content extends BaseEntity {
	
	private static final long serialVersionUID = 2404555917266665130L;
	@Json(strategy = Json.webStrategy)
	private int cid;
	@Json(strategy = Json.webStrategy)
	private String name;//内容名称
	@Json(strategy = Json.webStrategy)
	private EContent type;//内容类型(专题讲座|企业家培训)
	@Json(strategy = Json.webStrategy)
	private String summary;//简介
	@Json(strategy = Json.webStrategy)
	private String content;//内容
	@Json(strategy = Json.webStrategy)
	private String data;//以json的方式记录
	@Json(strategy = Json.webStrategy)
	private String originurl;//
	@Json(strategy = Json.webStrategy)
	private String adminuid;//操作人uid
	
	//下面数据不存于数据库 只为方便使用
	@Json(strategy = Json.webStrategy)
	private String adminname;//操作人name
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	
	public String getOriginurl() {
		return originurl;
	}

	public void setOriginurl(String originurl) {
		this.originurl = originurl;
	}

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
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

}