package com.supergenius.xo.manager.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.core.rule.CommentCountPkRule;
import com.supergenius.core.rule.PrizeCountPkRule;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EComment;

/**
 * 评论实体类
 * @author XieMing
 * @date 2016-7-17 下午12:04:13
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})

public class Comments extends BaseEntity {
	
	private static final long serialVersionUID = -1737598427813970860L;
	@Json(strategy = Json.webStrategy)
	private String fromuid;//所评论文章的UID
	@Json(strategy = Json.webStrategy)
	private String touid;//回复comment的uid，对应此表的uid
	@Json(strategy = Json.webStrategy)
	private String fromuseruid;//评论人uid
	@Json(strategy = Json.webStrategy)
	private String touseruid;//评论给谁的uid
	@Json(strategy = Json.webStrategy)
	private String content;//评论，若赞则使用<a>保存标题及连接地址
	@Json(strategy = Json.webStrategy)
	private EComment type;//类型，评论或者赞
	@Json(strategy = Json.webStrategy)
	private EChannel channel;//频道
	private User fromUser;
	private User toUser;
	private User user;
	private String data;
	@Json(strategy = Json.webStrategy)
	private String fromName;//来源事件的名称（挑战或视频）
	private List<Comments> replys = new ArrayList<>();
	private boolean isprize;
	
	
	public Comments() {
		super();
	}
	
	public Comments(String fromuid, String touid, String fromuseruid, String touseruid, String content, EComment type, EChannel channel) {
		super();
		this.fromuid = fromuid;
		this.touid = touid;
		this.fromuseruid = fromuseruid;
		this.touseruid = touseruid;
		this.content = content;
		this.type = type;
		this.channel = channel;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFromUser() {
		return fromUser;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFromUserName() {
		return fromUser.getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public int getFromUserOid() {
		return fromUser.getOid();
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public boolean getIsprize(){
		return this.isprize;
	}
	
	public void setIsprize(boolean isprize) {
		this.isprize = isprize;
	}

	public List<Comments> getReplys() {
		return replys;
	}

	public void setReplys(List<Comments> replys) {
		this.replys = replys;
	}

	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
	
	public String getTouid() {
		return touid;
	}
	
	public void setTouid(String touid) {
		this.touid = touid;
	}
	
	public String getFromuseruid() {
		return fromuseruid;
	}
	
	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}
	
	public String getTouseruid() {
		return touseruid;
	}
	
	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public EComment getType() {
		return type;
	}
	
	public void setType(EComment type) {
		this.type = type;
	}
	
	public EChannel getChannel() {
		return channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return channel.getName();
	}
	
	public void setChannel(EChannel channel) {
		this.channel = channel;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 得到评论的赞数
	 * @return
	 */
	@Json(strategy = Json.webStrategy)
	public long getPrizecount() {
		Rule rule = new PrizeCountPkRule(this.uid);
		long admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}
	
	public long getCommentCount() {
		Rule rule = new CommentCountPkRule(this.uid);
		long admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
}
