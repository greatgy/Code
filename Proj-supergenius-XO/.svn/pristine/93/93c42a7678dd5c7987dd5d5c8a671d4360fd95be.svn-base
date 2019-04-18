package com.supergenius.xo.official.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.official.enums.EType;

/**
 * 网站内容
 * @author LiuXiaoke
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy=Maps.dbStrategy)
public class Content extends BaseEntity {
	
	private static final long serialVersionUID = -2679326563934428480L;
	@Json(strategy = Json.webStrategy)
    private EType type;//类型
	@Json(strategy = Json.webStrategy)
    private String adminid;//管理员id
	@Json(strategy = Json.webStrategy)
    private String title;//标题
	@Json(strategy = Json.webStrategy)
    private String titleimg;//标题图片
	@Json(strategy = Json.webStrategy)
    private String imglittle;//小图
	@Json(strategy = Json.webStrategy)
    private String imgmedium;//中图
	@Json(strategy = Json.webStrategy)
    private String imgbig;//大图
	@Json(strategy = Json.webStrategy)
    private String imgoriginal;//原图
	@Json(strategy = Json.webStrategy)
    private String summary;//摘要
	@Json(strategy = Json.webStrategy)
    private String content;//内容
	@Json(strategy = Json.webStrategy)
    private boolean istop;//是否置顶 
	@Json(strategy = Json.webStrategy)
    private String keyworlds;//关键字
	
	public EType getType() {
		return type;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsAboutus(){
		return this.type == EType.aboutus;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsLegal(){
		return this.type == EType.legal;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsContact(){
		return this.type == EType.contact;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsFriendlink(){
		return this.type == EType.friendlink;
	}
	
	public void setType(EType type) {
		this.type = type;
	}
	
	public String getAdminid() {
		return adminid;
	}
	
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitleimg() {
		return titleimg;
	}
	
	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
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
	
	public boolean isIstop() {
		return istop;
	}
	
	public void setIstop(boolean istop) {
		this.istop = istop;
	}
	
	public String getKeyworlds() {
		return keyworlds;
	}
	
	public void setKeyworlds(String keyworlds) {
		this.keyworlds = keyworlds;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 设置图片
	 * @param imgs
	 * @author LiuXiaoke
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setImgs(String[] imgs) {
		this.setImgoriginal(imgs[0]);
		this.setImgbig(imgs[1]);
		this.setImgmedium(imgs[2]);
		this.setImglittle(imgs[3]);
	}
	
	/**
	 * 获取枚举中文名
	 * @return
	 * @author LiuXiaoke
	 */
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTypeName() {
		return EType.getName(type, Locale.CHINA);
	}
	
}
