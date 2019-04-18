package com.supergenius.xo.startup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EMsg;

/**
 * 收件箱实体类
 * @author yangguang
 * @date 2017年8月29日09:45:35
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Inbox extends BaseEntity {

	private static final long serialVersionUID = -589531310517754898L;
	private String useruid;//接收者id
	private String newsuid;//消息id
	private boolean isread;//消息状态(0未读1已读)
	private News news; //消息
	//private User fromuser; //发送者
	private EMsg type;// 消息类型
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getNewsuid() {
		return newsuid;
	}

	public void setNewsuid(String newsuid) {
		this.newsuid = newsuid;
	}

	public boolean isIsread() {
		return isread;
	}
	
	public void setIsread(boolean isread) {
		this.isread = isread;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

/*	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User sender) {
		this.fromuser = sender;
	}
*/
	public EMsg getType() {
		return type;
	}

	public void setType(EMsg type) {
		this.type = type;
	}
	
}
