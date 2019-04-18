package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.user.entity.User;

/**
 * 收件箱实体类
 * @author XieMing
 * @date 2016-7-17 下午12:03:31
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Inbox extends BaseEntity {

	private static final long serialVersionUID = -589531310517754898L;
	private String useruid;//接收者id
	private String msguid;//消息id
	private boolean isread;//消息状态(0未读1已读)
	private EMsgGroup typegroup;//消息组类型
	private Message message; //消息
	private User fromuser; //发送者
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getMsguid() {
		return msguid;
	}
	
	public void setMsguid(String msguid) {
		this.msguid = msguid;
	}
	
	public boolean isIsread() {
		return isread;
	}
	
	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	
	public EMsgGroup getTypegroup() {
		return typegroup;
	}

	public void setTypegroup(EMsgGroup typegroup) {
		this.typegroup = typegroup;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User sender) {
		this.fromuser = sender;
	}
	
}
