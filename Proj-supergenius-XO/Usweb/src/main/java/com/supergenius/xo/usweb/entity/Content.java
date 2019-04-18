package com.supergenius.xo.usweb.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 内容实体类
 * @author Yong
 * @date 2018年12月07日11:04:55
 */
public class Content extends BaseEntity {
	
	private static final long serialVersionUID = 8262001373977340801L;
	
	@Json(strategy = Json.webStrategy)
	private String name;//内容名称
	@Json(strategy = Json.webStrategy)
	private String content;//内容

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
