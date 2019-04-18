package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMajor;

/**
 * 专业实体类
 * 
 * @author liubin
 * @date 2016-7-17 下午2:40:36
 */
@Json(value = {"uid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Major extends BaseEntity {

	private static final long serialVersionUID = -8700572257872654937L;
	private String summary;// 简介
	@Json(strategy = Json.webStrategy)
	private String content;// 详情
	@Json(strategy = Json.webStrategy)
	private EMajor type;// 类别对应一个枚举类型EMajor

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

	public EMajor getType() {
		return type;
	}

	@Json(strategy = Json.webStrategy)
	public String getTypename() {
		return type.getName();
	}
	
	public void setType(EMajor type) {
		this.type = type;
	}
	
	public String getTypeName() {
		return this.type.getName();
	}
	
	public String getTypeEnglishName() {
		return this.type.name();
	}
}
