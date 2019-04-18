package com.supergenius.xo.gupage.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 图片实体类
 * 
 * @author yangguang
 * @date 2018年1月10日10:19:45
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "summary", "data", "istop", "imglittle", "imgmedium", "imgbig", "imgoriginal", "adminuid"}, ignoreTypeStrategy = { Json.webStrategy }, strategy = { Json.webStrategy })
public class Photo extends BaseEntity {

	private static final long serialVersionUID = 7021656777578046662L;
	private String adminuid;
	private String summary; //
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String data; // 备用
	private int istop; // 置顶

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

}
