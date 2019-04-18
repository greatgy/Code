package com.supergenius.xo.sudokuapi.entity;

import org.bson.types.ObjectId;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.supergenius.xo.base.BaseEntity;

/**
 * 登录历史实体
 * @author YuYingJie
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Loginhistories extends BaseEntity {

	private static final long serialVersionUID = 7450355081976261598L;
	@Json(strategy = Json.webStrategy)
	private ObjectId user;//用户id
	@Json(strategy = Json.webStrategy)
	private String ip;//用户ip地址
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getUserid() {
		return user.toString();
	}
	
	@Maps(strategy = Maps.allStrategy, isRaw = true)
	public ObjectId getUser() {
		return user;
	}
	
	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setUserid(String user) {
		this.user = new ObjectId(user);
	}
	
	@Maps(strategy = Maps.allStrategy, isRaw = true)
	public void setUser(ObjectId user) {
		this.user = user;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
