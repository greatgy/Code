package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;

/**
 * 存储一些简单的链接
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class Link {
	
	private String uid;
	@Json(strategy = Json.webStrategy)
	private String title; // 标题
	@Json(strategy = Json.webStrategy)
	private String origin;// 来源
	@Json(strategy = Json.webStrategy)
	private String desc;// 描述
	@Json(strategy = Json.webStrategy)
	private String href;// 链接
	@Json(strategy = Json.webStrategy)
	private String path;// 文件路径
	private int year;
	private int month;

	@Maps(strategy = "achivement")
	public String getTitle() {
		return title;
	}

	@Maps(strategy = "achivement")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Maps(strategy = "achivement")
	public String getHref() {
		return href;
	}

	@Maps(strategy = "achivement")
	public void setHref(String href) {
		this.href = href;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Maps(strategy = "achivement")
	public int getYear() {
		return year;
	}

	@Maps(strategy = "achivement")
	public void setYear(int year) {
		this.year = year;
	}

	@Maps(strategy = "achivement")
	public int getMonth() {
		return month;
	}

	@Maps(strategy = "achivement")
	public void setMonth(int month) {
		this.month = month;
	}

	@Maps(strategy = "achivement")
	public String getUid() {
		return uid;
	}

	@Maps(strategy = "achivement")
	public void setUid(String uid) {
		this.uid = uid;
	}
}
