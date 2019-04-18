package com.supergenius.server.manager.third.seegle.entity;

import java.io.Serializable;

/**
 * 授权实体
 * 
 * @author chenminchang
 * @createtime 2016-9-9上午10:47:47
 */
public class AuthEntity implements Serializable {
	
	private static final long serialVersionUID = 4008086704368614215L;
	private String accessKey;

	public AuthEntity(String accessKey) {
		super();
		this.accessKey = accessKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

}
