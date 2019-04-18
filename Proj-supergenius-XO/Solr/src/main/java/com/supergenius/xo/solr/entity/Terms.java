package com.supergenius.xo.solr.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 搜索词实体类
 * 
 * @author yangguang
 * @date 2017年11月2日18:02:35
 */
@Json(value = {"uid", "status", "createtime", "content", "count",}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Terms extends BaseEntity {

	private static final long serialVersionUID = 3397718897074619822L;
	private String content;// 词语的内容
	private Long count;// 点击和搜索数量

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
