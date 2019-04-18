package com.supergenius.xo.official.entity;

import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.official.enums.EDiscuss;
import com.supergenius.xo.official.enums.EUserType;

/**
 * 评论互动
 * @author liushaomin
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy=Maps.dbStrategy)
public class Discuss extends BaseEntity{
	
	private static final long serialVersionUID = 697911878620490830L;
	@Json(strategy = Json.webStrategy)
	private String fromuid;//评论者uid
    @Json(strategy = Json.webStrategy)
    private String fromname;//评论者姓名
    @Json(strategy = Json.webStrategy)
    private String fromavatar;//评论者头像
	@Json(strategy = Json.webStrategy)
    private EUserType usertype;//评论、管理员回复
	@Json(strategy = Json.webStrategy)
    private EDiscuss type;//评论、管理员回复
    @Json(strategy = Json.webStrategy)
    private String title;//标题
    @Json(strategy = Json.webStrategy)
    private String content;//内容
    @Json(strategy = Json.webStrategy)
    private boolean istop;//是否置顶
    @Json(strategy = Json.webStrategy)
    private List<Discuss> reply;//是否置顶
    
	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
	
	public String getFromname() {
		return fromname;
	}
	
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	
	public String getFromavatar() {
		return fromavatar;
	}

	public void setFromavatar(String fromavatar) {
		this.fromavatar = fromavatar;
	}
	
	public EUserType getUsertype() {
		return usertype;
	}

	public void setUsertype(EUserType usertype) {
		this.usertype = usertype;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserTypeName() {
		return EUserType.getName(usertype, Locale.CHINA);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsMember() {
		return this.usertype == EUserType.member;
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsVisitor() {
		return this.usertype == EUserType.visitor;
	}
	
	public EDiscuss getType() {
		return type;
	}
	
	public void setType(EDiscuss type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EDiscuss.getName(type, Locale.CHINA);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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
	
	public List<Discuss> getReply() {
		return reply;
	}
	
	public void setReply(List<Discuss> reply) {
		this.reply = reply;
	}
}
