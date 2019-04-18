package com.supergenius.xo.moral.entity;

import java.io.Serializable;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.enums.EStatus;

/**
 * 选项(Options)实体类
 * @author LiJiacheng
 */
@Json(value = {"uid", "status"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Options implements Serializable {
	
	private static final long serialVersionUID = 4499153806775634866L;
	
	@Json(strategy = Json.webStrategy)
	private String uid;
	@Json(strategy = Json.webStrategy)
	private String name;// 答案
	@Json(strategy = Json.webStrategy)
	private String sortorder;// 答案
	@Json(strategy = Json.webStrategy)
	private EStatus status = EStatus.enable;// 状态

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}
	
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getStatusName() {
		return EStatus.getName(this.status, Locale.CHINA);
	}

}
