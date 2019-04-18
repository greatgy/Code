package com.supergenius.xo.life.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;

/**
 * @author tf
 * @date 2018年8月20日
 */
@Json(value = { "country", "province", "city", "title", "oid", "cid"}, ignoreTypeStrategy = { Json.webStrategy}, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
public class Travel {
	private String country;
	private String province;
	private String city;
	private String title;
	private Long cid;
	private int oid;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
