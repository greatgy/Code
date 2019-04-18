package com.supergenius.xo.moral.entity;

import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.enums.EComment;
import com.supergenius.xo.user.entity.User;

/**
 * 评论
 * 
 * @author liushaomin
 * @modifier lijiacheng
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Comment extends BaseEntity {

	private static final long serialVersionUID = -9084001210962213942L;
	@Json(strategy = Json.webStrategy)
	private String fromuid;// 所评论的uid
	@Json(strategy = Json.webStrategy)
	private String touid;// 回复评论的uid，对应此表的uid
	@Json(strategy = { Json.webStrategy, Json.defaultStrategy })
	private String fromuseruid;// 评论人uid
	@Json(strategy = Json.webStrategy)
	private int fromuseroid;// 评论人oid
	@Json(strategy = { Json.webStrategy, Json.defaultStrategy })
	private String fromusername;// 评论人姓名
	@Json(strategy = { Json.webStrategy, Json.defaultStrategy })
	private String touseruid;// 评论给谁的uid
	@Json(strategy = Json.webStrategy)
	private int touseroid;// 评论给谁的oid
	@Json(strategy = { Json.webStrategy, Json.defaultStrategy })
	private String tousername;// 评论给谁的姓名
	@Json(strategy = Json.webStrategy)
	private String content;// 评论内容
	@Json(strategy = Json.webStrategy)
	private EComment type;// 评论类型
	@Json(strategy = Json.webStrategy)
	private EChannel channel;// 频道
	@Json(strategy = Json.webStrategy)
	private String fromtitle;
	@Json(strategy = Json.webStrategy)
	private int prizecount;// 赞数
	@Json(strategy = Json.webStrategy)
	private List<Comment> reply;
	@Json(strategy = Json.webStrategy)
	private User fromUser;
	@Json(strategy = Json.webStrategy)
	private String userAvatar;
	@Json(strategy = Json.webStrategy)
	private int isprize;// 是否点赞
	
	private String isvisitor;//匿名评论
	
	public String getIsvisitor() {
		return isvisitor;
	}

	public void setIsvisitor(String isvisitor) {
		this.isvisitor = isvisitor;
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

	public int getFromuseroid() {
		return fromuseroid;
	}

	public void setFromuseroid(int fromuseroid) {
		this.fromuseroid = fromuseroid;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTouseruid() {
		return touseruid;
	}

	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
	}

	public int getTouseroid() {
		return touseroid;
	}

	public void setTouseroid(int touseroid) {
		this.touseroid = touseroid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
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

	public void setChannel(EChannel channel) {
		this.channel = channel;
	}

	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getChannelName() {
		return EChannel.getName(this.channel, Locale.CHINA);
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getFromtitle() {
		return fromtitle;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromtitle(String fromtitle) {
		this.fromtitle = fromtitle;
	}

	public int getPrizecount() {
		return prizecount;
	}

	public void setPrizecount(int prizecount) {
		this.prizecount = prizecount;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public List<Comment> getReply() {
		return reply;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setReply(List<Comment> reply) {
		this.reply = reply;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public User getFromUser() {
		return fromUser;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUserAvatar() {
		return userAvatar;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public int getIsprize() {
		return isprize;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setIsprize(int isprize) {
		this.isprize = isprize;
	}

}
