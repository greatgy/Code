package com.supergenius.xo.tpi.entity;

import java.io.Serializable;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;

/**
 * 联系信息
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class ContactInfo implements Serializable{
	
	private static final long serialVersionUID = -6057490780272817105L;
	private String tel;// 机构电话
	private String email;// 机构邮箱
	private String contactman;// 联系人
	private String contactmantel;// 联系人手机
	private String contactmanemail; // 联系人邮箱
	private String province;// 国家
	private String city;// 城市

	private String cityName;
	private String provinceName;

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getCityName() {
		return cityName;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getProvinceName() {
		return provinceName;
	}

	@MapsIgnore(strategy = Maps.dbStrategy)
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactman() {
		return contactman;
	}

	public void setContactman(String contactman) {
		this.contactman = contactman;
	}

	public String getContactmantel() {
		return contactmantel;
	}

	public void setContactmantel(String contactmantel) {
		this.contactmantel = contactmantel;
	}

	public String getContactmanemail() {
		return contactmanemail;
	}

	public void setContactmanemail(String contactmanemail) {
		this.contactmanemail = contactmanemail;
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

	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getAddress() {
		return getProvinceName() + " " + getCityName(); 
	}

}
