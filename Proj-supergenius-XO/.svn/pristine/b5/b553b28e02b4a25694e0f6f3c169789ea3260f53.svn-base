package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.ESysEmailType;

/**
 * 文章类，并购动态和并购案例
 * 
 * @author LiuXiaoke
 */
@Maps(strategy=Maps.dbStrategy)
public class EmailTemplate extends BaseEntity {

	private static final long serialVersionUID = -2679326563934428480L;
	@Json(strategy = Json.webStrategy)
	private String title;// 标题
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private ESysEmailType type;//类型
	@Json(strategy = Json.webStrategy)
	private String typename;//模板名
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ESysEmailType getType() {
		return type;
	}

	public void setType(ESysEmailType type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String string) {
		this.typename = string;
	}
	
	
}
