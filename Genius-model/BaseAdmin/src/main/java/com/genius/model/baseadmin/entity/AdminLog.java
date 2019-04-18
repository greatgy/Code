package com.genius.model.baseadmin.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
/**
 * 管理员操作日志
 * @author liushaomin
 * @createtime 2014-10-15 下午4:15:04
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.allStrategy)
public class AdminLog extends BaseEntity {
	
	private static final long serialVersionUID = 7614957505747199951L;
	@Json(strategy = Json.webStrategy)
	private String adminuid;
	@Json(strategy = Json.webStrategy)
	private String adminname;
	@Json(strategy = Json.webStrategy)
	private String dataid;
	@Json(strategy = Json.webStrategy)
	private String data;
	@Json(strategy = Json.webStrategy)
	private int channel;
	@Json(strategy = Json.webStrategy)
	private String operation;
	@Json(strategy = Json.webStrategy)
	private String desc;
	
	public String getAdminuid() {
		return adminuid;
	}
	
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	
	public String getAdminname() {
		return this.adminname;
	}
	
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
	public String getDataid() {
		return dataid;
	}
	
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	
	public int getChannel() {
		return channel;
	}
	
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
