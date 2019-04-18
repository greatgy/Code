package com.supergenius.xo.manager.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EPKLog;
import com.supergenius.xo.manager.enums.EPKStage;

/** 
 * 挑战状态明细
 * @author chenminchang
 * @date 2016-7-18 下午2:07:14 
 */
public class PKStateDetail extends BaseEntity {

	private static final long serialVersionUID = -8508212493684324366L;
	private String pkscheduleuid; //挑战表UID
	private String useruid; //操作用户UID
	@Json(strategy = Json.webStrategy)
	private String desc; //描述
	private EPKStage fromstage; //原状态
	private EPKStage tostage; //现状态
	@Json(strategy = Json.webStrategy)
	private String action; //操作名称
	private EPKLog type; //操作类型
	//不存入数据库
	private String username; //操作用户的名字
	
	public PKStateDetail() {
		super();
	}
	public PKStateDetail(String pkscheduleuid, String useruid, EPKStage fromstage, EPKStage tostage, EPKLog type) {
		super();
		this.pkscheduleuid = pkscheduleuid;
		this.useruid = useruid;
		this.fromstage = fromstage;
		this.tostage = tostage;
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUsername() {
		return username;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getFromstageName() {
		return getFromstage().getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTostageName() {
		return getTostage().getName();
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return getType().getName();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPkscheduleuid() {
		return pkscheduleuid;
	}
	
	public void setPkscheduleuid(String pkscheduleuid) {
		this.pkscheduleuid = pkscheduleuid;
	}
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public EPKStage getFromstage() {
		return fromstage;
	}
	
	public void setFromstage(EPKStage fromstage) {
		this.fromstage = fromstage;
	}
	
	public EPKStage getTostage() {
		return tostage;
	}
	
	public void setTostage(EPKStage tostage) {
		this.tostage = tostage;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public EPKLog getType() {
		return type;
	}

	public void setType(EPKLog type) {
		this.type = type;
	}
}