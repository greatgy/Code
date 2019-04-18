package com.supergenius.xo.gupage.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.gupage.enums.EPatent;

/**
 * 专利实体类
 * 
 * @author loupengyu
 * @date 2018年1月10日10:54:42
 */
@Json(value = { "uid", "data" , "title", "type", "filepath", "imglittle", "imgmedium", "imgbig", "imgoriginal", "kind", "adminuid", "status", "type", "createtime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Patent extends BaseEntity{

	private static final long serialVersionUID = 6331214638036370029L;

	private String title;// 标题
	private String filepath;// 文件路径
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private EPatent type;//类型
	private int kind;//图片还是文件
	private String data;//其他数据json格式
	private String adminuid;//操作人uid
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	public EPatent getType() {
		return type;
	}
	public void setType(EPatent type) {
		this.type = type;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getAdminuid() {
		return adminuid;
	}
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}