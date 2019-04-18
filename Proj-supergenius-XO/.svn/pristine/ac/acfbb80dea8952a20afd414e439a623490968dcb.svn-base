package com.supergenius.xo.official.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.official.enums.ERecruit;

/**
 * 招聘实体
 * 
 * @author liushaomin
 * @modifier lijiacheng
 */
@Maps(strategy = Maps.dbStrategy)
@Json(ignoreTypeStrategy = Json.webStrategy)
public class Recruit extends BaseEntity {

	private static final long serialVersionUID = 6645603861618628489L;
	@Json(strategy = Json.webStrategy)
	private String title;// 标题
	@Json(strategy = Json.webStrategy)
	private ERecruit type;// 岗位类别（财务、人力资源、行政、项目、质量、高级管理、IT、互联网、通信、金融、咨询、法律、教育、翻译、科研人员、实习生）
	@Json(strategy = Json.webStrategy)
	private String address;// 工作地址
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private boolean istop;// 是否置顶

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ERecruit getType() {
		return type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return ERecruit.getName(type, Locale.CHINA);
	}

	public void setType(ERecruit type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}
}
