package com.supergenius.xo.startup.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 创业平台文章实体类
 * 
 * @author 许志翔
 * @date 2017年8月23日11:35:41
 */
@Json(value = { "uid", "status", "updatetime", "keywords", "createtime", "cid", "adminuid", "authoruid", "author", "content",
		"title", "imglittle", "imgmedium", "imgbig", "imgoriginal", "origin", "summary", "istop", "istoptime", "type", "isflash",
		"isoriginal", "adminname",
		"cataloguename" }, ignoreTypeStrategy = {Json.webStrategy, Json.cacheStrategy}, strategy = {Json.webStrategy, Json.cacheStrategy})
@Maps(strategy={Maps.searchStrategy})
@MapsIgnore(strategy=Maps.searchStrategy, value={"adminuid", "authoruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight","today","week","month"})
public class Article extends BaseEntity implements Comparable<Article> {

	private static final long serialVersionUID = -8352769693089672642L;
	private int cid; // 模块的uid
	private String adminuid; // 管理员uid
	private String authoruid; // 作者uid
	private String author; // 作者名字
	private String keywords;//关键字
	private String content; // 内容
	private String title; // 标题
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String origin; // 来源
	private String summary; // 摘要内容
	private int istop; // 置顶(设为焦点文章)
	private int type; // 类型(0 文章 1 视频 2 图片)
	private int isflash;//是否快讯(0 否 1 是)
	private int isoriginal; // 是否原创(0 否 1 是)
	private DateTime toptime; // 置顶时间

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String cataloguename; // 模块名称
	private int clickcount;
	private int prizecount;
	private int commentscount;
	private int collectcount;
	private float weight;// 权重值
	private double today;// 权重值
	private double week;// 权重值
	private double month;// 权重值

	public double getToday() {
		return today;
	}

	public void setToday(double today) {
		this.today = today;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public double getWeek() {
		return week;
	}

	public void setWeek(double week) {
		this.week = week;
	}

	public double getMonth() {
		return month;
	}

	public void setMonth(double month) {
		this.month = month;
	}

	public int getIsflash() {
		return isflash;
	}

	public void setIsflash(int isflash) {
		this.isflash = isflash;
	}

	public int getCid() {
		return cid;
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

	public String getAuthoruid() {
		return authoruid;
	}

	public void setAuthoruid(String authoruid) {
		this.authoruid = authoruid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public DateTime getToptime() {
		if (toptime == null) {
			toptime = new DateTime(DateTimeZone.forOffsetHours(8));
		}
		return toptime.toDateTime(DateTimeZone.forOffsetHours(8));
	}

	public void setToptime(DateTime toptime) {
		this.toptime = toptime;
	}

	public int getIsoriginal() {
		return isoriginal;
	}

	public void setIsoriginal(int isoriginal) {
		this.isoriginal = isoriginal;
	}

	public int getIstop() {
		return istop;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	public boolean getIsprize(){
		return this.isprize;
	}
	
	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIscollect(){
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
	
	public int compareWithToday(Article o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Article) {
			Article article = (Article) o;
			if (today > article.getToday()) {
				return -1;
			} else if (today == article.getToday()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
	
	public int compareWithWeek(Article o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Article) {
			Article article = (Article) o;
			if (week > article.getWeek()) {
				return -1;
			} else if (week == article.getWeek()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
	
	public int compareWithMonth(Article o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Article) {
			Article article = (Article) o;
			if (month > article.getMonth()) {
				return -1;
			} else if (month == article.getMonth()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
}
