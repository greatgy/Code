package com.supergenius.xo.enterpriser.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 帖子实体类
 * 
 * @author ChenQi
 * @date 2018年1月29日17:46:04
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid", "istop","adminuid", "authoruid", "author", "content", "title", "summary", "toptime", "adminname", "keywords", "isrecommend","fromVisitorName", "activetime",
		"cataloguename" }, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminuid", "authoruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight" })
public class Forum extends BaseEntity implements Comparable<Forum> {

	private static final long serialVersionUID = 3525857126789211085L;
	private int cid;
	private String cataloguename; // 模块名称
	private String adminuid;
	private String authoruid;
	private String author;
	private String content;
	private String title; // 标题
	private String summary; // 摘要内容
	private String keywords;// SEO关键词
	private int isrecommend;// 是否推荐(是否在首页中显示)
	private int istop;// 是否置顶 [0否1是]
	private DateTime toptime; // 置顶时间
	private DateTime activetime; // 最后活跃时间(当帖子发生点赞、评论、收藏时更新)

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private int clickcount;
	private int prizecount;
	private int commentscount;
	private int collectcount;
	private float weight;// 权重值
	private Visitor fromVisitor;
	private String fromVisitorName;
	private User fromUser;

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public Visitor getFromVisitor() {
		return fromVisitor;
	}

	public void setFromVisitor(Visitor fromVisitor) {
		this.fromVisitor = fromVisitor;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
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

	public DateTime getActivetime() {
		return activetime;
	}

	public void setActivetime(DateTime activetime) {
		this.activetime = activetime;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getBooktimeStr() {
		return getActivetime().toString(DateUtil.FORMAT_DATETIME_CHINA);
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

	@Override
	public int compareTo(Forum o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Forum) {
			Forum article = (Forum) o;
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
	
	public String getFromVisitorName() {
		return fromVisitorName;
	}

	public void setFromVisitorName(String fromVisitorName) {
		this.fromVisitorName = fromVisitorName;
	}
}
