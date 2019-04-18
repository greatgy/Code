package com.supergenius.xo.user.entity;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EContent;

/** 
 * 内容实体
 * @author guanshiqian
 * @date 2016-4-15 下午3:55:11
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Content extends BaseEntity {
	
	private static final long serialVersionUID = -2679326563934428480L;
	@Json(strategy = Json.webStrategy)
	private String name;//名字
	@Json(strategy = Json.webStrategy)
	private EContent type;//类型
	private String summary;//简介
	@Json(strategy = Json.webStrategy)
	private String content;//内容
	@Json(strategy = Json.webStrategy)
	private String title;//标题
	private String titleshort;//短标题
	private String imgtitle;//标题图片
	private String imglittle;//小图
	private String imgmedium;//中图
	private String imgbig;//大图
	private String imgoriginal;//原图
	private String data;//以json的方式记录
	
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
	public String getTypeName() {
		if(this.type == null) {
			return null;
		} else {
			return this.type.getName();
		}
	}
	
	public void setType(EContent type) {
		this.type = type;
	}
	
	public void setTypeName(String name) {
		if(StringUtils.isNotEmpty(name)) {
			this.type = EContent.get(Integer.parseInt(name));
		}
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitleshort() {
		return titleshort;
	}
	
	public void setTitleshort(String titleshort) {
		this.titleshort = titleshort;
	}
	
	public String getImgtitle() {
		return imgtitle;
	}
	
	public void setImgtitle(String imgtitle) {
		this.imgtitle = imgtitle;
	}
	
	public String getImglittle() {
		return imglittle;
	}
	
	public void setImglittle(String imglittle) {
		this.imglittle = imglittle;
	}
	
	public String getImgmedium() {
		return imgmedium;
	}
	
	public void setImgmedium(String imgmedium) {
		this.imgmedium = imgmedium;
	}
	
	public String getImgbig() {
		return imgbig;
	}
	
	public void setImgbig(String imgbig) {
		this.imgbig = imgbig;
	}
	
	public String getImgoriginal() {
		return imgoriginal;
	}
	
	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
