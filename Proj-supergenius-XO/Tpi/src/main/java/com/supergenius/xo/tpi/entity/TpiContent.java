package com.supergenius.xo.tpi.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EContent;

/**
 * 网站内容
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class TpiContent extends BaseEntity {
	
	private static final long serialVersionUID = -4105123546655552146L;
	
	private EContent type;
	private String adminuid;
	@Json(strategy = Json.webStrategy)
	private String title;
	private String titleshort;
	private String imglittle;//小图
	private String imgmedium;//中图
	private String imgbig;//大图
	private String imgoriginal;//原图
	private boolean isimgshow ;
	@Json(strategy = Json.webStrategy)
	private String summary;
	private String author;
	private String origin;
	private String originurl;
	@Json(strategy = Json.webStrategy)
	private String content;
	private boolean istop = false;
	private int sortorder = 0;
	
	public EContent getType() {
		return type;
	}
	
	public void setType(EContent type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EContent.getName(type, Locale.CHINA);
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
	
	public String getTitleshort() {
		return titleshort;
	}
	
	public void setTitleshort(String titleshort) {
		this.titleshort = titleshort;
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
	
	public boolean isIsimgshow() {
		return isimgshow;
	}
	
	public void setIsimgshow(boolean isimgshow) {
		this.isimgshow = isimgshow;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getOriginurl() {
		return originurl;
	}
	
	public void setOriginurl(String originurl) {
		this.originurl = originurl;
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
	
	public int getSortorder() {
		return sortorder;
	}
	
	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}

	/**
	 * @param tpiContent
	 * @author liushaomin
	 */
	public void set(TpiContent tpiContent) {
		this.title = tpiContent.getTitle();
		this.content = tpiContent.getContent();
	}
	
	public void clearXSS() {
		this.title = WebUtil.clearXSS(this.title);
		this.summary = WebUtil.clearXSS(this.summary);
		this.author = WebUtil.clearXSS(this.author);
		this.origin = WebUtil.clearXSS(this.origin);
		this.originurl = WebUtil.clearXSS(this.originurl);
		this.content = WebUtil.clearXSS(this.content);
	}

}
