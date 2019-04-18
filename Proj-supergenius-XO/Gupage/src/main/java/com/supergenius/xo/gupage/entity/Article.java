package com.supergenius.xo.gupage.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 文章实体类
 * 
 * @author yangguang
 * @date 2017年11月13日15:41:20
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid", "adminuid", "content", "title", "imglittle", "imgmedium", "imgbig", "imgoriginal",
		"summary", "adminname", "kind", "keywords"}, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminuid", "useruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight" })
public class Article extends BaseEntity implements Comparable<Article> {

	private static final long serialVersionUID = 7021656777578046662L;
	private int cid;
	private String adminuid;
	private String keywords;
	private String content;
	private String title; // 标题
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String summary; // 摘要内容
	private String data;//备用
	private int kind; // 类型(0 文章 1 视频 2 图片)

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String cataloguename; // 模块名称
	private int clickcount;
	private int prizecount;
	private int commentscount;
	private int collectcount;
	private float weight;// 权重值

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getCid() {
		return cid;
	}
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
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

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

	public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}

	public int getPrizecount() {
		return prizecount;
	}

	public void setPrizecount(int prizecount) {
		this.prizecount = prizecount;
	}

	public int getCommentscount() {
		return commentscount;
	}

	public void setCommentscount(int commentscount) {
		this.commentscount = commentscount;
	}

	public int getCollectcount() {
		return collectcount;
	}

	public void setCollectcount(int collectcount) {
		this.collectcount = collectcount;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIsprize() {
		return this.isprize;
	}

	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIscollect() {
		return this.iscollect;
	}

	public void setIscollect(Boolean iscollect) {
		this.iscollect = iscollect;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int compareTo(Article o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Article) {
			Article article = (Article) o;
			if (weight > article.getWeight()) {
				return -1;
			} else if (weight == article.getWeight()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
}