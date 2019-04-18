package com.supergenius.xo.career.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.core.rule.CareerProblemCommentCountRlue;
import com.supergenius.core.rule.CareerProblemPartyCountRlue;
import com.supergenius.core.rule.CareerProblemPrizeCountRlue;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 问题实体类
 * 
 * @author ChenQi
 * @date 2017年11月13日16:55:15
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "adminuid", "useruid", "author", "content", "title",
		 "istop", "toptime", "adminname"}, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminname" })
public class Problem extends BaseEntity {

	private static final long serialVersionUID = 3525857126789211085L;
	private String adminuid;
	private String useruid;
	private String author;
	private String content;
	private String title; // 标题
	private int istop; // 置顶(设为焦点文章)
	private DateTime toptime; // 置顶时间

	// 下面属性不存在数据库中，封装为了使用
	private long partycount; // 参与总人数
	private String adminname; // 管理员姓名
	private boolean isprize;
	private String commenttime;
	private String commentname;
	
	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
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
	
	@Json(strategy = Json.webStrategy)
	public long getPartycount() {
		Rule rule1 = new CareerProblemPrizeCountRlue(this.uid);
		long admirecount = RedisUtil.getIncr(rule1) > 0 ? RedisUtil.getIncr(rule1) : 0;
		Rule rule2 = new CareerProblemCommentCountRlue(this.uid);
		long commentcount = RedisUtil.getIncr(rule2) > 0 ? RedisUtil.getIncr(rule2) : 0;
		Rule rule3 = new CareerProblemPartyCountRlue(this.uid);
		long prizecommentcount = RedisUtil.getIncr(rule3) > 0 ? RedisUtil.getIncr(rule3) : 0;
		this.partycount = admirecount + commentcount + prizecommentcount;
		return this.partycount;
	}

	public void setPartycount(long partycount) {
		this.partycount = partycount;
	}

	/**
	 * 得到评论的赞数
	 * 
	 * @return
	 */
	@Json(strategy = Json.webStrategy)
	public long getPrizecount() {
		Rule rule = new CareerProblemPrizeCountRlue(this.uid);
		admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}

	public Long getCommentcount() {
		Rule rule = new CareerProblemCommentCountRlue(this.uid);
		return RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
	}
	
	public String getFirstTypeName() {
		return "problem";
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public String getCommentname() {
		return commentname;
	}

	public void setCommentname(String commentname) {
		this.commentname = commentname;
	}
}
