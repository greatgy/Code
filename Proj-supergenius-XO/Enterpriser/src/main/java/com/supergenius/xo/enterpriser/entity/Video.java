package com.supergenius.xo.enterpriser.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.enterpriser.rule.EnterpriserVideoClickCountRule;

/**
 * 视频实体类
 * 
 * @author yangguang
 * @date 2018年1月10日10:19:45
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "imglittle", "imgmedium", "imgbig", "imgoriginal", "adminuid",
		"title", "summary", "cid", "content", "data", "istop"}, ignoreTypeStrategy = { Json.webStrategy }, strategy = { Json.webStrategy })
public class Video extends BaseEntity {

	private static final long serialVersionUID = 7021656777578046662L;
	private String adminuid;
	private String title; // 标题
	private int cid;
	private String content; // 视频代码
	private String summary; // 视频描述
	private String keywords;// 视频标签
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String data; // 备用
	private int istop; // 置顶(设为焦点文章)

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public Long getClickcount() {
		Rule rule = new EnterpriserVideoClickCountRule(this.uid);
		return RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
	}
}
