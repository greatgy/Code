package com.supergenius.xo.life.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.rule.LifeProblemClickCountRlue;
import com.supergenius.xo.life.rule.LifeProblemCommentCountRlue;
import com.supergenius.xo.life.rule.LifeProblemPrizeCountRlue;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 问题实体类
 * 
 * @author JiaShitao
 * @date 2018年5月9日14:43:00
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid","adminuid", "useruid", "username", "content", "title", "place", "cataloguename", "istop", "toptime", "adminname", "data",
		"state", "ismember", "commentscount"}, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = { "adminname" })
public class Problem extends BaseEntity implements Comparable<Problem> {
	private static final long serialVersionUID = 3525857126789211085L;
	private long cid;// 模块id
	private String cataloguename;// 模块名字
	private String adminuid;// 操作管理员UID
	private String useruid;// 提问者uid
	private String username;
	private String content;// 内容
	private String title; // 标题
	private String place; // 行万里路模块目的地
	private String data; // 备用
	private EState state; // 状态(保存待点评、待回复等状态)
	private int istop; // 置顶(设为焦点文章)
	private DateTime toptime; // 置顶时间
	private int ismember; // 是否会员(0用户1会员)

	// 数据库中没有但要用到的字段
	private String adminname; // 管理员姓名
	private Long clickcount;
	private Long prizecount;
	private Long commentscount;
	private boolean isprize;
	private User user; // 用户
	private Visitor visitor; // 匿名
	private String fromuseruid;
	
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public EState getState() {
		return state;
	}

	public void setState(EState state) {
		this.state = state;
	}

	@Json(strategy = { Json.webStrategy })
	public String getStateName() {
		return this.state.getName();
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public DateTime getToptime() {
		return toptime;
	}

	public void setToptime(DateTime toptime) {
		this.toptime = toptime;
	}

	public int getIstop() {
		return istop;
	}

	public int getIsmember() {
		return ismember;
	}

	public void setIsmember(int ismember) {
		this.ismember = ismember;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public Long getCommentscount() {
		Rule rule = new LifeProblemCommentCountRlue(this.uid);
		this.commentscount = RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
		return this.commentscount;
	}
	
	public Long getClickcount() {
		Rule rule = new LifeProblemClickCountRlue(this.uid);
		this.clickcount = RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
		return this.clickcount;
	}
	
	public Long getPrizecount() {
		Rule rule = new LifeProblemPrizeCountRlue(this.uid);
		this.prizecount = RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
		return this.prizecount;
	}

	public void setClickcount(Long clickcount) {
		this.clickcount = clickcount;
	}

	public void setPrizecount(Long prizecount) {
		this.prizecount = prizecount;
	}

	public void setCommentscount(Long commentscount) {
		this.commentscount = commentscount;
	}

	public boolean isIsprize() {
		return isprize;
	}

	public void setIsprize(boolean isprize) {
		this.isprize = isprize;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public String getFirstTypeName() {
		return "problem";
	}
	
	public String getFromuseruid() {
		return fromuseruid;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}

	@Override
	public int compareTo(Problem o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Problem) {
			Problem problem = (Problem) o;
			if (this.getCommentscount() > problem.getCommentscount()) {
				return -1;
			} else if (this.getCommentscount() == problem.getCommentscount()) {
				if(this.getPrizecount() > problem.getPrizecount()) {
					return -1;
				}
				if(this.getPrizecount() < problem.getPrizecount()) {
					return 1;
				}
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
}
