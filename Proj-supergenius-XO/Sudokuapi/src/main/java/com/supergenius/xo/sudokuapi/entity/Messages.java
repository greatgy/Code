package com.supergenius.xo.sudokuapi.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.base.BaseEntity;
import com.supergenius.xo.sudokuapi.enums.EMsg;

/**
 * 站内消息实体
 * @author YuYingJie
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = {Json.webStrategy, Json.appStrategy}, strategy = {Json.webStrategy, Json.appStrategy})
@Maps(strategy = Maps.dbStrategy)
public class Messages extends BaseEntity {

	private static final long serialVersionUID = -2086414919713225362L;
	private ObjectId from;//发送者id
	private List<ObjectId> to;//接收者id
	@Json(strategy = Json.webStrategy)
	private String title;//标题
	@Json(strategy = Json.webStrategy)
	private Map<String, Object> content;//内容
	private EMsg type;//类型
	@Json(strategy = Json.webStrategy)
	private boolean deleted;//是否删除
	@Json(strategy = Json.webStrategy)
	private int messagetype;//【0欢迎，1升级，2战报】
	@Json(strategy = Json.webStrategy)
	private String fromusername;
	@Json(strategy = Json.webStrategy)
	private List<String> tousername;
	
	//以下字段专为app使用，不保存在数据库中
	@Json(strategy = Json.appStrategy)
	private String grade;
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public List<String> getToid() {
		List<String> ids = new ArrayList<>();
		for (ObjectId id : this.to) {
			ids.add(id.toString());
		}
		return ids;
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public List<ObjectId> getTo() {
		return to;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setToid(List<String> to) {
		List<ObjectId> objectIds = new ArrayList<>();
		for (String id : to) {
			objectIds.add(new ObjectId(id));
		}
		this.to = objectIds;
	}
	
	@Maps(strategy = Maps.dbStrategy, isRaw = true)
	public void setTo(List<ObjectId> to) {
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public EMsg getType() {
		return type;
	}
	
	public void setType(EMsg type) {
		this.type = type;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getFromusername() {
		return fromusername;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public List<String> getTousername() {
		return tousername;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setTousername(List<String> tousername) {
		this.tousername = tousername;
	}

	public int getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(int messagetype) {
		this.messagetype = messagetype;
	}
}