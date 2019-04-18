package com.supergenius.xo.enterpriser.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.JsonUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 文章实体类
 * 
 * @author ChenQi
 * @date 2018年1月29日17:46:04
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid", "adminuid", "authoruid", "author", "content", "title", "imglittle", "imgmedium", "imgbig", "imgoriginal", "origin",
		"summary", "istop","data", "toptime", "type", "contact", "adminname", "keywords","isrecommend", "isoriginal", "kind",
		"cataloguename" }, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminuid", "authoruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight" })
public class Article extends BaseEntity implements Comparable<Article> {

	private static final long serialVersionUID = 3525857126789211085L;
	private int cid;
	private String adminuid;
	private String authoruid;
	private String author;
	private String content;
	private String title; // 标题
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String origin; // 来源
	private String summary; // 摘要内容
	private String keywords;// SEO关键词
	private String contact;// 联系方式
	private String data;// json字符串，主要存上传附件的名称和路径
	private int isoriginal;//0 非原创  1 原创
	private int isrecommend;//是否推荐(是否在首页中显示)
	private int kind; // 类型(0 文章 1 视频 2 图片)
	private int istop; // 置顶(设为焦点文章)
	private int type; // 类型(0 未发布 1 已发布)
	private DateTime toptime; // 置顶时间

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String cataloguename; // 模块名称
	private int clickcount;
	private int prizecount;
	private int commentscount;
	private int collectcount;
	private float weight;// 权重值
	private int ismember;// 判断是否是会员

	
	public String getData() {
		return data;
	}
	/**
	 * 保存附件
	 * @param list
	 */
	public void setData_accessory(List<Object> list) {
		Map<String, Object> map =new HashMap<>();
		map.put(MapperDict.accessory, list);
		this.data = JsonUtil.toJson(map);
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public int getIsoriginal() {
		return isoriginal;
	}

	public void setIsoriginal(int isoriginal) {
		this.isoriginal = isoriginal;
	}
	
	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public String getAuthoruid() {
		return authoruid;
	}

	public void setAuthoruid(String authoruid) {
		this.authoruid = authoruid;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
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

	public int getIstop() {
		return istop;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	@Json(strategy = Json.webStrategy)
	public int getIsmember() {
		return this.ismember;
	}

	public void setIsmember(int ismember) {
		this.ismember = ismember;
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
