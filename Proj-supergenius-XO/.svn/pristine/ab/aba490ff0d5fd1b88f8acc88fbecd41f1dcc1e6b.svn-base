package com.supergenius.xo.sudokuapi.entity;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.base.BaseEntity;

/**
 * 站内消息收件箱实体
 * @author YuYingJie
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = {Maps.dbStrategy})
public class Inboxes extends BaseEntity {

	private static final long serialVersionUID = -1824632362136153534L;
	private ObjectId to;//接收者id
	private ObjectId from;//发送者id
	private ObjectId message;//消息id
	private String title;//消息标题
	private boolean read;//是否已读
	private int type;//消息类型【0欢迎，1升级，2战报】
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getToid() {
		return to.toString();
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public ObjectId getTo() {
		return to;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setToid(String to) {
		this.to = new ObjectId(to);
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public void setTo(ObjectId to) {
		this.to = to;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getFromid() {
		return from.toString();
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public ObjectId getFrom() {
		return from;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromid(String from) {
		this.from = new ObjectId(from);
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public void setFrom(ObjectId from) {
		this.from = from;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getMessageid() {
		return message.toString();
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public ObjectId getMessage() {
		return message;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setMessageid(String message) {
		this.message = new ObjectId(message);
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public void setMessage(ObjectId message) {
		this.message = message;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getRead() {
		return read;
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
