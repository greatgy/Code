package com.supergenius.xo.life.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.life.rule.LifeTopicCommentCountRlue;
import com.supergenius.xo.life.rule.LifeTopicPartyCountRlue;
import com.supergenius.xo.life.rule.LifeTopicPrizeCountRlue;
import com.supergenius.xo.life.rule.LifeTopicRlue;

/**
 * 话题实体类
 * 
 * @author ChenQi
 * @date 2018年5月9日16:03:26
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid", "adminuid", "useruid", "author", "content", "title", "imglittle", "imgmedium", "imgbig", "imgoriginal", "origin",
		"summary", "istop", "toptime", "examine", "type", "adminname", "keywords", "isoriginal", "kind",
		"cataloguename","partynum" }, ignoreTypeStrategy = { Json.webStrategy}, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminuid", "useruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight" })
public class Topic extends BaseEntity implements Comparable<Topic> {

	private static final long serialVersionUID = 805601317722678260L;
	private long cid;
	private String adminuid;
	private String useruid;
	private String author;
	private String content;
	private String title; // 标题
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String origin; // 来源
	private String summary; // 摘要内容
	private String keywords;// seo搜索关键字
	private int isoriginal;//0 非原创  1 原创
	private int examine;//0未通过审核  1 通过审核
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
	private long partycount;// 参与人数

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

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
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

	public int getPrizecounts() {
		Rule rule = new LifeTopicRlue(this.uid);
		return RedisUtil.getHInt(rule, "prizecount");
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
	
	public int getExamine() {
		return examine;
	}

	public void setExamine(int examine) {
		this.examine = examine;
	}

	@Json(strategy = Json.webStrategy)
	public long getPartycount() {
		Rule rule1 = new LifeTopicPrizeCountRlue(this.uid);
		long admirecount = RedisUtil.getIncr(rule1) > 0 ? RedisUtil.getIncr(rule1) : 0;
		Rule rule2 = new LifeTopicCommentCountRlue(this.uid);
		long commentcount = RedisUtil.getIncr(rule2) > 0 ? RedisUtil.getIncr(rule2) : 0;
		Rule rule3 = new LifeTopicPartyCountRlue(this.uid);
		long prizecommentcount = RedisUtil.getIncr(rule3) > 0 ? RedisUtil.getIncr(rule3) : 0;
		this.partycount = admirecount + commentcount + prizecommentcount;
		return this.partycount;
	}

	public void setPartycount(long partycount) {
		this.partycount = partycount;
	}

	@Override
	public int compareTo(Topic o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Topic) {
			Topic topic = (Topic) o;
			if (weight > topic.getWeight()) {
				return -1;
			} else if (weight == topic.getWeight()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
}