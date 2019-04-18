package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;

/**
 * 消息实体类
 * @author XieMing
 * @date 2016-7-17 下午12:03:51
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Message extends BaseEntity {

	private static final long serialVersionUID = 2034338681436147769L;
	private String fromuid;//发送者id
	private String title;//标题
	private String content;//内容
	private String href;//链接地址
	private EMsg type;//消息类型
	private EMsgGroup typegroup;//消息组类型,将所有消息类型分为三组
	
	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
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
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public EMsg getType() {
		return type;
	}
	
	public void setType(EMsg type) {
		this.type = type;
	}
	
	public EMsgGroup getTypegroup() {
		return typegroup;
	}

	public void setTypegroup(EMsgGroup typegroup) {
		this.typegroup = typegroup;
	}
}
