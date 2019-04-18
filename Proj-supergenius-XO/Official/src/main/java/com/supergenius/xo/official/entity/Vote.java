package com.supergenius.xo.official.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;


/**
 * 顾雏军专栏 投票
 * @author liushaomin
 */
@Json(value = {"uid", "createtime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy=Maps.dbStrategy)
public class Vote extends BaseEntity{

	private static final long serialVersionUID = 2594968535011711381L;
	
	private String useragent;
	private String loginip;
	private String cookie;
	
	public String getUseragent() {
		return useragent;
	}
	
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	
	public String getLoginip() {
		return loginip;
	}
	
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
