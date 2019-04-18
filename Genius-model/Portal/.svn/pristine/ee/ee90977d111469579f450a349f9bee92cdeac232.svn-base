package com.genius.model.portal.entity;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.FileUtil;
import com.genius.model.base.entity.BaseEntity;

/**
 * @author Architect.bian
 * 
 */
@Json(value = {"oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Content extends BaseEntity {

	private static final long serialVersionUID = -8305260019761051322L;

	private String type;
	@Json(strategy = Json.webStrategy)
	private String adminuid;
	@Json(strategy = Json.webStrategy)
	private String title;
	private String titleshort;
	private String titleimg;//标题图
	@Json(strategy = Json.webStrategy)
	private String imglittle;//小图
	private String imgmedium;//中图
	private String imgbig;//大图
	private String imgoriginal;//原图
	private boolean isimgshow;
	@Json(strategy = Json.webStrategy)
	private String summary;
	@Json(strategy = Json.webStrategy)
	private String author;
	private String origin;
	private String originurl;
	@Json(strategy = Json.webStrategy)
	private String content;
	@Json(strategy = Json.webStrategy)
	private boolean istop = false;
	private int sortorder = 0;
	private String keywords;
	private String description;
	
	public Content() {}
	
	public Content(int oid, String title, String content, String summary) {
		this.setOid(oid);
		this.title = title;
		this.content = content;
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	public String getImgoriginal() {
		return imgoriginal;
	}

	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
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

	public String getImgbig() {
		return imgbig;
	}

	public void setImgbig(String imgbig) {
		this.imgbig = imgbig;
	}

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

	public boolean isIsimgshow() {
		return isimgshow;
	}

	public void setIsimgshow(boolean isimgshow) {
		this.isimgshow = isimgshow;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void set(Content t) {
		this.setTitle(t.getTitle());
		this.setTitleshort(t.getTitleshort());
		this.setTitleimg(t.getTitleimg());
		if (StringUtils.isNotEmpty(t.getImgoriginal())) {
			this.setImgoriginal(t.getImgoriginal());
			this.setImgbig(t.getImgbig());
			this.setImgmedium(t.getImgmedium());
			this.setImglittle(t.getImglittle());
		}
		this.setIsimgshow(t.isIsimgshow());
		this.setSummary(t.getSummary());
		this.setAuthor(t.getAuthor());
		this.setOrigin(t.getOrigin());
		this.setOriginurl(t.getOriginurl());
		this.setContent(t.getContent());
		this.setIstop(t.isIstop());
		this.setSortorder(t.getSortorder());
		this.setKeywords(t.getKeywords());
		this.setDescription(t.getDescription());
	}

	public void setImgs(String[] files) {
		if (files != null && files.length > 0) {
			FileUtil.deleteImg(this.imgoriginal);
			FileUtil.deleteImg(this.imgbig);
			FileUtil.deleteImg(this.imgmedium);
			FileUtil.deleteImg(this.imglittle);
		}
		if (files.length == 4) {
			this.imgoriginal = files[0];
			this.imgbig = files[1];
			this.imgmedium = files[2];
			this.imglittle = files[3];
		}
	}
}
