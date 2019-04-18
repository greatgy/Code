package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Maps;

/**
 * 区域实体
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class Region {

	private int uid;
	private String name;
	private int parentid;

	@Maps(strategy=Maps.allStrategy, alias="_id")
	public int getUid() {
		return uid;
	}

	@Maps(strategy=Maps.allStrategy, alias="_id")
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
