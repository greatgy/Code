package com.supergenius.xo.life.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 图片实体类
 * 
 * @author ChenQi
 * @date 2018年5月11日11:40:52
 */
@Json(value = { "uid", "status", "updatetime", "oid", "cid", "createtime", "title", "content", "url"}, ignoreTypeStrategy = { Json.webStrategy }, strategy = { Json.webStrategy })
public class Photo extends BaseEntity {

	private static final long serialVersionUID = 7021656777578046662L;
	private String title; // 标题
	private String content; // 图片路径
	private String url; // 图片链接地址
	private long cid; // 模块cid

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	
}
