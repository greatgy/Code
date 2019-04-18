package com.supergenius.xo.startup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EMsg;

/**
 * 消息实体类
 * 
 * @author yangguang
 * @date 2017年8月29日09:44:57
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value = { "uid" })
public class News extends BaseEntity {

	private static final long serialVersionUID = 2034338681436147769L;
	private String fromuid;// 发送者id
	private String title;// 标题
	private String content;// 内容
	private String href;// 链接地址
	private EMsg type;// 消息类型
	
	// 下面属性不存在数据库中，封装为了使用
	private String fromusername; //发送人姓名
	private boolean isread; //是否阅读 0 未读 1 已读
	
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

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public boolean isIsread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}

}
