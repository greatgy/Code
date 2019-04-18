package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EContent;

/**
 * 内容实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午2:43:24
 */
@Json(value = {"uid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Content extends BaseEntity {

	private static final long serialVersionUID = -8503372690829024257L;
	private String name;// 帮助的名字
	@Json(strategy = Json.webStrategy)
	private EContent type;// 帮助类型(职业经理人首页|学员培训细则)
	private String data;// 以json的方式记录
	private String summary;// 帮助简介
	@Json(strategy = Json.webStrategy)
	private String content;// 帮助详情

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EContent getType() {
		return type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypename() {
		return type.getName();
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
	
	
}
